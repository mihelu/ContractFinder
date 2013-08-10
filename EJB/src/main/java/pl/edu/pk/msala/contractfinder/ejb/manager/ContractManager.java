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
import javax.persistence.criteria.*;
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

    public List<Contract> findContracts(ContractFindData findData) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contract> query = builder.createQuery(Contract.class);
        Root<Contract> root = query.from(Contract.class);
//        root.fetch(Contract_.account, JoinType.LEFT);
//        query.select(
//                builder.construct(Contract.class, root.get(Contract_.id), root.get(Contract_.name), root.get(Contract_.publishStart), root.get(Contract_.publishEnd), root.get(Contract_.account))
//        );
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

    public List<Contract> findAccountContracts(Long id) {
        return entityManager.createNamedQuery(Contract.CON_FIND_ACCOUNT_CONTRACTS).setParameter(1, id).getResultList();
    }
}
