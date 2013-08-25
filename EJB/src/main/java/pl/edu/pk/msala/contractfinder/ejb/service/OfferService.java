package pl.edu.pk.msala.contractfinder.ejb.service;

import org.hibernate.Hibernate;
import pl.edu.pk.msala.contractfinder.ejb.dto.OfferData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Contract;
import pl.edu.pk.msala.contractfinder.ejb.entity.Offer;
import pl.edu.pk.msala.contractfinder.ejb.manager.ContractManager;
import pl.edu.pk.msala.contractfinder.ejb.manager.OfferManager;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 21.07.13
 * Time: 19:43
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class OfferService {

    @PersistenceContext(unitName = "ContractFinderDB")
    private EntityManager entityManager;
    @EJB
    private OfferManager offerManager;
    @EJB
    private ContractManager contractManager;

    public List<Offer> findAccountOffers(Long accountId) {
        List<Offer> offers = offerManager.findAccountOffers(accountId);
        for (Offer offer : offers) {
            Hibernate.initialize(offer.getContract());
        }
        return offers;
    }

    public OfferData getAccountContractOffer(Long accountId, Long contactId) {
        OfferData result = new OfferData();
        Contract contract = contractManager.getContract(contactId);
        if (contract != null) {
            if(contract.getPublishEnd().before(new Date())) {
                result.setAllowed(false);
            }else if(contract.getAccount().getId().equals(accountId)) {
                result.setAllowed(false);
            } else {
                Offer offer = offerManager.getAccountContractOffer(accountId, contactId);
                entityManager.detach(offer);
                if (offer != null) {
                    offer.setContract(null);
                    offer.setAccount(null);
                }
                result.setOffer(offer);
                result.setAllowed(true);
            }
        }
        return result;
    }

    public Offer createOffer(Offer offer) {
        offer.setRemoved(false);
        return offerManager.createOffer(offer);
    }

    public void reopenOffer(Long id) {
       Offer toModify = entityManager.find(Offer.class, id);
       toModify.setRemoved(null);
       offerManager.createOffer(toModify);
    }
}
