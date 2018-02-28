package rest;

import données.Homedonnées;
import données.Persondonnées;
import Model.Home;
import Model.Person;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Nantenin
 */
@Path("/person")
public class PersonWebService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons() {
        return Persondonnées.getPersons();
    }

    @GET
    @Path("search/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Person findById(@PathParam("id") int id) {
        return Persondonnées.getPersonById(id);
    }

    @PUT
    @Path("create")
    @Produces({ MediaType.APPLICATION_JSON })
    public Boolean createPerson(String jsonStringPerson) {
        List<Home> residences = new ArrayList<Home>();

        JSONObject jsonPerson = new JSONObject(jsonStringPerson);
        JSONArray jsonResidences = jsonPerson.getJSONArray("homesChecked");
        for(int i = 0; i < jsonResidences.length(); i++){
            Home h = Homedonnées.getHomeById(jsonResidences.getJSONObject(i).getInt("idHome"));
            residences.add(h);
        }
        Person person = new Person();
        person.setFirstname(jsonPerson.getString("firstname"));
        person.setLastname(jsonPerson.getString("lastname"));
        person.setEmail(jsonPerson.getString("email"));
        person.setResidences(residences);
        return Persondonnées.createPerson(person);
    }
}
