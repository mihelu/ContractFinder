package pl.edu.pk.msala.contractfinder.ejb.manager;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.entity.Role;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.utils.QueryUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 15:01
 */
@Stateless
@LocalBean
public class AccountManager {

    private static final Logger logger = Logger.getLogger(AccountManager.class);

    @PersistenceContext(unitName = "ContractFinderDB")
    private EntityManager entityManager;

    public void createAccount(Account account) {
        logger.info(account);
        entityManager.persist(account);
    }

    public Account getAccount(AccountData accountData) throws AppException {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Account.class);
        criteria.setFetchMode("roles", FetchMode.JOIN);
        criteria.add(Restrictions.eq("login", accountData.getLogin()));
        criteria.add(Restrictions.eq("password", accountData.getPassword()));
        return QueryUtil.<Account>getSingleResult(criteria, "Nieprawidłowe dane"); //FIXME message?
    }

    public Account getAccount(Long id) {
        return entityManager.find(Account.class, id);
    }

    public Role getRole(String name) throws AppException {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("name", name));
        return QueryUtil.<Role>getSingleResult(criteria, "GET_ROLE_FAILED"); //FIXME message?
    }
}
