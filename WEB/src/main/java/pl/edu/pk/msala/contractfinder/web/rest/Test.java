package pl.edu.pk.msala.contractfinder.web.rest;

import com.google.common.collect.Lists;
import pl.edu.pk.msala.contractfinder.web.service.TestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    public List<Osoba> test() {
        //TestService.test();
        List<Osoba> osoby = Lists.newArrayList(new Osoba("TEST", "TEST"), new Osoba("TEST1", "TEST1"));
        return osoby;
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
