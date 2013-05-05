package pl.edu.pk.msala.contractfinder.web.rest.exceptionmapper;

import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 29.04.13
 * Time: 01:34
 */
@Provider
public class AppRollbackExceptionMapper implements ExceptionMapper<AppRollbackException> {
    @Override
    public Response toResponse(AppRollbackException e) {
        return Response.status(500).entity(e.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
