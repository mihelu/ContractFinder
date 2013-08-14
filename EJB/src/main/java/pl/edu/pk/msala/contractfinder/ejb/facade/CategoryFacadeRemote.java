package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.entity.Category;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 13.08.13
 * Time: 22:34
 */
@Remote
public interface CategoryFacadeRemote {

    public List<Category> getCategoriesDict();
}
