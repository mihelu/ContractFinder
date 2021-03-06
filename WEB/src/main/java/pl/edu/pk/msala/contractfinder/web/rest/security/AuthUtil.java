package pl.edu.pk.msala.contractfinder.web.rest.security;

import com.google.common.collect.Sets;

import javax.ws.rs.core.NewCookie;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 29.04.13
 * Time: 19:39
 */
public class AuthUtil {

    public static final String SESSION_ID = "sessionId";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final int TOKEN_EXPIRE = 900; //sec
    public static final Set<String> noFilterUrls = Sets.newHashSet(
            "/rest/auth/login",
            "/rest/auth/logout",
            "/rest/account/register"
    );

    public static NewCookie createCookie(String name, String value) {
        return createCookie(name, value, TOKEN_EXPIRE);
    }

    public static NewCookie createCookie(String name, String value, int expire) {
        return new NewCookie(name, value, "/", "", "", expire, false);
    }

    public static String getSessionId(javax.servlet.http.Cookie[] cookies) {
        String result = "";
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(SESSION_ID)) {
                result = cookies[i].getValue();
                break;
            }
        }
        return result;
    }

    public static String generateToken(String login, String password) {
        try {
            String tokenString = login + password + System.currentTimeMillis();
            MessageDigest messageDigest = null;
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(tokenString.getBytes("UTF-8"));
            return bytesToHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String bytesToHex(byte[] b) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuffer buf = new StringBuffer();
        for (int j = 0; j < b.length; j++) {
            buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
            buf.append(hexDigit[b[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static boolean shouldFilterUrl(String url) {
        return !noFilterUrls.contains(url);
    }
}
