package pl.edu.pk.msala.contractfinder.web.rest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.ejb.dto.OfferData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.entity.Offer;
import pl.edu.pk.msala.contractfinder.ejb.facade.OfferFacadeRemote;
import pl.edu.pk.msala.contractfinder.web.locator.FacadeLocator;
import pl.edu.pk.msala.contractfinder.web.rest.security.AuthUtil;
import pl.edu.pk.msala.contractfinder.web.session.WebSessionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
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

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOffer(Offer offer) {
        if (!StringUtils.isEmpty(AuthUtil.getSessionId(httpServletRequest.getCookies()))) {
            Account account = new Account();
            account.setId(resolveWebSession().getAccountId());
            offer.setAccount(account);
            logger.info(offer);
        } else {
            return Response.status(500).entity("BŁĄD WEWNĘTRZNY SYSTEMU!").type(MediaType.TEXT_PLAIN_TYPE).build();
        }
        Offer created = offerFacadeRemote.createOffer(offer);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Path("/account")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAccountOffers() {
        List<Offer> offers = offerFacadeRemote.findAccountOffers(resolveWebSession().getAccountId());
        for (Offer offer : offers) {
            offer.getContract().setOffers(null);
            offer.getContract().setCategories(null);
        }
        return Response.ok(offers).build();
    }

    @GET
    @Path("/contract/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountContractOffer(@PathParam("id") Long id) {
        OfferData offer = offerFacadeRemote.getAccountContactOffer(resolveWebSession().getAccountId(), id);
        return Response.ok(offer).build();
    }
}
