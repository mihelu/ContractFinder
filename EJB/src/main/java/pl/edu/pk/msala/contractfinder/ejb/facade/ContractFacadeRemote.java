package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.entity.Contract;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;

import javax.ejb.Remote;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 23.06.13
 * Time: 16:51
 */
@Remote
public interface ContractFacadeRemote {

    public Long createContract(Contract contract) throws AppRollbackException;
}
