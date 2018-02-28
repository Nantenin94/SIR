package données;

import Model.Heater;
import Model.Home;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @author Nantenin .
 */
public class Heaterdonnées {
    public static List<Heater> getHeaters(){
        return Managerdonnées.getManagerdonnéesInstance().createQuery("Select h From Heater h", Heater.class).getResultList();
    }

    public static Heater getHeaterById(int id){
        return Managerdonnées.getManagerdonnéesInstance().createQuery("Select h From Heater h where h.id=:id", Heater.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public static Boolean createHeater(Heater heater){
        EntityTransaction tx = Managerdonnées.getManagerdonnéesInstance().getTransaction();
        tx.begin();
        try {
            Managerdonnées.getManagerdonnéesInstance().persist(heater);
        } catch (Exception e) {
            return false;
        }
        tx.commit();
        return true;
    }
}

