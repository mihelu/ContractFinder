package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.entity.Contract;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;
import pl.edu.pk.msala.contractfinder.ejb.service.ContractService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 23.06.13
 * Time: 16:52
 */
@Stateless
public class ContractFacade implements ContractFacadeRemote {

    @EJB
    private ContractService contractService;

    @Override
    public Long createContract(Contract contract) throws AppRollbackException {
        return contractService.createContract(contract);
    }
}
