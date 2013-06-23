package pl.edu.pk.msala.contractfinder.web.rest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.entity.Contract;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;
import pl.edu.pk.msala.contractfinder.ejb.facade.ContractFacadeRemote;
import pl.edu.pk.msala.contractfinder.web.locator.FacadeLocator;
import pl.edu.pk.msala.contractfinder.web.rest.security.AuthUtil;
import pl.edu.pk.msala.contractfinder.web.session.WebSessionsContainer;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 23.06.13
 * Time: 16:47
 */
@Path("/contract")
public class ContractService {

    private final Logger logger = Logger.getLogger(ContractService.class);
    private ContractFacadeRemote contractFacadeRemote = FacadeLocator.<ContractFacadeRemote>getFacade(ContractFacadeRemote.class);

    @Context
    private HttpServletRequest httpServletRequest;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createContract(Contract contract) throws AppRollbackException {
        logger.info(contract);
        if(!StringUtils.isEmpty(AuthUtil.getSessionId(httpServletRequest.getCookies()))) {
            Account account = new Account();
            account.setId(WebSessionsContainer.getWebSession(AuthUtil.getSessionId(httpServletRequest.getCookies())).getAccountId());
            contract.setAccount(account);
        }else {
            return Response.status(500).entity("BŁĄD WEWNĘTRZNY SYSTEMU!").type(MediaType.TEXT_PLAIN_TYPE).build();
        }
        Long id = contractFacadeRemote.createContract(contract);
        return Response.ok(id).build();
    }

    @POST
    @Path("/details")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContract(Long id) throws AppException {
        Contract contract = contractFacadeRemote.getContract(id);
        contract.getAccount().setRoles(null);
        return Response.ok(contract).build();
    }
}
