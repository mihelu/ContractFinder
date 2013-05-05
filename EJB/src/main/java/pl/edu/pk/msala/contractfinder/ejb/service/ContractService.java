package pl.edu.pk.msala.contractfinder.ejb.service;

import pl.edu.pk.msala.contractfinder.ejb.manager.ContractManager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 27.04.13
 * Time: 12:58
 */
@Stateless
@LocalBean
public class ContractService {

    @EJB
    private ContractManager contractManager;
}
