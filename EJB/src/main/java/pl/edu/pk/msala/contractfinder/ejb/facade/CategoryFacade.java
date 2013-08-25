package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.entity.Category;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;
import pl.edu.pk.msala.contractfinder.ejb.manager.CategoryManager;
import pl.edu.pk.msala.contractfinder.ejb.service.CategoryService;

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
    @EJB
    private CategoryService categoryService;

    @Override
    public List<Category> getCategoriesDict() {
        return categoryManager.getCategoriesDict();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryManager.findAllCategories();
    }

    @Override
    public void changeCategoryStatus(Long id, boolean removed) {
        categoryService.changeCategoryStatus(id, removed);
    }

    @Override
    public void createCategory(String name) throws AppRollbackException {
        categoryService.createCategory(name);
    }
}
