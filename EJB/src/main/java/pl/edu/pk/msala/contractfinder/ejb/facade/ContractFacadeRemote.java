package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.dto.find.ContractFindData;
import pl.edu.pk.msala.contractfinder.ejb.dto.list.AccountContractListData;
import pl.edu.pk.msala.contractfinder.ejb.dto.list.ContractListData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Contract;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 23.06.13
 * Time: 16:51
 */
@Remote
public interface ContractFacadeRemote {

    public Long createContract(Contract contract) throws AppRollbackException;

    public Contract getContract(Long id) throws AppException;

    public List<ContractListData> findContracts(ContractFindData findData);

    public List<AccountContractListData> findAccountContracts(Long id);

    public List<ContractListData> pullFinishedContracts(Long accountId);
}
