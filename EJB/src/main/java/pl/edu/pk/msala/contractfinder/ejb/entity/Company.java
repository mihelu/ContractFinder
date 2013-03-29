package pl.edu.pk.msala.contractfinder.ejb.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 29.03.13
 * Time: 19:46
 */
@Entity
@Table(name = "CF_COMPANIES")
public class Company {

    @Id
    @SequenceGenerator(name = "pk_seq", sequenceName = "com_id_seq", initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_seq")
    @Column(name = "COM_ID")
    private Long id;

    @Column(name = "COM_NAME")
    private String name;

    @Column(name = "COM_DESCRIPTION")
    private String description;

    @OneToOne(mappedBy = "company")
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
