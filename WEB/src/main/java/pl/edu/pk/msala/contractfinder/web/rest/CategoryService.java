package pl.edu.pk.msala.contractfinder.web.rest;

import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;
import pl.edu.pk.msala.contractfinder.ejb.facade.CategoryFacadeRemote;
import pl.edu.pk.msala.contractfinder.web.locator.FacadeLocator;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
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
    public Response getCategoriesDict() {
        return Response.ok().entity(categoryFacadeRemote.getCategoriesDict()).build();
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllCategories() {
        return Response.ok().entity(categoryFacadeRemote.getAllCategories()).build();
    }

    @RolesAllowed("ADMIN")
    @POST
    @Path("/changeStatus/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeCategoryStatus(@PathParam("id") Long id, String removed) {
        categoryFacadeRemote.changeCategoryStatus(id, "true".equals(removed));
        return Response.ok().build();
    }

    @RolesAllowed("ADMIN")
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(String name) throws AppRollbackException {
        categoryFacadeRemote.createCategory(name);
        return Response.ok().build();
    }

}
