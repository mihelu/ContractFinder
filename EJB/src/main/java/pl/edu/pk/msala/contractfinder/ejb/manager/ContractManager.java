package pl.edu.pk.msala.contractfinder.ejb.manager;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.hibernate.ejb.criteria.OrderImpl;
import pl.edu.pk.msala.contractfinder.ejb.dto.find.ContractFindData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Category;
import pl.edu.pk.msala.contractfinder.ejb.entity.Category_;
import pl.edu.pk.msala.contractfinder.ejb.entity.Contract;
import pl.edu.pk.msala.contractfinder.ejb.entity.Contract_;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 27.04.13
 * Time: 12:57
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ContractManager {

    @PersistenceContext(unitName = "ContractFinderDB")
    private EntityManager entityManager;

    public Long createContract(Contract contract) {
        Contract created = entityManager.merge(contract);
        entityManager.flush();
        return created.getId();
    }

    public Contract getContract(Long id) {
        return entityManager.find(Contract.class, id);
    }

    public List<Contract> findContracts(ContractFindData findData) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contract> query = builder.createQuery(Contract.class);
        Root<Contract> root = query.from(Contract.class);
        ListJoin<Contract, Category> join = null;
        if (findData.getCategories() != null && !findData.getCategories().isEmpty()) {
            join = root.join(Contract_.categories, JoinType.LEFT);
        }
        List<Predicate> predicates = Lists.newArrayList();
        Predicate predicateOr = builder.or(
                builder.like(builder.lower(root.get(Contract_.name)), "%" + StringUtils.defaultString(findData.getQuery()).toLowerCase() + "%"),
                builder.like(builder.lower(root.get(Contract_.description)), "%" + StringUtils.defaultString(findData.getQuery()).toLowerCase() + "%")
        );
        predicates.add(predicateOr);
        if (findData.getCategories() != null && !findData.getCategories().isEmpty()) {
            Predicate category = builder.isTrue(join.get(Category_.id).in(findData.getCategories()));
            predicates.add(category);
        }
        if (!findData.isSearchInFinished()) {
            Predicate publishFinished = builder.greaterThanOrEqualTo(root.get(Contract_.publishEnd), new Date());
            predicates.add(publishFinished);
        }
        Predicate predicate = builder.and(predicates.toArray(new Predicate[predicates.size()]));
        query.where(predicate);
        Order order = new OrderImpl(root.get(Contract_.publishEnd), true);

        query.orderBy(new Order[]{order});
        return entityManager.createQuery(query.distinct(true)).getResultList();
    }

    public List<Contract> findAccountContracts(Long id) {
        return entityManager.createNamedQuery(Contract.CON_FIND_ACCOUNT_CONTRACTS).setParameter(1, id).getResultList();
    }
}
