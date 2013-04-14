package pl.edu.pk.msala.contractfinder.ejb.exception;

import org.hibernate.HibernateException;

import javax.ejb.ApplicationException;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 15:03
 */
@ApplicationException(rollback = false)
public class AppException extends Exception{

    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
