package pl.edu.pk.msala.contractfinder.ejb.entity;

import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;

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
        @NamedQuery(name = Offer.OFF_FIND_ACCOUNT_OFFERS, query = "SELECT off FROM Offer off LEFT JOIN off.account a WHERE a.id = ?1"),
        @NamedQuery(name = Offer.OFF_GET_ACCOUNT_CONTRACT_OFFER, query = "SELECT off FROM Offer off LEFT JOIN off.account a LEFT JOIN off.contract c WHERE a.id = ?1 AND c.id = ?2")
})
@SQLDelete(sql = "UPDATE CF_OFFER SET OFF_REMOVED = TRUE WHERE OFF_ID = ?")
public class Offer implements Serializable{

    public static final String OFF_FIND_CONTRACT_OFFERS = "offFindContractOffers";
    public static final String OFF_FIND_ACCOUNT_OFFERS = "offFindAccountOffers";
    public static final String OFF_GET_ACCOUNT_CONTRACT_OFFER = "offGetAccountContractOffer";
    @Id
    @SequenceGenerator(name = "pk_seq", sequenceName = "off_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_seq")
    @Column(name = "OFF_ID")
    private Long id;

    @Column(name = "OFF_COMMENT", nullable = true)
    private String comment;

    @Column(name = "OFF_YEARS")
    private Integer years;

    @Column(name = "OFF_MONTHS")
    private Integer months;

    @Column(name = "OFF_DAYS")
    private Integer days;

    @Column(name = "OFF_HOURS")
    private Integer hours;

    @Column(name = "OFF_OFFER_PRICE", nullable = false)
    private BigDecimal offerPrice;

    @Column(name = "OFF_REMOVED", nullable = true)
    private Boolean removed;

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

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
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
