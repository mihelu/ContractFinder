package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.entity.Offer;
import pl.edu.pk.msala.contractfinder.ejb.service.OfferService;

import javax.ejb.EJB;
import javax.ejb.Remote;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 21.07.13
 * Time: 19:46
 */
@Remote
public interface OfferFacadeRemote {

    public List<Offer> findAccountOffers(Long accountId);
}
