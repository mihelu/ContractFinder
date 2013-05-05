package pl.edu.pk.msala.contractfinder.web.session;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 29.04.13
 * Time: 17:20
 */
public class WebSessionsContainer {

    private static Map<String,WebSession> sessionMap = Maps.newConcurrentMap();

    public static boolean isWebSession(String sessionId) {
        return sessionMap.containsKey(sessionId);
    }

    public static WebSession getWebSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    public static void putWebSession(WebSession webSession) {
        sessionMap.put(webSession.getSessionId(), webSession);
    }

    public static void removeWebSession(String sessionId) {
        sessionMap.remove(sessionId);
    }
}
