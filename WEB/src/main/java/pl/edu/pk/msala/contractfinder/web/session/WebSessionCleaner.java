package pl.edu.pk.msala.contractfinder.web.session;

import com.google.common.util.concurrent.AbstractScheduledService;
import org.apache.log4j.Logger;
import pl.edu.pk.msala.contractfinder.web.rest.security.AuthUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 22.06.13
 * Time: 23:16
 */
public class WebSessionCleaner implements ServletContextListener {

    Logger logger = Logger.getLogger(WebSessionCleaner.class);
    private CleaningService service;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("CleaningService started");
        service = new CleaningService();
        service.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("CleaningService stopped");
        service.stop();
    }

    private class CleaningService extends AbstractScheduledService {

        @Override
        protected void runOneIteration() throws Exception {
            logger.info("RUN_ONE_ITERATION");
            for(Iterator<Map.Entry<String,WebSession>> it = WebSessionsContainer.getSessionMap().entrySet().iterator();it.hasNext();) {
                Map.Entry<String,WebSession> entry = it.next();
                logger.info("Session: " + entry.getKey());
                if(entry.getValue().getCreateDate().plusSeconds(AuthUtil.TOKEN_EXPIRE).isBeforeNow()) {
                    it.remove();
                }
            }
        }

        @Override
        protected Scheduler scheduler() {
            return Scheduler.newFixedRateSchedule(0, 1, TimeUnit.SECONDS);
        }
    }
}
