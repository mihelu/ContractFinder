package pl.edu.pk.msala.contractfinder.web.rest.filter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.web.constants.Constants;
import pl.edu.pk.msala.contractfinder.web.rest.security.AuthContext;
import pl.edu.pk.msala.contractfinder.web.session.WebSession;
import pl.edu.pk.msala.contractfinder.web.session.WebSessionsContainer;

import javax.ws.rs.core.Cookie;

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
        if (shouldFilter(containerRequest)) {
            Cookie sessionCookie = containerRequest.getCookies().get("sessionId");
            Cookie accessTokenCookie = containerRequest.getCookies().get("accessToken");

            if (isWebSession(sessionCookie, accessTokenCookie)) {
                WebSession webSession = WebSessionsContainer.getWebSession(sessionCookie.getValue());
                if (webSession.getAccessToken().equals(accessTokenCookie.getValue())) {
                    containerRequest.setSecurityContext(new AuthContext(webSession));
                } else {
                    WebSessionsContainer.removeWebSession(sessionCookie.getValue());
                    containerRequest.setSecurityContext(null);
                }
            } else {
                containerRequest.setSecurityContext(null);
            }
        }
        return containerRequest;
    }

    private boolean isWebSession(Cookie session, Cookie accessToken) {
        return session != null && accessToken != null && WebSessionsContainer.isWebSession(session.getValue());
    }

    private boolean shouldFilter(ContainerRequest containerRequest) {
        return !containerRequest.getRequestUri().getPath().equals(Constants.LOGIN_PATH) &&
                !containerRequest.getRequestUri().getPath().equals(Constants.LOGOUT_PATH) &&
                !containerRequest.getRequestUri().getPath().equals(Constants.REGISTER_PATH);
    }
}

