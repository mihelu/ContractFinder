package pl.edu.pk.msala.contractfinder.web.rest;

import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.ejb.facade.CategoryFacadeRemote;
import pl.edu.pk.msala.contractfinder.web.locator.FacadeLocator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 13.08.13
 * Time: 22:37
 */
@Path("/category")
public class CategoryService {

    private CategoryFacadeRemote categoryFacadeRemote = FacadeLocator.<CategoryFacadeRemote>getFacade(CategoryFacadeRemote.class);
    private final Logger logger = Logger.getLogger(CategoryService.class);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok().entity(categoryFacadeRemote.getCategoriesDict()).build();
    }
}
