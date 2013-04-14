package pl.edu.pk.msala.contractfinder.web.rest;

import com.google.common.collect.Lists;
import pl.edu.pk.msala.contractfinder.ejb.dto.AccountData;
import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;
import pl.edu.pk.msala.contractfinder.web.service.AccountService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 24.03.13
 * Time: 16:40
 */
@Path("/auth")
public class Auth {

    private AccountService accountService = new AccountService();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Account login(AccountData accountData) throws AppException {
         return accountService.login(accountData);
    }

}
