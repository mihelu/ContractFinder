package pl.edu.pk.msala.contractfinder.web.service;

import pl.edu.pk.msala.contractfinder.ejb.facade.TestFacadeRemote;
import pl.edu.pk.msala.contractfinder.web.locator.FacadeLocator;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 28.03.13
 * Time: 23:07
 */
public class TestService {

    public static String test() {
        TestFacadeRemote facadeRemote = FacadeLocator.<TestFacadeRemote>getFacade(TestFacadeRemote.class);
        return facadeRemote.test();
    }
}
