package données;

import Model.Person;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 @author Nantenin
 */
public class Persondonnées {
    public static List<Person> getPersons(){
        return Managerdonnées.getManagerdonnéesInstance().createQuery("Select p From Person p", Person.class).getResultList();
    }

    public static Person getPersonById(int id){
        return Managerdonnées.getManagerdonnéesInstance().createQuery("Select p From Person p where p.id=:id", Person.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public static Boolean createPerson(Person person){
        EntityTransaction tx = Managerdonnées.getManagerdonnéesInstance().getTransaction();
        tx.begin();
        try {
            Managerdonnées.getManagerdonnéesInstance().persist(person);
        } catch (Exception e) {
            return false;
        }
        tx.commit();
        return true;
    }
}
