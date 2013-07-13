package pl.edu.pk.msala.contractfinder.ejb.service;

import org.hibernate.Hibernate;
import pl.edu.pk.msala.contractfinder.ejb.dto.find.ContractFindData;
import pl.edu.pk.msala.contractfinder.ejb.dto.list.ContractListData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Contract;
import pl.edu.pk.msala.contractfinder.ejb.manager.ContractManager;

import javax.ejb.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 27.04.13
 * Time: 12:58
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ContractService {

    @EJB
    private ContractManager contractManager;
    @EJB
    private AccountService accountService;

    public Long createContract(Contract contract) {
        contract.setAccount(accountService.getAccount(contract.getAccount().getId()));
        contract.setPublishStart(new Date());
        return contractManager.createContract(contract);
    }

    public Contract getContract(Long id) {
        Contract contract = contractManager.getContract(id);
        Hibernate.initialize(contract.getAccount());
        Hibernate.initialize(contract.getCategories());
        Hibernate.initialize(contract.getOffers());
        return contract;
    }

    public List<ContractListData> findContracts(ContractFindData findData) {
        return contractManager.findContracts(findData);
    }
}
