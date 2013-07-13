package pl.edu.pk.msala.contractfinder.web.rest;

import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.facade.AccountFacadeRemote;
import pl.edu.pk.msala.contractfinder.web.locator.FacadeLocator;
import pl.edu.pk.msala.contractfinder.web.rest.security.AuthUtil;
import pl.edu.pk.msala.contractfinder.web.session.WebSession;
import pl.edu.pk.msala.contractfinder.web.session.WebSessionsContainer;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 24.03.13
 * Time: 16:40
 */
@Path("/auth")
public class AuthService {

    private AccountFacadeRemote accountFacadeRemote = FacadeLocator.<AccountFacadeRemote>getFacade(AccountFacadeRemote.class);
    private final Logger logger = Logger.getLogger(AuthService.class);

    @Context
    private HttpServletRequest httpServletRequest;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AccountData accountData) throws AppException {
        Account account = accountFacadeRemote.getAccount(accountData);
        String sessionId = httpServletRequest.getSession(true).getId();
        String accessToken = AuthUtil.generateToken(account.getLogin(), account.getPassword());
        WebSessionsContainer.putWebSession(new WebSession(account.getId(), sessionId, accessToken, account.getRoles()));
        logger.info("LOGGED IN: " + account);
        return Response.ok().
                cookie(AuthUtil.createCookie(AuthUtil.SESSIONID, sessionId)).
                cookie(AuthUtil.createCookie(AuthUtil.ACCESSTOKEN, accessToken)).
                build();
    }

}
