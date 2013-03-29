package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.entity.Account;
import pl.edu.pk.msala.contractfinder.ejb.entity.User;
import pl.edu.pk.msala.contractfinder.ejb.interceptor.FacadeInterceptor;
import pl.edu.pk.msala.contractfinder.ejb.manager.TestManager;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 17.03.13
 * Time: 20:51
 */
@Stateless
@Interceptors(value = FacadeInterceptor.class)
public class TestFacade implements TestFacadeRemote {

    @EJB
    private TestManager testManager;

    @Override
    public String test() {
        User user = new User();
        user.setName("TEST");
        user.setLastName("TEST");
        user = testManager.testPersist(user);
        user = testManager.testUpdate(user);

        Account account = new Account();
        account.setUser(user);
        account.setPersonal(Boolean.TRUE);
        account.setCreateDate(new Date());
        account.setLogin("LOGIN");
        account.setPassword("PASSWORD");

        testManager.createAccount(account);

        return "TEST_FROM_EJB";
    }
}
