import javax.ejb.Stateless;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 17.03.13
 * Time: 20:51
 */
@Stateless
public class TestService {

    public String test() {
        return "TEST_FROM_EJB";
    }
}
