package données;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Nantenin
 */
public class Managerdonnées {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
    private static EntityManager MANAGER = factory.createEntityManager();

    public static EntityManager getManagerdonnéesInstance(){
        return MANAGER;
    }


}

