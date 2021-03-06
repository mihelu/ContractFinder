package pl.edu.pk.msala.contractfinder.web.rest.filter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.web.constants.Constants;
import pl.edu.pk.msala.contractfinder.web.rest.security.AuthContext;
import pl.edu.pk.msala.contractfinder.web.rest.security.AuthUtil;
import pl.edu.pk.msala.contractfinder.web.session.WebSession;
import pl.edu.pk.msala.contractfinder.web.session.WebSessionsContainer;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 27.04.13
 * Time: 12:59
 */
public class RequestFilter implements ContainerRequestFilter {

    Logger logger = Logger.getLogger(RequestFilter.class);

    @Override
    public ContainerRequest filter(ContainerRequest containerRequest) {
        if (AuthUtil.shouldFilterUrl(containerRequest.getRequestUri().getPath())) {
            Cookie sessionCookie = containerRequest.getCookies().get("sessionId");
            Cookie accessTokenCookie = containerRequest.getCookies().get("accessToken");

            if (isWebSession(sessionCookie, accessTokenCookie)) {
                WebSession webSession = WebSessionsContainer.getWebSession(sessionCookie.getValue());
                if (webSession.getAccessToken().equals(accessTokenCookie.getValue())) {
                    containerRequest.setSecurityContext(new AuthContext(webSession));
                } else {
                    WebSessionsContainer.removeWebSession(sessionCookie.getValue());
                    containerRequest.setSecurityContext(null);
                    throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            } else {
                containerRequest.setSecurityContext(null);
                throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
        return containerRequest;
    }

    private boolean isWebSession(Cookie session, Cookie accessToken) {
        return session != null && accessToken != null && WebSessionsContainer.isWebSession(session.getValue());
    }
}

