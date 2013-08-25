package pl.edu.pk.msala.contractfinder.ejb.facade;

import pl.edu.pk.msala.contractfinder.ejb.entity.Category;
import pl.edu.pk.msala.contractfinder.ejb.exception.AppRollbackException;

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

    public List<Category> getAllCategories();

    public void changeCategoryStatus(Long id, boolean removed);

    public void createCategory(String name) throws AppRollbackException;
}
