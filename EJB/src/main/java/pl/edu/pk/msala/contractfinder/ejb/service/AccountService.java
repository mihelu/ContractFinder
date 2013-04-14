package pl.edu.pk.msala.contractfinder.ejb.service;

import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.manager.AccountManager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

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

    public Account login(AccountData accountData) throws AppException {
        return accountManager.findAccount(accountData);
    }
}
