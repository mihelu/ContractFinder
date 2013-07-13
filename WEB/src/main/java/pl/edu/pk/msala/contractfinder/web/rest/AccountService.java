package pl.edu.pk.msala.contractfinder.web.rest;

import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;
import pl.edu.pk.msala.contractfinder.ejb.facade.AccountFacadeRemote;
import pl.edu.pk.msala.contractfinder.web.locator.FacadeLocator;
import pl.edu.pk.msala.contractfinder.web.session.WebSessionsContainer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 20.04.13
 * Time: 12:42
 */
@Path("/account")
public class AccountService {

    private final Logger logger = Logger.getLogger(AccountService.class);
    private AccountFacadeRemote accountFacadeRemote = FacadeLocator.<AccountFacadeRemote>getFacade(AccountFacadeRemote.class);

    @Path("/register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Account account) throws AppRollbackException {
        logger.info(account);
        accountFacadeRemote.createAccount(account);
        return Response.ok().build();
    }

    @GET
    @Path("/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@CookieParam(value = "sessionId") String sessionId) {
        Account account = null;
        if (WebSessionsContainer.isWebSession(sessionId)) {
            account = accountFacadeRemote.getAccount(WebSessionsContainer.getWebSession(sessionId).getAccountId());
            account.setLogin(null);
            account.setPassword(null);
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok().entity(account).build();
    }
}
