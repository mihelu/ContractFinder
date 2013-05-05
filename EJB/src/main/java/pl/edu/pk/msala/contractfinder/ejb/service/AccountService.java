package pl.edu.pk.msala.contractfinder.ejb.service;

import com.google.common.collect.Sets;
import pl.edu.pk.msala.contractfinder.ejb.constant.Roles;
import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.entity.Role;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;
import pl.edu.pk.msala.contractfinder.ejb.manager.AccountManager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 15:09
 */
@Stateless
@LocalBean
public class AccountService {

    @EJB
    private AccountManager accountManager;

    public void createAccount(Account account) throws AppRollbackException {
        try {
            Role userRole = accountManager.getRole(Roles.USER);
            account.setCreateDate(new Date());
            account.setRoles(Sets.newHashSet(userRole));
            accountManager.createAccount(account);
        } catch (Exception e) {
            throw new AppRollbackException(e);
        }
    }

    public Account getAccount(AccountData accountData) throws AppException {
        Account account = accountManager.getAccount(accountData);
        account.getRoles();
        return account;
    }
}