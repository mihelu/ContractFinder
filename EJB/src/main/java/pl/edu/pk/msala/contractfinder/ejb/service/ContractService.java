package pl.edu.pk.msala.contractfinder.ejb.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.hibernate.Hibernate;
import org.joda.time.Period;
import pl.edu.pk.msala.contractfinder.ejb.dto.find.ContractFindData;
import pl.edu.pk.msala.contractfinder.ejb.dto.list.AccountContractListData;
import pl.edu.pk.msala.contractfinder.ejb.dto.list.ContractListData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.entity.Contract;
import pl.edu.pk.msala.contractfinder.ejb.entity.Offer;
import pl.edu.pk.msala.contractfinder.ejb.manager.ContractManager;
import pl.edu.pk.msala.contractfinder.ejb.manager.OfferManager;

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
    @EJB
    private OfferManager offerManager;

    public Long createContract(Contract contract) {
        contract.setAccount(accountService.getAccount(contract.getAccount().getId()));
        return contractManager.createContract(contract);
    }

    public Contract getContract(Long id) {
        Contract contract = contractManager.getContract(id);
        Hibernate.initialize(contract.getAccount());
        Hibernate.initialize(contract.getCategories());
        Hibernate.initialize(contract.getOffers());
        return contract;
    }

    public List<AccountContractListData> findAccountContracts(Long id) {
        return repackAccountContractList(contractManager.findAccountContracts(id));
    }

    public List<ContractListData> findContracts(ContractFindData findData) {
        return repackContractList(contractManager.findContracts(findData));
    }

    private List<AccountContractListData> repackAccountContractList(List<Contract> contracts) {
        return Lists.newArrayList(Lists.transform(contracts, new Function<Contract, AccountContractListData>() {
            @Override
            public AccountContractListData apply(Contract input) {
                AccountContractListData accountContractListData = new AccountContractListData();
                accountContractListData.setId(input.getId());
                accountContractListData.setName(input.getName());
                List<Offer> offers = offerManager.findContractOffers(input.getId());
                accountContractListData.setOffers(offers.size());
                return accountContractListData;
            }
        }));
    }

    private List<ContractListData> repackContractList(List<Contract> contracts) {
        return Lists.newArrayList(Lists.transform(contracts, new Function<Contract, ContractListData>() {
            @Override
            public ContractListData apply(Contract input) {
                ContractListData contractListData = new ContractListData();
                contractListData.setId(input.getId());
                contractListData.setName(input.getName());
                contractListData.setPublishStart(input.getPublishStart());
                Period period = new Period(new Date().getTime(), input.getPublishEnd().getTime());
                contractListData.setMonths(period.getMonths());
                contractListData.setDays(period.getDays());
                contractListData.setHours(period.getHours());
                contractListData.setMinutes(period.getMinutes());
                contractListData.setSeconds(period.getSeconds());
                contractListData.setMillis(period.getMillis());
                Account account = input.getAccount();
                if (account.getPersonal()) {
                    contractListData.setPublisher(account.getUser().getFirstName() + " " + account.getUser().getLastName());
                } else {
                    contractListData.setPublisher(account.getCompany().getName());
                }
                return contractListData;
            }
        }));
    }
}
