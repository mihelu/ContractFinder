package pl.edu.pk.msala.contractfinder.web.rest.filter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import org.joda.time.DateTime;
import pl.edu.pk.msala.contractfinder.web.constants.Constants;
import pl.edu.pk.msala.contractfinder.web.rest.security.AuthContext;
import pl.edu.pk.msala.contractfinder.web.rest.security.AuthUtil;
import pl.edu.pk.msala.contractfinder.web.session.WebSession;
import pl.edu.pk.msala.contractfinder.web.session.WebSessionsContainer;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 27.04.13
 * Time: 13:01
 */
public class ResponseFilter implements ContainerResponseFilter {
    @Override
    public ContainerResponse filter(ContainerRequest containerRequest, ContainerResponse containerResponse) {
        if (AuthUtil.shouldFilterUrl(containerRequest.getRequestUri().getPath())) {
            AuthContext securityContext = (AuthContext) containerRequest.getSecurityContext();
            if (securityContext != null) {
                NewCookie sessionCookie = AuthUtil.createCookie(AuthUtil.SESSIONID, securityContext.getWebSession().getSessionId());
                NewCookie accessTokenCookie = AuthUtil.createCookie(AuthUtil.ACCESSTOKEN, securityContext.getWebSession().getAccessToken());
                WebSession webSession = securityContext.getWebSession();
                webSession.setCreateDate(new DateTime());
                WebSessionsContainer.putWebSession(webSession);
                containerResponse.setResponse(Response.fromResponse(containerResponse.getResponse()).cookie(sessionCookie).cookie(accessTokenCookie).build());
            } else {
                containerResponse.setResponse(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
        return containerResponse;
    }
}
