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
import daoInterface.ProductoDao;
import pojo.Producto;

public class MySqlImplProductoDao implements ProductoDao {

	Connection conn = MySqlConnection.getConnection();


	public MySqlImplProductoDao() {
		super();
		this.createTable();
		//		this.createProductos();
	}


	@SuppressWarnings("deprecation")
	public void createProductos() {
		CSVParser parser;

		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("archivosCsv/productos.csv"));
			for(CSVRecord row: parser) {
				Producto p = new Producto();
				p.setIdProducto(Integer.parseInt(row.get("idProducto")));
				p.setNombre(row.get("nombre"));
				p.setValor(Float.parseFloat(row.get("valor")));
				this.create(p);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	private void createTable() {

		String sql = "CREATE TABLE IF NOT EXISTS producto(" + "idProducto INT (11),"
				+ "nombre VARCHAR(500)," + "valor FLOAT(11)," + "PRIMARY KEY (idProducto))";

		try {
			conn.prepareStatement(sql).execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void create(Producto producto) {

		String sql = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?,?,?)";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, producto.getIdProducto());
			ps.setString(2, producto.getNombre());
			ps.setFloat(3, producto.getValor());
			ps.executeUpdate();
			ps.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean delete(int id) throws SQLException {
		String sql = "DELETE FROM producto WHERE idProducto=?";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id);
		int deleted = ps.executeUpdate();
		ps.close();
		conn.commit();

		return deleted!=0;

	}

	@Override
	public Producto get(int id) throws SQLException {

		Producto p = new Producto();
		String sql = "SELECT * FROM producto WHERE idProducto=?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				p.setIdProducto(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setValor(rs.getFloat(3));
			}
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;

	}

	@Override
	public List<Producto> getAll() throws SQLException {
		List <Producto> listaProductos = new ArrayList<Producto>();
		String sql = "SELECT * FROM producto";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Producto p = new Producto();
				p.setIdProducto(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setValor(rs.getFloat(3));
				listaProductos.add(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaProductos;
	}

	@Override
	public Producto getProductHighestCollection() throws SQLException {
		Producto p = new Producto();

		String sql = "SELECT p.idProducto, p.nombre, p.valor ,sum((fp.cantidad*p.valor)) AS facturado \n"
				+ "FROM facturaProducto fp\n"
				+ "INNER JOIN producto p on (fp.idProducto=p.idProducto)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p.setIdProducto(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setValor(rs.getFloat(3));
				p.setTotalFacturado(rs.getFloat(4));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
