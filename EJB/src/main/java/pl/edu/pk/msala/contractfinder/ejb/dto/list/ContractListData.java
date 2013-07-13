package pl.edu.pk.msala.contractfinder.ejb.dto.list;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 24.06.13
 * Time: 16:54
 */
public class ContractListData implements Serializable{

    private Long id;
    private String name;
    private Date publishStart;

    public ContractListData() {
    }

    public ContractListData(Long id, String name, Date publishDate) {
        this.id = id;
        this.name = name;
        this.publishStart = publishDate;
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

    public Date getPublishStart() {
        return publishStart;
    }

    public void setPublishStart(Date publishStart) {
        this.publishStart = publishStart;
    }
}
