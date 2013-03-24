package test;

import javax.ejb.Remote;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 24.03.13
 * Time: 17:10
 */
@Remote
public interface TestFacadeRemote {

    public String test();
}
