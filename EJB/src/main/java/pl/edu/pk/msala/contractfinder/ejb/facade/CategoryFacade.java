package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.entity.Category;
import pl.edu.pk.msala.contractfinder.ejb.manager.CategoryManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 13.08.13
 * Time: 22:35
 */
@Stateless
public class CategoryFacade implements CategoryFacadeRemote {

    @EJB
    private CategoryManager categoryManager;

    @Override
    public List<Category> getCategoriesDict() {
        return categoryManager.findAllCategories();
    }
}
