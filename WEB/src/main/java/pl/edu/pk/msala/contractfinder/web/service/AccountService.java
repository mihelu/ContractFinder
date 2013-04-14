package pl.edu.pk.msala.contractfinder.web.service;

import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.facade.AccountFacadeRemote;
import pl.edu.pk.msala.contractfinder.web.locator.FacadeLocator;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 18:50
 */
public class AccountService {

       public Account login(AccountData accountData) throws AppException {
           AccountFacadeRemote accountFacadeRemote = FacadeLocator.<AccountFacadeRemote>getFacade(AccountFacadeRemote.class);
           return accountFacadeRemote.getAccount(accountData);
       }
}
