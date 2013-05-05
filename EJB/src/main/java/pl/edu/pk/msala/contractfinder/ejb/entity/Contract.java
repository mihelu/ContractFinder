package pl.edu.pk.msala.contractfinder.ejb.entity;

import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 14.04.13
 * Time: 18:42
 */
@Indexed
@Entity
@Table(name = "CF_CONTRACT")
public class Contract implements Serializable {

    @DocumentId
    @Id
    @SequenceGenerator(name = "pk_seq", sequenceName = "con_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_seq")
    @Column(name = "CON_ID")
    private Long id;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "CON_NAME", unique = false)
    private String name;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "CON_DESCRIPTION", unique = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "CF_CONTRACT_CATEGORY",
            joinColumns = {
                    @JoinColumn(name = "CC_CON_ID", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "CC_CAT_ID", nullable = false)
            })
    private List<Category> categories;

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
