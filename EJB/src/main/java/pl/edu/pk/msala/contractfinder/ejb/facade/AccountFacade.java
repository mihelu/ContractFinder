package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;
import pl.edu.pk.msala.contractfinder.ejb.manager.AccountManager;
import pl.edu.pk.msala.contractfinder.ejb.service.AccountService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 15:52
 */
@Stateless
public class AccountFacade implements AccountFacadeRemote {

    @EJB
    private AccountService accountService;
    @EJB
    private AccountManager accountManager;

    @Override
    public Account getAccount(AccountData accountData) throws AppException {
        return accountService.getAccount(accountData);
    }

    @Override
    public Account getAccount(Long id) {
        return accountService.getAccount(id);
    }

    @Override
    public void createAccount(Account account) throws AppRollbackException {
        accountService.createAccount(account);
    }

    @Override
    public List<Account> findAccounts() {
        return accountService.findAccounts();
    }

    @Override
    public void blockAccount(Long id) {
        accountService.blockAccount(id);
    }

    @Override
    public void unblockAccount(Long id) {
        accountService.unblockAccount(id);
    }
}
