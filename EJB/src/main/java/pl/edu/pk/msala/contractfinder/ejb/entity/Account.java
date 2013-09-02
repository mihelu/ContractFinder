package pl.edu.pk.msala.contractfinder.ejb.entity;

import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 29.03.13
 * Time: 19:46
 */
@Entity
@Table(name = "CF_ACCOUNT")
@NamedQueries({
        @NamedQuery(name = Account.ACC_GET_BY_LOGIN, query = "SELECT a FROM Account a WHERE a.login = ?1"),
        @NamedQuery(name = Account.ACC_FIND_ALL, query = "SELECT a FROM Account a")
})
public class Account implements Serializable {

    public static final String ACC_GET_BY_LOGIN = "accGetByLogin";
    public static final String ACC_FIND_ALL = "accFindAll";
    @Id
    @SequenceGenerator(name = "pk_seq", sequenceName = "acc_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_seq")
    @Column(name = "ACC_ID")
    private Long id;

    @Column(name = "ACC_LOGIN", unique = true, nullable = false)
    private String login;

    @Column(name = "ACC_PASSWORD", nullable = false)
    private String password;

    @Column(name = "ACC_EMAIL", nullable = false)
    private String email;

    @Column(name = "ACC_CREATE_DATE", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACC_USE_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACC_COM_ID")
    private Company company;

    @Column(name = "ACC_PERSONAL", nullable = false)
    private Boolean personal;

    @Column(name = "ACC_STATUS")
    private String status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "CF_ACCOUNT_ROLES",
            joinColumns = {
                    @JoinColumn(name = "ARO_ACC_ID", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "ARO_ROL_ID", nullable = false)
            })
    private Set<Role> roles;

    @PostLoad
    public void init() {

    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(Account.class).
                addValue(id).
                addValue(login).
                addValue(createDate).
                addValue(personal).
                addValue(company).
                addValue(user).
                addValue(roles).
                toString();
    }
}
