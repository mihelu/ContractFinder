package pl.edu.pk.msala.contractfinder.ejb.manager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.utils.QueryUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 15:01
 */
@Stateless
@LocalBean
public class AccountManager {

    @PersistenceContext(unitName = "ContractFinderDB")
    private EntityManager entityManager;

    public Account findAccount(AccountData accountData) throws AppException {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Account.class);
        criteria.add(Restrictions.eq("login", accountData.getLogin()));
        criteria.add(Restrictions.eq("password", accountData.getPassword()));
        return QueryUtil.<Account>getSingleResult(criteria, "GET_ACCOUNT_FAILED"); //FIXME message?
    }

}
