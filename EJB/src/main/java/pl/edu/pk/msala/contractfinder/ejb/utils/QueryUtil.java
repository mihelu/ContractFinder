package pl.edu.pk.msala.contractfinder.ejb.utils;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 15:56
 */
public class QueryUtil {

    private QueryUtil() {

    }

    public static <T> T getSingleResult(Criteria criteria) throws AppException {
        return getSingleResult(criteria, null);
    }

    public static <T> T getSingleResult(Criteria criteria, String message) throws AppException {
        try {
            T result = (T) criteria.uniqueResult();
            if (result == null) {
                if (message == null && message.equals("")) {
                    throw new AppException();
                } else {
                    throw new AppException(message);
                }
            }
            return result;
        } catch (HibernateException e) {
            if (message == null || message.equals("")) {
                throw new AppException(e);
            } else {
                throw new AppException(message, e);
            }
        }
    }
}
