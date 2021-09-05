package factory;

import dao.MySqlImplClienteDao;
import dao.MySqlImplFacturaDao;
import dao.MySqlImplFacturaProductoDao;
import dao.MySqlImplProductoDao;
import daoInterface.ClienteDao;
import daoInterface.FacturaDao;
import daoInterface.FacturaProductoDao;
import daoInterface.ProductoDao;

public class MySqlFactory extends Factory {

	@Override
	public ClienteDao getClienteDao() {

		return new MySqlImplClienteDao();
	}

	@Override
	public FacturaDao getFacturaDao() {

		return new MySqlImplFacturaDao();
	}

	@Override
	public ProductoDao getProductoDao() {

		return new MySqlImplProductoDao();
	}

	@Override
	public FacturaProductoDao getFacturaProductoDao() {

		return new MySqlImplFacturaProductoDao();
	}




}
