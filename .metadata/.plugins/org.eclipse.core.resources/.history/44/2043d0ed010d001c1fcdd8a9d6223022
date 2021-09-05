package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import connection.MySqlConnection;
import daoInterface.ClienteDao;
import pojo.Cliente;

public class MySqlImplClienteDao implements ClienteDao{

	private Connection conn = MySqlConnection.getConnection();


	public MySqlImplClienteDao() {
		createTable();
	}

	private void createTable() {

		String sql = "CREATE TABLE IF NOT EXISTS cliente(" + "id INT NOT NULL AUTO_INCREMENT,"
				+ "nombre VARCHAR(500)," + "email VARCHAR(500)," + "PRIMARY KEY (id))";

		try {
			conn.prepareStatement(sql).execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void create(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO cliente (nombre, email) VALUES (?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getEmail());
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (Exception e) {
			e.getMessage();
		}
		System.out.println("Insert cliente successfull");
	}

	@Override
	public boolean delete(int id) throws SQLException {
		String sql = "DELETE FROM cliente WHERE id=" + id;
		PreparedStatement ps;
		try {

			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.commit();
			System.out.println("True");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Cliente get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> getClientByBilling() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
