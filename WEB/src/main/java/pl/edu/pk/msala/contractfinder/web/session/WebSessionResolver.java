package pl.edu.pk.msala.contractfinder.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 21.07.13
 * Time: 16:10
 */
public abstract class WebSessionResolver {


    @Context
    private HttpServletRequest httpServletRequest;

    public WebSession resolveWebSession() {
        return WebSessionsContainer.getWebSession(httpServletRequest);
    }
}
