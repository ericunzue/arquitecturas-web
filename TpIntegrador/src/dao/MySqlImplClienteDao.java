package dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import connection.MySqlConnection;
import daoInterface.ClienteDao;
import pojo.Cliente;


public class MySqlImplClienteDao implements ClienteDao{

	private Connection conn = MySqlConnection.getConnection();


	public MySqlImplClienteDao() {

	}

	public void initializer() {

		final String sql = "CREATE TABLE IF NOT EXISTS cliente("
				+ "idCliente INT(11),"
				+ "nombre VARCHAR(500),"
				+ "email VARCHAR(500),"
				+ "PRIMARY KEY (idCliente))";

		try {
			conn.prepareStatement(sql).execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@SuppressWarnings("deprecation")
	public void fillCustomers() {
		CSVParser parser;

		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("archivosCsv/clientes.csv"));
			for(CSVRecord row: parser) {
				Cliente c = new Cliente();
				c.setIdCliente(Integer.parseInt(row.get("idCliente")));
				c.setNombre(row.get("nombre"));
				c.setEmail((row.get("email")));
				this.create(c);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	@Override
	public void create(Cliente cliente)  {
		String sql = "INSERT INTO cliente (idCliente,nombre, email) VALUES (?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cliente.getIdCliente());
			ps.setString(2, cliente.getNombre());
			ps.setString(3, cliente.getEmail());
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (Exception e) {
			e.getMessage();
		}

	}


	@Override
	public boolean delete(int id) throws SQLException {
		String sql = "DELETE FROM cliente WHERE idCliente=?";
		int response = 0;
		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			response = ps.executeUpdate();
			ps.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response!=0;
	}


	@Override
	public Cliente get(int id) throws SQLException {

		Cliente c = new Cliente();
		String sql = "SELECT * FROM cliente WHERE idCliente=?";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				c.setIdCliente(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setEmail(rs.getString(3));
			}

			conn.commit();

		} catch (Exception e) {
			System.err.println("Get error");
		}

		return c;
	}


	@Override
	public List<Cliente> getAll() throws SQLException {
		List <Cliente> listaClientes = new ArrayList<Cliente>();
		String sql = "SELECT * FROM cliente";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setIdCliente(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setEmail(rs.getString(3));
				listaClientes.add(c);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaClientes;
	}


	@Override
	public List<Cliente> getClientByBilling() throws SQLException {
		//HACER
		List <Cliente> listaClientes = new ArrayList<Cliente>();
		String sql = "SELECT c.idCliente, c.nombre, c.email, SUM(fp.cantidad) as cantidad, SUM(fp.cantidad)*p.valor AS total "
				+ "FROM cliente c "
				+ "JOIN factura f ON (c.idCliente = f.idCliente) "
				+ "JOIN facturaProducto fp ON f.idFactura = fp.idFactura "
				+ "JOIN producto p ON fp.idProducto = p.idProducto GROUP BY c.idCliente ORDER BY total DESC";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setIdCliente(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setEmail(rs.getString(3));
				c.setTotal(rs.getDouble(4));
				listaClientes.add(c);

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaClientes;
	}



}
