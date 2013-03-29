package pl.edu.pk.msala.contractfinder.ejb.exception;

import javax.ejb.ApplicationException;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 28.03.13
 * Time: 22:48
 */
@ApplicationException(rollback = true)
public class AppException extends Exception{

    public AppException() {

    }

    public AppException(String message) {
        super(message);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}
