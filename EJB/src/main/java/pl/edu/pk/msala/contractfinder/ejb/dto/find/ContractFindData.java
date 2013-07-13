package pl.edu.pk.msala.contractfinder.ejb.dto.find;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 23.06.13
 * Time: 16:25
 */
public class ContractFindData implements Serializable{

    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
