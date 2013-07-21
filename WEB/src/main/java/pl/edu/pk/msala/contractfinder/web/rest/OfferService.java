package pl.edu.pk.msala.contractfinder.web.rest;

import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.ejb.entity.Offer;
import pl.edu.pk.msala.contractfinder.ejb.facade.OfferFacadeRemote;
import pl.edu.pk.msala.contractfinder.web.locator.FacadeLocator;
import pl.edu.pk.msala.contractfinder.web.session.WebSessionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 21.07.13
 * Time: 19:52
 */
@Path("/offer")
public class OfferService extends WebSessionResolver{

    private final Logger logger = Logger.getLogger(ContractService.class);
    private OfferFacadeRemote offerFacadeRemote = FacadeLocator.<OfferFacadeRemote>getFacade(OfferFacadeRemote.class);

    @Context
    private HttpServletRequest httpServletRequest;

    @GET
    @Path("/account")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAccountOffers() {
        List<Offer> offers = offerFacadeRemote.findAccountOffers(resolveWebSession().getAccountId());
        return Response.ok(offers).build();
    }
}
