package pl.edu.pk.msala.contractfinder.ejb.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 29.03.13
 * Time: 19:46
 */
@Entity
@Table(name = "CF_ACCOUNTS")
public class Account {

    @Id
    @SequenceGenerator(name = "pk_seq", sequenceName = "acc_id_seq", initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_seq")
    @Column(name = "ACC_ID")
    private Long id;

    @Column(name = "ACC_LOGIN", unique = true, nullable = false)
    private String login;

    @Column(name = "ACC_PASSWORD", nullable = false)
    private String password;

    @Column(name = "ACC_CREATE_DATE", nullable = false)
    private Date createDate;

    @OneToOne
    @JoinColumn(name = "ACC_USE_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "ACC_COM_ID")
    private Company company;

    @Column(name = "ACC_PERSONAL", nullable = false)
    private Boolean personal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Boolean getPersonal() {
        return personal;
    }

    public void setPersonal(Boolean personal) {
        this.personal = personal;
    }
}
