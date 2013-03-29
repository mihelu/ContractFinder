package pl.edu.pk.msala.contractfinder.web.rest;

import pl.edu.pk.msala.contractfinder.ejb.facade.TestFacadeRemote;
import pl.edu.pk.msala.contractfinder.web.locator.FacadeLocator;
import pl.edu.pk.msala.contractfinder.web.service.TestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 24.03.13
 * Time: 16:40
 */
@Path("/test")
public class Test {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Osoba test() {
        TestService.test();
        return new Osoba("TEST", "TEST");
    }

    class Osoba {

        private String name;
        private String surname;

        Osoba(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }
    }

}
