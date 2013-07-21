package pl.edu.pk.msala.contractfinder.ejb.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 24.06.13
 * Time: 17:02
 */
@Entity
@Table(name = "CF_OFFER")
@NamedQueries({
        @NamedQuery(name = Offer.OFF_FIND_CONTRACT_OFFERS, query = "SELECT off FROM Offer off LEFT JOIN off.contract c WHERE c.id = ?1"),
        @NamedQuery(name = Offer.OFF_FIND_ACCOUNT_OFFERS, query = "SELECT off FROM Offer off LEFT JOIN off.account a WHERE a.id = ?1")
})
public class Offer implements Serializable{

    public static final String OFF_FIND_CONTRACT_OFFERS = "offFindContractOffers";
    public static final String OFF_FIND_ACCOUNT_OFFERS = "offFindAccountOffers";
    @Id
    @SequenceGenerator(name = "pk_seq", sequenceName = "off_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_seq")
    @Column(name = "OFF_ID")
    private Long id;

    @Column(name = "OFF_COMMENT", nullable = true)
    private String comment;

    @Column(name = "OFF_DAYS")
    private Integer days;

    @Column(name = "OFF_OFFER_PRICE", nullable = false)
    private BigDecimal offerPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OFF_CON_ID", nullable = false)
    private Contract contract;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OFF_ACC_ID", nullable = false)
    private Account account;

    @PostLoad
    public void init() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
