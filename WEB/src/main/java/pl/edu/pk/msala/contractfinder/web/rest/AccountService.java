package pl.edu.pk.msala.contractfinder.web.rest;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;
import pl.edu.pk.msala.contractfinder.ejb.facade.AccountFacadeRemote;
import pl.edu.pk.msala.contractfinder.web.locator.FacadeLocator;
import pl.edu.pk.msala.contractfinder.web.session.WebSessionsContainer;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @GET
    @Path("/details/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("id") Long id) {
        Account account = accountFacadeRemote.getAccount(id);
        account.setLogin(null);
        account.setPassword(null);
        return Response.ok(account).build();
    }

    @GET
    @Path("/all")
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAccounts() {
        List<Account> accounts = Lists.newArrayList(Lists.transform(accountFacadeRemote.findAccounts(), new Function<Account, Account>() {
            @Override
            public Account apply(Account input) {
                input.setRoles(null);
                input.setUser(null);
                input.setCompany(null);
                return input;
            }
        }));
        return Response.ok(accounts).build();
    }

    @GET
    @Path("/block/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void blockAccount(@PathParam("id") Long id) {
        accountFacadeRemote.blockAccount(id);
    }

    @GET
    @Path("/unblock/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void unblockAccount(@PathParam("id") Long id) {
        accountFacadeRemote.unblockAccount(id);
    }
}
