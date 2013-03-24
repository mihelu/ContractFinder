package pl.edu.pk.msala.contractfinder.locator;

import pl.edu.pk.msala.contractfinder.utils.WebAppConfiguration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;

/**
 * User: mhl
 * Date: 12.03.13
 * Time: 15:59
 */
public class ServiceLocator {
    private InitialContext ic;
    private static final String NAMING_URL = WebAppConfiguration.getParam(WebAppConfiguration.NAMING_URL);

    public ServiceLocator() {
        try {
            Properties p = new Properties();
            p.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
            p.put("java.naming.provider.url", NAMING_URL);
            p.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
            ic = new InitialContext(p);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }

    }

    private Object lookup(String jndiName) throws NamingException {
        return ic.lookup(jndiName);
    }


    public <X extends Object> X getRemote(String jndiname, Class className) throws NamingException {
        Object objref = lookup(jndiname);
        return (X) PortableRemoteObject.narrow(objref, className);
    }
}
