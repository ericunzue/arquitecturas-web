import java.sql.SQLException;

import factory.Factory;
import pojo.Cliente;

public class Main {
	public static void main(String[] args) throws SQLException {

		Cliente c1 = new Cliente("José", "Gonzalez");

		Factory.getConcreteFactory(Factory.MYSQL).getClienteDao().create(c1);
		Factory.getConcreteFactory(Factory.MYSQL).getClienteDao().delete(5);


	}

}
