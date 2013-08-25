package pl.edu.pk.msala.contractfinder.ejb.manager;

import pl.edu.pk.msala.contractfinder.ejb.entity.Offer;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.utils.QueryUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 21.07.13
 * Time: 17:34
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class OfferManager {

    @PersistenceContext(unitName = "ContractFinderDB")
    private EntityManager entityManager;

    public List<Offer> findContractOffers(Long contractId) {
        return entityManager.createNamedQuery(Offer.OFF_FIND_CONTRACT_OFFERS).setParameter(1, contractId).getResultList();
    }

    public List<Offer> findAccountOffers(Long accountId) {
        return entityManager.createNamedQuery(Offer.OFF_FIND_ACCOUNT_OFFERS).setParameter(1, accountId).getResultList();
    }

    public Offer getAccountContractOffer(Long accountId, Long contactId) {
        try {
            return QueryUtil.getSingleResult(entityManager.createNamedQuery(Offer.OFF_GET_ACCOUNT_CONTRACT_OFFER, Offer.class).setParameter(1, accountId).setParameter(2, contactId));
        } catch (AppException e) {
            return null;
        }
    }

    public Offer createOffer(Offer offer) {
        entityManager.merge(offer);
        entityManager.flush();
        return offer;
    }

    public void removeOffer(Long id) {
        Offer toRemove = entityManager.find(Offer.class, id);
        entityManager.remove(toRemove);
        entityManager.flush();
    }
}
