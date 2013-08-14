package pl.edu.pk.msala.contractfinder.ejb.dto.find;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 23.06.13
 * Time: 16:25
 */
public class ContractFindData implements Serializable{

    private String query;
    private List<Long> categories;
    private boolean searchInFinished;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public boolean isSearchInFinished() {
        return searchInFinished;
    }

    public void setSearchInFinished(boolean searchInFinished) {
        this.searchInFinished = searchInFinished;
    }
}
