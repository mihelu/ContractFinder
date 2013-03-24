import pl.edu.pk.msala.contractfinder.locator.FacadeLocator;
import test.TestFacadeRemote;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 17.03.13
 * Time: 21:10
 */
public class Test {

    public static void main(String[] args) {
        TestFacadeRemote facade = FacadeLocator.getFacade(TestFacadeRemote.class);
        System.out.println(facade.test());
    }
}
