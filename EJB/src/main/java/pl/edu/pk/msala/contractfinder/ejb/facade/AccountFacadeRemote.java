package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;

import javax.ejb.Remote;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 15:52
 */
@Remote
public interface AccountFacadeRemote {

    public Account getAccount(AccountData accountData) throws AppException;
}
