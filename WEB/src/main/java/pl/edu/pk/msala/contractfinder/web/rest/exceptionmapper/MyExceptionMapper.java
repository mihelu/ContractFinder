package pl.edu.pk.msala.contractfinder.web.rest.exceptionmapper;

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
public class MyExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        return Response.status(404).entity("TEST MESSAGE FROM REST").type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
