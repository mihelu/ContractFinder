package pl.edu.pk.msala.contractfinder.ejb.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 15:44
 */
@XmlRootElement
public class AccountData implements Serializable{

    private String login;
    private String password;

    public AccountData() {
    }

    public AccountData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
