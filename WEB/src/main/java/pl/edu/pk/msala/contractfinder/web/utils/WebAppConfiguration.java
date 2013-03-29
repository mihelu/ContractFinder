package pl.edu.pk.msala.contractfinder.web.utils;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Collections;
import java.util.Map;

/**
 * User: suowik
 * Date: 12.03.13
 * Time: 16:03
 */
public class WebAppConfiguration implements ServletContextListener{

    private static Map<String,String> contextParameters;
    public static final String NAMING_URL = "naming.url";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext context = servletContextEvent.getServletContext();
        contextParameters = Maps.toMap(Sets.newHashSet(Collections.list(context.getInitParameterNames())), new Function<String, String>() {
            @Override
            public String apply(String key) {
                return context.getInitParameter(key);
            }
        });
        System.out.println(contextParameters);
    }

    public static String getParam(String key){
        return contextParameters.get(key);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
