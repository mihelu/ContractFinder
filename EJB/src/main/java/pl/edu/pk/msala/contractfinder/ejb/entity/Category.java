package pl.edu.pk.msala.contractfinder.ejb.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 27.04.13
 * Time: 12:26
 */
@Entity
@Table(name = "CF_CATEGORY")
@NamedQueries({
  @NamedQuery(name = Category.CAT_FIND_ALL_NOT_REMOVED, query = "SELECT c FROM Category c WHERE c.removed = FALSE"),
  @NamedQuery(name = Category.CAT_FIND_ALL, query = "SELECT c FROM Category c ORDER BY c.name")
})
public class Category implements Serializable{

    public static final String CAT_FIND_ALL_NOT_REMOVED = "catFindAllNotRemoved";
    public static final String CAT_FIND_ALL = "catFindAll";

    @Id
    @SequenceGenerator(name = "pk_seq", sequenceName = "cat_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_seq")
    @Column(name = "CAT_ID")
    private Long id;

    @Column(name="CAT_NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "CAT_REMOVED", nullable = false)
    private Boolean removed;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private List<Contract> contracts;

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

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
