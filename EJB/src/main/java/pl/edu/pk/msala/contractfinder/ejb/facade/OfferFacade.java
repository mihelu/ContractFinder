package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.dto.OfferData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Offer;
import pl.edu.pk.msala.contractfinder.ejb.manager.OfferManager;
import pl.edu.pk.msala.contractfinder.ejb.service.OfferService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 21.07.13
 * Time: 19:46
 */
@Stateless
public class OfferFacade implements OfferFacadeRemote {
    @EJB
    private OfferService offerService;
    @EJB
    private OfferManager offerManager;

    @Override
    public Offer createOffer(Offer offer) {
        return offerService.createOffer(offer);
    }

    @Override
    public void removeOffer(Long id) {
       offerManager.removeOffer(id);
    }

    @Override
    public List<Offer> findAccountOffers(Long accountId) {
        return offerService.findAccountOffers(accountId);
    }

    @Override
    public OfferData getAccountContactOffer(Long accountId, Long contractId) {
        return offerService.getAccountContractOffer(accountId, contractId);
    }

    @Override
    public void reopenOffer(Long id) {
        offerService.reopenOffer(id);
    }
}
