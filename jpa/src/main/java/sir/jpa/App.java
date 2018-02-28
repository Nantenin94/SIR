package sir.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Model.ElectronicDevice;
import Model.Heater;
import Model.Home;
import Model.Person;
import sir.jpa.App;

/**
 * Hello world!
 *
 */
public class App {
	private EntityManager manager;

	public App(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
		EntityManager manager = factory.createEntityManager();
		App jpaTest = new App(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		try {
			jpaTest.createTheSituation();

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		jpaTest.listPerson();
		jpaTest.getAllResidences();
		jpaTest.findAllHeaters();
		jpaTest.findHeaterById(1);

		manager.close();
		factory.close();
	}

	public void createTheSituation() {
		/* ------- HOME ------- */
		Collection<Home> ListeMaison1 = new ArrayList<Home>();
		Home maison2 = new Home();
		maison2.setNbRoom(6);
		maison2.setSize(150);
		ListeMaison1.add(maison2);

		Home maison3 = new Home();
		maison3.setNbRoom(25);
		maison3.setSize(1000);
		ListeMaison1.add(maison3);

		Collection<Home> listeMaison2 = new ArrayList<Home>();
		Home maison4 = new Home();
		maison4.setNbRoom(6);
		maison4.setSize(150);
		listeMaison2.add(maison4);

		/* ------- PERSON ------- */
		Person p1 = new Person();
		p1.setFirstname("Aly laye");
		p1.setLastname("CONDE");
		p1.setEmail("alylaye93@gmail.com");
		p1.setResidences(ListeMaison1);

		Person p2 = new Person();
		p2.setFirstname("Nantenin");
		p2.setLastname("DOUMBIA");
		p2.setEmail("donnabeija94@gmail.com");
		p2.setResidences(ListeMaison1);

		Person p3 = new Person();
		p3.setFirstname("Donna");
		p3.setLastname("Beija");
		p3.setEmail("doumbianantenin@yahoo.com ");
		p3.setResidences(listeMaison2);

		Person p4 = new Person();
		p4.setFirstname("Louceny");
		p4.setLastname("Kankou");
		p4.setEmail("loucenykankouc@gmail.com ");
		p4.setResidences(listeMaison2);

		/* ------- HEATER ------- */
		Collection<Heater> listeChauffage1 = new ArrayList<Heater>();
		Heater h1 = new Heater();
		h1.setConsomation(50);
		h1.setPower(1000);
		listeChauffage1.add(h1);

		Collection<Heater> listeChauffage2 = new ArrayList<Heater>();
		Heater h2 = new Heater();
		h2.setConsomation(100);
		h2.setPower(2000);
		listeChauffage2.add(h2);

		Collection<Heater> listeChauffage3 = new ArrayList<Heater>();
		Heater h3 = new Heater();
		h3.setConsomation(75);
		h3.setPower(1800);
		listeChauffage3.add(h3);

		h1.setResidence(maison2);
		h2.setResidence(maison3);
		h3.setResidence(maison4);

		maison2.setHeaters(listeChauffage1);
		maison3.setHeaters(listeChauffage2);
		maison4.setHeaters(listeChauffage3);

		/* ------- ED ------- */
		Collection<ElectronicDevice> Appareilselectroniques1 = new ArrayList<ElectronicDevice>();
		ElectronicDevice ed1 = new ElectronicDevice();
		ed1.setConsomation(100);
		ed1.setFonction("Robot de cuisine");
		ed1.setResidence(maison2);

		ElectronicDevice ed2 = new ElectronicDevice();
		ed2.setConsomation(20);
		ed2.setFonction("Domotique");
		ed2.setResidence(maison2);

		Collection<ElectronicDevice> Appareilselectroniques2 = new ArrayList<ElectronicDevice>();
		ElectronicDevice ed3 = new ElectronicDevice();
		ed3.setConsomation(250);
		ed3.setFonction("Mixeur");
		ed3.setResidence(maison3);

		ElectronicDevice ed4 = new ElectronicDevice();
		ed4.setConsomation(50);
		ed4.setFonction("CamÃ©ras");
		ed4.setResidence(maison3);

		Collection<ElectronicDevice> Appareilselectroniques3 = new ArrayList<ElectronicDevice>();
		ElectronicDevice ed5 = new ElectronicDevice();
		ed5.setConsomation(750);
		ed5.setFonction("TV");
		ed5.setResidence(maison4);

		Appareilselectroniques1.add(ed1);
		Appareilselectroniques1.add(ed2);
		Appareilselectroniques2.add(ed3);
		Appareilselectroniques2.add(ed4);
		Appareilselectroniques3.add(ed5);

		maison2.setElectronicDevices(Appareilselectroniques1);
		maison3.setElectronicDevices(Appareilselectroniques2);
		maison4.setElectronicDevices(Appareilselectroniques3);

		manager.persist(maison2);
		manager.persist(maison3);
		manager.persist(maison4);
		manager.persist(h1);
		manager.persist(h2);
		manager.persist(h3);
		manager.persist(ed1);
		manager.persist(ed2);
		manager.persist(ed3);
		manager.persist(ed4);
		manager.persist(ed5);
		manager.persist(p1);
		manager.persist(p2);
		manager.persist(p3);
		manager.persist(p4);

	}

	private void listPerson() {
		List<Person> resultList = manager.createQuery("Select p From Person p", Person.class).getResultList();
		System.out.println("num of person:" + resultList.size());
		for (Person next : resultList) {
			System.out.println("next person: " + next);
		}
	}

	public List<Person> getPersons() {
		return manager.createQuery("Select p From Person p", Person.class).getResultList();
	}

	public void getAllResidences() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();

		Root<Home> from = criteriaQuery.from(Home.class);
		CriteriaQuery<Object> select = criteriaQuery.select(from);

		TypedQuery<Object> typedQuery = manager.createQuery(select);
		List<Object> resultList = typedQuery.getResultList();

		for (Object o : resultList) {
			System.out.println("CriteriaQuery ---> ID DES RESIDENCES : " + ((Home) o).getIdHome());
		}
	}

	public void findAllHeaters() {
		List<Heater> results = manager.createNamedQuery("Heater.findAll").getResultList();
		for (Heater h : results) {
			System.out.println("NamedQuery ---> CHAUFFAGE : " + h.getIdHeater());
		}
	}

	public void findHeaterById(int id) {
		Object result = manager.createNamedQuery("Heater.findById").setParameter("id", id).getSingleResult();

		System.out.println("NamedQuery ---> CHAUFFAGE POWER : " + ((Heater) result).getPower());
	}

}
