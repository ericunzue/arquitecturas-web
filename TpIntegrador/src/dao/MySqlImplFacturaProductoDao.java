package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connection.MySqlConnection;
import daoInterface.FacturaProductoDao;
import pojo.FacturaProducto;

public class MySqlImplFacturaProductoDao implements FacturaProductoDao {


	Connection conn = MySqlConnection.getConnection();

	public MySqlImplFacturaProductoDao() {
		this.createTable();

	}

	private void createTable() {
		String create = "CREATE TABLE IF NOT EXISTS facturaProducto (idFactura INT NOT NULL,"
				+ "idProducto INT NOT NULL,"
				+ "cantidad INT (11),"
				+ "PRIMARY KEY (idFactura, idProducto),"
				+ "FOREIGN KEY (idFactura) REFERENCES factura (idFactura),"
				+ "FOREIGN KEY (idProducto) REFERENCES producto(idProducto))";

		try {
			conn.prepareStatement(create).execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void create(FacturaProducto entities) throws SQLException {


	}



	@Override
	public FacturaProducto get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FacturaProducto> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int a, int b) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}





}
