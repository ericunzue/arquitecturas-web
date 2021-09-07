package dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import connection.MySqlConnection;
import daoInterface.FacturaProductoDao;

import pojo.FacturaProducto;

public class MySqlImplFacturaProductoDao implements FacturaProductoDao {


	Connection conn = MySqlConnection.getConnection();

	public MySqlImplFacturaProductoDao() {
		this.createTable();

	}

	private void createTable() {
		String create = "CREATE TABLE IF NOT EXISTS facturaProducto (idFactura INT (11),"
				+ "idProducto INT (11),"
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
	public void create(FacturaProducto fp) throws SQLException {
		String sql = "INSERT INTO facturaProducto (idFactura, idProducto, cantidad) VALUES (?,?,?)";


		try {
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setInt(1, fp.getIdFactura());
		    ps.setInt(2, fp.getIdProducto());
		    ps.setInt(3, fp.getCantidad());
		    ps.executeUpdate();
		    ps.close();
		    conn.commit();
		    System.out.println("Insert FacturaProducto successfull");

		} catch (Exception e) {
		   e.printStackTrace();
		}


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



	@SuppressWarnings("deprecation")
	@Override
	public void createFacturasProductos() throws SQLException {
		CSVParser parser;

		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("archivosCsv/facturas-productos.csv"));
			for(CSVRecord row: parser) {
				FacturaProducto fp = new FacturaProducto();
				fp.setIdFactura(Integer.parseInt(row.get("idFactura")));
				fp.setIdProducto(Integer.parseInt(row.get("idProducto")));
				fp.setCantidad(Integer.parseInt(row.get("cantidad")));
				this.create(fp);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}





}
