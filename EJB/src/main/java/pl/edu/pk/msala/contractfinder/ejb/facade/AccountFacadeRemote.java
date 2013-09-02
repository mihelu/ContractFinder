package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 15:52
 */
@Remote
public interface AccountFacadeRemote {

    public Account getAccount(AccountData accountData) throws AppException;

    public Account getAccount(Long id);

    public void createAccount(Account account) throws AppRollbackException;

    public List<Account> findAccounts();

    public void blockAccount(Long id);

    public void unblockAccount(Long id);
}
