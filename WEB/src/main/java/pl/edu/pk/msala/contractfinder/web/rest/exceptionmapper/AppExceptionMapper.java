package pl.edu.pk.msala.contractfinder.web.rest.exceptionmapper;

import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 01.04.13
 * Time: 13:20
 */
@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {

    @Override
    public Response toResponse(AppException e) {
        return Response.status(404).entity(e.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
