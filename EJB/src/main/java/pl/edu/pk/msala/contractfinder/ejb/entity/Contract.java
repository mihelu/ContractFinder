package pl.edu.pk.msala.contractfinder.ejb.entity;

import org.hibernate.Hibernate;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
@NamedQueries({
        @NamedQuery(name = Contract.CON_FIND_ACCOUNT_CONTRACTS, query = "SELECT c FROM Contract c LEFT JOIN c.account a WHERE a.id = ?1")
})
public class Contract implements Serializable {

    public static final String CON_FIND_ACCOUNT_CONTRACTS = "conFindAccountContracts";
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

    @Column(name = "CON_PUBLISH_START", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date publishStart;

    @Column(name = "CON_PUBLISH_END", nullable = false, columnDefinition = "TIMESTAMP")
    private Date publishEnd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CON_ACC_ID", nullable = false)
    private Account account;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contract", cascade = CascadeType.ALL)
    private List<Offer> offers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "CF_CONTRACT_CATEGORY",
            joinColumns = {
                    @JoinColumn(name = "CC_CON_ID", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "CC_CAT_ID", nullable = false)
            })
    private List<Category> categories;

    public Contract() {
    }

    public Contract(Long id, String name, Date publishStart, Date publishEnd) {
        this.id = id;
        this.name = name;
        this.publishStart = publishStart;
        this.publishEnd = publishEnd;
    }

    @PostLoad
    public void init() {

    }

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

    public Date getPublishStart() {
        return publishStart;
    }

    public void setPublishStart(Date publishStart) {
        this.publishStart = publishStart;
    }

    public Date getPublishEnd() {
        return publishEnd;
    }

    public void setPublishEnd(Date publishEnd) {
        this.publishEnd = publishEnd;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
