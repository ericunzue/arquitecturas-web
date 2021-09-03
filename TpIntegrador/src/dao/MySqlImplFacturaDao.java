package dao;

import java.sql.SQLException;
import java.util.List;
import daoInterface.FacturaDao;
import pojo.Factura;

public class MySqlImplFacturaDao implements FacturaDao{

	@Override
	public void create(Factura entities) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Factura get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Factura> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
