package pl.edu.pk.msala.contractfinder.web.rest.security;

import com.google.common.base.Function;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.entity.Role;
import pl.edu.pk.msala.contractfinder.web.session.WebSession;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 27.04.13
 * Time: 14:01
 */
public class AuthContext implements SecurityContext{

    private WebSession webSession;

    public AuthContext(final WebSession webSession) {
        this.webSession = webSession;
    }

    @Override
    public Principal getUserPrincipal() {
        return new Principal() {
            @Override
            public String getName() {
                return webSession.getSessionId();
            }
        };
    }

    @Override
    public boolean isUserInRole(String s) {
        boolean isInRole = false;
        Iterator<Role> it = webSession.getRoles().iterator();
        while (it.hasNext()) {
            isInRole = s.equals(it.next().getName());
        }
        if(!isInRole) {
            Response denied = Response.status(Response.Status.FORBIDDEN).entity("Permission Denied").build();
            throw new WebApplicationException(denied);
        }
        return true;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }

    public WebSession getWebSession() {
        return webSession;
    }
}
