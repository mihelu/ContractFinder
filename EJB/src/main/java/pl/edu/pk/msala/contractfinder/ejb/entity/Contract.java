package pl.edu.pk.msala.contractfinder.ejb.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 18:42
 */
@Entity
@Table(name = "CF_CONTRACTS")
public class Contract implements Serializable{

    @Id
    @SequenceGenerator(name = "pk_seq", sequenceName = "con_id_seq", initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_seq")
    @Column(name = "CON_ID")
    private Long id;

    @Column(name = "CON_NAME",unique = false)
    private String name;

    @Column(name = "CON_DESCRIPTION",unique = false)
    private String description;

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
