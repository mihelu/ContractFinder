package pl.edu.pk.msala.contractfinder.ejb.service;

import pl.edu.pk.msala.contractfinder.ejb.entity.Category;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;
import pl.edu.pk.msala.contractfinder.ejb.manager.CategoryManager;

import javax.ejb.*;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 16.08.13
 * Time: 15:24
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class CategoryService {

    @EJB
    private CategoryManager categoryManager;

    public Category createCategory(Category category) throws AppRollbackException {
        try {
        return categoryManager.createCategory(category);
        }catch (Exception e) {
            throw new AppRollbackException("Nie udało się dodać kategorii!");
        }
    }

    public Category createCategory(String name) throws AppRollbackException {
        try {
            Category category = new Category();
            category.setName(name);
            category.setRemoved(false);
            return categoryManager.createCategory(category);
        }catch (Exception e) {
            throw new AppRollbackException("Nie udało się dodać kategorii!");
        }
    }

    public void changeCategoryStatus(Long id, boolean removed) {
        Category category = categoryManager.getCategory(id);
        category.setRemoved(removed);
        categoryManager.modifyCategory(category);
    }
}
