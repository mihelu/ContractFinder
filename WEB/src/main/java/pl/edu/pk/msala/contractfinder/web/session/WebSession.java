package pl.edu.pk.msala.contractfinder.web.session;

import org.joda.time.DateTime;
import pl.edu.pk.msala.contractfinder.ejb.entity.Role;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 29.04.13
 * Time: 17:16
 */
public class WebSession implements Serializable {

    private Long accountId;
    private String sessionId;
    private String accessToken;
    private DateTime createDate;
    private Set<Role> roles;

    public WebSession(Long accountId, String sessionId, String accessToken, Set<Role> roles) {
        this.accountId = accountId;
        this.sessionId = sessionId;
        this.accessToken = accessToken;
        this.createDate = new DateTime();
        this.roles = roles;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
