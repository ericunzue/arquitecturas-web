package dao;

import java.sql.SQLException;
import java.util.List;
import daoInterface.ProductoDao;
import pojo.Producto;

public class MySqlImplProductoDao implements ProductoDao {

	@Override
	public void create(Producto entities) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Producto get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto getProductHighestCollection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
