package pl.edu.pk.msala.contractfinder.ejb.manager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 27.04.13
 * Time: 12:57
 */
@Stateless
@LocalBean
public class ContractManager {

    @PersistenceContext(unitName = "ContractFinderDB")
    private EntityManager entityManager;
}
