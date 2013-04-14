package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.service.AccountService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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

    @Override
    public Account getAccount(AccountData accountData) throws AppException {
        return accountService.login(accountData);
    }
}
