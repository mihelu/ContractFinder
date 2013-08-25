package pl.edu.pk.msala.contractfinder.ejb.manager;

import pl.edu.pk.msala.contractfinder.ejb.entity.Category;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 13.08.13
 * Time: 22:29
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class CategoryManager {

    @PersistenceContext(unitName = "ContractFinderDB")
    private EntityManager entityManager;

    public List<Category> getCategoriesDict() {
        return entityManager.createNamedQuery(Category.CAT_FIND_ALL_NOT_REMOVED, Category.class).getResultList();
    }

    public List<Category> findAllCategories() {
        return entityManager.createNamedQuery(Category.CAT_FIND_ALL, Category.class).getResultList();
    }

    public Category getCategory(long id) {
        return entityManager.find(Category.class, id);
    }

    public void modifyCategory(Category category) {
        entityManager.merge(category);
        entityManager.flush();
    }

    public Category createCategory(Category category) {
        Category created = entityManager.merge(category);
        entityManager.flush();
        return created;
    }
}
