package pl.edu.pk.msala.contractfinder.ejb.manager;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import pl.edu.pk.msala.contractfinder.ejb.dto.find.ContractFindData;
import pl.edu.pk.msala.contractfinder.ejb.dto.list.ContractListData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Contract;
import pl.edu.pk.msala.contractfinder.ejb.entity.Contract_;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        entityManager.persist(contract);
        entityManager.flush();
        return contract.getId();
    }

    public Contract getContract(Long id) {
        return entityManager.find(Contract.class, id);
    }

    public List<ContractListData> findContracts(ContractFindData findData) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ContractListData> query = builder.createQuery(ContractListData.class);
        Root<Contract> root = query.from(Contract.class);
        query.select(
                builder.construct(ContractListData.class, root.get(Contract_.id), root.get(Contract_.name), root.get(Contract_.publishStart))
        );
        List<Predicate> predicates = Lists.newArrayList();
        Predicate predicateOr = builder.or(
                builder.like(builder.lower(root.get(Contract_.name)), "%" + StringUtils.defaultString(findData.getQuery()) + "%"),
                builder.like(builder.lower(root.get(Contract_.description)), "%" + StringUtils.defaultString(findData.getQuery()) + "%")
        );
        predicates.add(predicateOr);
        Predicate predicate = builder.and(predicates.toArray(new Predicate[predicates.size()]));
        query.where(predicate);
        return entityManager.createQuery(query).getResultList();
    }
}
