package eric.arq;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import eric.arq.dao.Direccion;
import eric.arq.dao.Persona;

public class Insert {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Direccion d = new Direccion("Bolivar", "Roca 168");
		em.persist(d);
		Persona j = new Persona(1, "Lula", 28,d);
		Persona a = new Persona(2, "Pedro", 30,d);
		Persona b = new Persona(3, "Juan", 10,d);
		em.persist(j);
		em.persist(a);
		em.persist(b);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
