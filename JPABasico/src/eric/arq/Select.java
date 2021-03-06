package eric.arq;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import eric.arq.dao.Persona;

public class Select {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Persona j = em.find(Persona.class,1);
		System.out.println(j);
		@SuppressWarnings("unchecked")
		List<Persona> personas = em.createQuery("SELECT p FROM Persona p").getResultList();
		personas.forEach(p-> System.out.println(p));
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
