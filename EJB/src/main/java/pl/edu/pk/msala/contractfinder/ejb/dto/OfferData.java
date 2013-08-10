package pl.edu.pk.msala.contractfinder.ejb.dto;

import pl.edu.pk.msala.contractfinder.ejb.entity.Offer;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 10.08.13
 * Time: 17:50
 */
public class OfferData implements Serializable {

    private Boolean allowed;
    private Offer offer;

    public Boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
