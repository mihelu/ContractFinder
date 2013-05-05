package pl.edu.pk.msala.contractfinder.ejb.entity;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 27.04.13
 * Time: 14:56
 */
@Entity
@Table(name="CF_ROLE")
public class Role implements Serializable{

    @Id
    @SequenceGenerator(name = "pk_seq", sequenceName = "rol_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_seq")
    @Column(name = "ROL_ID")
    private Long id;

    @Column(name="ROL_NAME", unique = true, nullable = false)
    private String name;

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

    @Override
    public String toString() {
        return Objects.toStringHelper(Role.class).
                addValue(id).
                addValue(name).
                toString();
    }


}
