package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.dto.find.ContractFindData;
import pl.edu.pk.msala.contractfinder.ejb.dto.list.AccountContractListData;
import pl.edu.pk.msala.contractfinder.ejb.dto.list.ContractListData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Contract;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;
import pl.edu.pk.msala.contractfinder.ejb.service.ContractService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

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

    @Override
    public Contract getContract(Long id) throws AppException {
        return contractService.getContract(id);
    }

    @Override
    public List<ContractListData> findContracts(ContractFindData findData) {
        return contractService.findContracts(findData);
    }

    @Override
    public List<AccountContractListData> findAccountContracts(Long id) {
        return contractService.findAccountContracts(id);
    }
}
