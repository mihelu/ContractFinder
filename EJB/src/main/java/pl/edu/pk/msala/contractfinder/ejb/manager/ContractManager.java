package pl.edu.pk.msala.contractfinder.ejb.manager;

import pl.edu.pk.msala.contractfinder.ejb.entity.Contract;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
