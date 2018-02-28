package rest;

import données.ElectronicDevicedonnées;
import données.Heaterdonnées;
import données.Homedonnées;
import données.Persondonnées;
import Model.ElectronicDevice;
import Model.Heater;
import Model.Person;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Nantenin
 */
@Path("/electronicdevice")
public class ELectronicWebService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ElectronicDevice> getAllEd() {
        return ElectronicDevicedonnées.getHeaters();
    }

    @GET
    @Path("search/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public ElectronicDevice findById(@PathParam("id") int id) {
        return ElectronicDevicedonnées.getHeaterById(id);
    }

    @PUT
    @Path("create")
    @Produces({ MediaType.APPLICATION_JSON })
    public Boolean createEd(String jsonStringEd) {
        JSONObject jsonEd = new JSONObject(jsonStringEd);
        ElectronicDevice ed = new ElectronicDevice();
        ed.setConsomation(jsonEd.getInt("consumption"));
        ed.setFonction(jsonEd.getString("fonction"));
        ed.setResidence(Homedonnées.getHomeById(jsonEd.getInt("idHome")));
        return ElectronicDevicedonnées.createElectronicDevice(ed);
    }
}
