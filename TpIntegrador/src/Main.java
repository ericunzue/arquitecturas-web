
import java.sql.SQLException;

import daoInterface.ClienteDao;
import daoInterface.FacturaDao;
import daoInterface.FacturaProductoDao;
import daoInterface.ProductoDao;
import factory.Factory;


public class Main {
	public static void main(String[] args) throws SQLException {



		ClienteDao clienteDao =   Factory.getConcreteFactory(Factory.MYSQL).getClienteDao();
		FacturaDao facturaDao =   Factory.getConcreteFactory(Factory.MYSQL).getFacturaDao();
		ProductoDao productoDao =   Factory.getConcreteFactory(Factory.MYSQL).getProductoDao();
		FacturaProductoDao facturaProductoDao =   Factory.getConcreteFactory(Factory.MYSQL).getFacturaProductoDao();
//		Factura fac1 = new Factura(1, 2);
//		facturaDao.create(fac1);
//		productoDao.createProductos();







	}

}
