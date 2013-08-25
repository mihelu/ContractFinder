package pl.edu.pk.msala.contractfinder.ejb.manager;

import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account_;
import pl.edu.pk.msala.contractfinder.ejb.entity.Role;
import pl.edu.pk.msala.contractfinder.ejb.entity.Role_;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.utils.QueryUtil;

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

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 15:01
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AccountManager {

    private static final Logger logger = Logger.getLogger(AccountManager.class);

    @PersistenceContext(unitName = "ContractFinderDB")
    private EntityManager entityManager;

    public void createAccount(Account account) {
        logger.info(account);
        entityManager.persist(account);
    }

    public Account getAccount(AccountData accountData) throws AppException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> query = builder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);
        Predicate restrictions = builder.and(
                builder.equal(root.get(Account_.login), accountData.getLogin()),
                builder.equal(root.get(Account_.password), accountData.getPassword())
        );
        root.fetch(Account_.roles);
        query.where(restrictions);
        return QueryUtil.<Account>getSingleResult(entityManager.createQuery(query), "Nieprawidłowe dane");
    }

    public Account getAccount(Long id) {
        return entityManager.find(Account.class, id);
    }

    public Account getAccount(String login) throws AppException {
        return QueryUtil.<Account>getSingleResult(entityManager.createNamedQuery(Account.ACC_GET_BY_LOGIN, Account.class).setParameter(1, login));
    }

    public Role getRole(String name) throws AppException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        query.where(builder.equal(root.get(Role_.name), name));
        return QueryUtil.<Role>getSingleResult(entityManager.createQuery(query), "GET_ROLE_FAILED");
    }
}
