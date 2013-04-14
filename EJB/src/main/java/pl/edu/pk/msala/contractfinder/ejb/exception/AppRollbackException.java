package pl.edu.pk.msala.contractfinder.ejb.exception;

import javax.ejb.ApplicationException;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 28.03.13
 * Time: 22:48
 */
@ApplicationException(rollback = true)
public class AppRollbackException extends Exception{

    public AppRollbackException() {

    }

    public AppRollbackException(String message) {
        super(message);
    }

    public AppRollbackException(Throwable cause) {
        super(cause);
    }
}
