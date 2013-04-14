package pl.edu.pk.msala.contractfinder.ejb.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 25.03.13
 * Time: 22:23
 */
@Entity
@Table(name = "CF_USERS")
public class User implements Serializable{

    @Id
    @SequenceGenerator(name = "pk_seq", sequenceName = "use_id_seq", initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_seq")
    @Column(name = "USE_ID")
    private Long id;

    @Column(name = "USE_FIRSTNAME")
    private String firstName;

    @Column(name = "USE_LASTNAME")
    private String lastName;

    @OneToOne(mappedBy = "user")
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
