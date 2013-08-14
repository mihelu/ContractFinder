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

    public List<Category> findAllCategories() {
        return entityManager.createNamedQuery(Category.CAT_FIND_ALL, Category.class).getResultList();
    }
}
