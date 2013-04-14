package pl.edu.pk.msala.contractfinder.ejb.interceptor;

import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 28.03.13
 * Time: 20:35
 */
public class FacadeInterceptor {

    Logger logger = Logger.getLogger(FacadeInterceptor.class);

    @AroundInvoke
    public Object aroundInvoke(InvocationContext invocationContext) throws Exception {
        String method = invocationContext.getTarget().getClass().getSimpleName() + "." + invocationContext.getMethod().getName();
        //logger.info("Before invoke: " + method);
        try {
            return invocationContext.proceed();
        } catch (Exception pe) {
            logger.error("Exception in: " + method + " : " + pe.getMessage());
            throw new AppRollbackException(pe);
        } finally {
            //logger.info("After invoke: " + method);
        }
    }
}
