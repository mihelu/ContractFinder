package pl.edu.pk.msala.contractfinder.ejb.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.hibernate.Hibernate;
import pl.edu.pk.msala.contractfinder.ejb.constant.ACC_ROLES;
import pl.edu.pk.msala.contractfinder.ejb.constant.ACC_STATUS;
import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.entity.Role;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;
import pl.edu.pk.msala.contractfinder.ejb.manager.AccountManager;

import javax.ejb.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 15:09
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AccountService {

    @EJB
    private AccountManager accountManager;

    public void createAccount(Account account) throws AppRollbackException {
        try {
            Role userRole = accountManager.getRole(ACC_ROLES.USER);
            account.setCreateDate(new Date());
            account.setStatus(ACC_STATUS.ACTIVE);
            account.setRoles(Sets.newHashSet(userRole));
            accountManager.createAccount(account);
        } catch (Exception e) {
            throw new AppRollbackException("Rejestracja nie powiodła się!");
        }
    }

    public Account getAccount(AccountData accountData) throws AppException {
        Account account = accountManager.getAccount(accountData);
        if(ACC_STATUS.BLOCKED.equals(account.getStatus())) {
            throw new AppException("Konto zablokowane!");
        }
        return account;
    }

    public Account getAccount(Long id) {
        Account account = accountManager.getAccount(id);
        Hibernate.initialize(account.getRoles());
        return account;
    }

    public List<Account> findAccounts() {
        return accountManager.findAccounts();
    }

    public void blockAccount(Long id) {
        changeStatus(id, ACC_STATUS.BLOCKED);
    }

    public void unblockAccount(Long id) {
        changeStatus(id, ACC_STATUS.ACTIVE);
    }

    private void changeStatus(Long id, String status) {
        Account account = accountManager.getAccount(id);
        account.setStatus(status);
        accountManager.modifyAccount(account);
    }
}
