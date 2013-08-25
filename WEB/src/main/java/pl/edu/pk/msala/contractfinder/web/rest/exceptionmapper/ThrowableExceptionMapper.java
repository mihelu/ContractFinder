package pl.edu.pk.msala.contractfinder.web.rest.exceptionmapper;

import org.apache.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 29.04.13
 * Time: 00:27
 */
@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {

    private final Logger logger = Logger.getLogger(ThrowableExceptionMapper.class);

    @Override
    public Response toResponse(Throwable throwable) {
        logger.error(throwable);
        return Response.status(500).entity("BŁĄD WEWNĘTRZNY SYSTEMU!").type(MediaType.TEXT_PLAIN_TYPE).build();  //TODO message
    }
}
