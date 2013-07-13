package pl.edu.pk.msala.contractfinder.ejb.utils;

import pl.edu.pk.msala.contractfinder.ejb.exception.AppException;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 15:56
 */
public class QueryUtil {

    private QueryUtil() {

    }

    public static <T> T getSingleResult(TypedQuery<T> query) throws AppException {
        return getSingleResult(query, null);
    }

    public static <T> T getSingleResult(TypedQuery<T> query, String message) throws AppException {
        try {
            T result = query.getSingleResult();
            if (result == null) {
                if (message == null && message.equals("")) {
                    throw new AppException();
                } else {
                    throw new AppException(message);
                }
            }
            return result;
        } catch (PersistenceException e) {
            if (message == null || message.equals("")) {
                throw new AppException(e);
            } else {
                throw new AppException(message, e);
            }
        }
    }
}
