package données;

import Model.Home;
import Model.Person;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @author Nantenin
 */
public class Homedonnées {
    public static List<Home> getHomes(){
        return Managerdonnées.getManagerdonnéesInstance().createQuery("Select h From Home h", Home.class).getResultList();
    }

    public static Home getHomeById(int id){
        return Managerdonnées.getManagerdonnéesInstance().createQuery("Select h From Home h where h.id=:id", Home.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public static Boolean createHome(Home home){
        EntityTransaction tx = Managerdonnées.getManagerdonnéesInstance().getTransaction();
        tx.begin();
        try {
            Managerdonnées.getManagerdonnéesInstance().persist(home);
        } catch (Exception e) {
            return false;
        }
        tx.commit();
        return true;
    }
}
