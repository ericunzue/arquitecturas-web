package entityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
@WebListener
public class EMF implements ServletContextListener {
		private static EntityManagerFactory EMF;

		public static EntityManager createEntityManager() {
				if (EMF == null) {
						throw new IllegalStateException("Context is not initialized yet.");
				}
				return EMF.createEntityManager();
		}

		@Override
		public  void contextInitialized(ServletContextEvent sce) {
				EMF= Persistence.createEntityManagerFactory("Facultad");
				System.out.println("EntityManagerFactory Inicializado");
		}
		@Override
		public void contextDestroyed(ServletContextEvent sce) {
				if(EMF!=null) {

						EMF.close();
						System.out.println("EntityManagerFactory cerrado");
				}
		}
		
	
}
