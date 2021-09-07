package main;


import java.sql.SQLException;
import java.util.List;

import daoInterface.ClienteDao;
import daoInterface.FacturaDao;
import daoInterface.FacturaProductoDao;
import daoInterface.ProductoDao;
import factory.Factory;
import pojo.Cliente;

public class Main {

	public static void main(String[] args) throws SQLException {

		//Crea una Factory de MySQL
		Factory MySqlFactory = Factory.getConcreteFactory(Factory.MYSQL);


		ClienteDao clienteDao = MySqlFactory.getClienteDao();
		ProductoDao productoDao = MySqlFactory.getProductoDao();
		FacturaDao facturaDao = MySqlFactory.getFacturaDao();
		FacturaProductoDao facturaProductoDao = MySqlFactory.getFacturaProductoDao();

		//Crea las tablas
		clienteDao.initializer();
		productoDao.initializer();
		facturaDao.initializer();
		facturaProductoDao.initializer();

		//Llena las tablas
//		clienteDao.fillCustomers();
//		productoDao.fillProducts();
//		facturaDao.fillBills();
//		facturaProductoDao.fillBillsProducts();

		//PUNTO 3
		System.out.println("Punto 3");
		System.out.println("Producto que más recaudó: " + productoDao.topBillingProduct());

		System.out.println("<--------------------------------->");

		//PUNTO 4
		System.out.println("Punto 4");
		List<Cliente> clientesPorFacturacion = clienteDao.getClientByBilling();

		for (Cliente cliente : clientesPorFacturacion) {
			System.out.println(cliente);
		}


	}

}
