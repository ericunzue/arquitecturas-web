package factory;

import dao.MySqlImplClienteDao;
import dao.MySqlImplFacturaDao;
import dao.MySqlImplProductoDao;
import daoInterface.ClienteDao;
import daoInterface.FacturaDao;
import daoInterface.ProductoDao;

public class MySqlFactory extends Factory {

	@Override
	public ClienteDao getClienteDao() {
		// TODO Auto-generated method stub
		return new MySqlImplClienteDao();
	}

	@Override
	public FacturaDao getFacturaDao() {
		// TODO Auto-generated method stub
		return new MySqlImplFacturaDao();
	}

	@Override
	public ProductoDao getProducotDao() {
		// TODO Auto-generated method stub
		return new MySqlImplProductoDao();
	}




}