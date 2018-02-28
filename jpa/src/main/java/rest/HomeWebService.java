package rest;

import données.Homedonnées;
import données.Persondonnées;
import Model.Home;
import Model.Person;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *@author Nantenin
 */
@Path("/home")
public class HomeWebService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Home> getAllHomes() {
        return Homedonnées.getHomes();
    }

    @GET
    @Path("search/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Home findById(@PathParam("id") int id) {
        return Homedonnées.getHomeById(id);
    }

    @PUT
    @Path("create")
    @Produces({ MediaType.APPLICATION_JSON })
    public Boolean createHome(String jsonStringHome) {
        JSONObject jsonHome = new JSONObject(jsonStringHome);
        Home home = new Home();
        home.setSize(jsonHome.getInt("size"));
        home.setNbRoom(jsonHome.getInt("nbRoom"));
        return Homedonnées.createHome(home);
    }
}

