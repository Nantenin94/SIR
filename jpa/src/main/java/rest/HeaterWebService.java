package rest;

import données.Heaterdonnées;
import données.Homedonnées;
import Model.Heater;
import Model.Home;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *@author Nantenin
 */
@Path("/heater")
public class HeaterWebService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Heater> getAllHeaters() {
        return Heaterdonnées.getHeaters();
    }

    @GET
    @Path("search/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Heater findById(@PathParam("id") int id) {
        return Heaterdonnées.getHeaterById(id);
    }

    @PUT
    @Path("create")
    @Produces({ MediaType.APPLICATION_JSON })
    public Boolean createHeater(String jsonStringHeater) {
        JSONObject jsonHeater = new JSONObject(jsonStringHeater);
        Heater heater = new Heater();
        heater.setPower(jsonHeater.getInt("power"));
        heater.setConsomation(jsonHeater.getInt("consomation"));
        heater.setResidence(Homedonnées.getHomeById(jsonHeater.getInt("idHome")));
        return Heaterdonnées.createHeater(heater);
    }
}
