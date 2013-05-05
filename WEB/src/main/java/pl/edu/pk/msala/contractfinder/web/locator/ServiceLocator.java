package pl.edu.pk.msala.contractfinder.web.locator;

import org.apache.log4j.Logger;

import javax.naming.*;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;

/**
 * User: mhl
 * Date: 12.03.13
 * Time: 15:59
 */
public class ServiceLocator {

    private static final Logger logger = Logger.getLogger(ServiceLocator.class);
    private InitialContext ic;

    public ServiceLocator() {
        try {
            Properties jndiProps = new Properties();
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            jndiProps.put(Context.PROVIDER_URL, "remote://localhost:4447");
            jndiProps.put(Context.SECURITY_PRINCIPAL, "jboss");
            jndiProps.put(Context.SECURITY_CREDENTIALS, "jbosspass");
            jndiProps.put("jboss.naming.client.ejb.context", true);
            jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            jndiProps.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_DISALLOWED_MECHANISMS", "JBOSS-LOCAL-USER");
            jndiProps.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", false);
            ic = new InitialContext(jndiProps);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    public <X extends Object> X getRemote(String jndiname, Class className) throws NamingException {
        return (X) ic.lookup(jndiname);
    }
}
