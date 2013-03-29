package pl.edu.pk.msala.contractfinder.ejb.manager;

import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.entity.User;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 27.03.13
 * Time: 21:30
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TestManager {

    @PersistenceContext(unitName = "ContractFinderDB")
    private EntityManager entityManager;

    public User testPersist(User user) {
        entityManager.persist(user);
        return user;
    }
    public User testUpdate(User user) {
        return user;
          //throw new EJBTransactionRolledbackException("BLAD");
    }

    public void createAccount(Account account) {
        entityManager.persist(account);
    }
}
