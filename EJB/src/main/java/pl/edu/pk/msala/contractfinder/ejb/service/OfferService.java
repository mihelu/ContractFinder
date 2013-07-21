package pl.edu.pk.msala.contractfinder.ejb.service;

import org.hibernate.Hibernate;
import pl.edu.pk.msala.contractfinder.ejb.entity.Offer;
import pl.edu.pk.msala.contractfinder.ejb.manager.OfferManager;

import javax.ejb.*;
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

    @EJB
    private OfferManager offerManager;

    public List<Offer> findAccountOffers(Long accountId) {
        List<Offer> offers = offerManager.findAccountOffers(accountId);
        for(Offer offer : offers) {
            Hibernate.initialize(offer.getContract());
            offer.getContract().setOffers(null);
            offer.getContract().setCategories(null);
        }
        return offers;
    }
}
