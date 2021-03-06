package factory;

import java.lang.reflect.InvocationTargetException;
import daoInterface.ClienteDao;
import daoInterface.FacturaDao;
import daoInterface.FacturaProductoDao;
import daoInterface.ProductoDao;

public abstract class Factory {
	public static final String MYSQL = "factory.MySqlFactory";
	public static final String DERBY_JDBC = "Derby_JDBC_Sin_Implementar";
	public static final String JPA_HIBERNATE = "Jpa_Hibernate_Sin_Implementar";

	public static Factory getConcreteFactory(String className) {

		Factory dao = null;

		try {
			dao = (Factory) Class.forName(className).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}


		return dao;

	}


	public abstract ClienteDao getClienteDao();
	public abstract FacturaDao getFacturaDao();
	public abstract ProductoDao getProductoDao();
	public abstract FacturaProductoDao getFacturaProductoDao();
}
