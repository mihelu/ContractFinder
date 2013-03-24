package pl.edu.pk.msala.contractfinder.locator;

import javax.naming.NamingException;

/**
 * User: mhl
 * Date: 12.03.13
 * Time: 16:05
 */
public class FacadeLocator {

    private static final String JNDI_PREFIX = "EAR/";


    private FacadeLocator() {
    }

    public static <T> T getFacade(Class viewClass) {
        try {
            ServiceLocator serviceLocator = new ServiceLocator();
            System.out.println(JNDI_PREFIX + viewClass.getSimpleName().replaceAll("Remote$", "") + "/remote-" + viewClass.getCanonicalName());
            return serviceLocator.<T>getRemote(JNDI_PREFIX + viewClass.getSimpleName().replaceAll("Remote$", "") + "/remote-" + viewClass.getCanonicalName(), viewClass);
        } catch (NamingException ex) {
            throw new IllegalArgumentException("Cannot locate facade: " + viewClass, ex);
        }
    }


}
