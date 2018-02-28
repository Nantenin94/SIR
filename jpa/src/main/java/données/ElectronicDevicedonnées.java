/**
 * 
 */
/**
 * @author Nantenin
 *
 */
package données;

import Model.ElectronicDevice;
import Model.Heater;

import javax.persistence.EntityTransaction;
import java.util.List;

public class ElectronicDevicedonnées {
    public static List<ElectronicDevice> getHeaters(){
        return Managerdonnées.getManagerdonnéesInstance().createQuery("Select e From ElectronicDevice e", ElectronicDevice.class).getResultList();
    }

    public static ElectronicDevice getHeaterById(int id){
        return Managerdonnées.getManagerdonnéesInstance().createQuery("Select e From ElectronicDevice e where e.id=:id", ElectronicDevice.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public static Boolean createElectronicDevice(ElectronicDevice ed){
        EntityTransaction tx = Managerdonnées.getManagerdonnéesInstance().getTransaction();
        tx.begin();
        try {
            Managerdonnées.getManagerdonnéesInstance().persist(ed);
        } catch (Exception e) {
            return false;
        }
        tx.commit();
        return true;
    }
}
