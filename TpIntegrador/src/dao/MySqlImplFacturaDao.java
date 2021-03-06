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

import daoInterface.FacturaDao;
import pojo.Cliente;
import pojo.Factura;


public class MySqlImplFacturaDao implements FacturaDao{

	private Connection conn = MySqlConnection.getConnection();

	public  MySqlImplFacturaDao() {

	}


	public void initializer() {

		String create = "CREATE TABLE IF NOT EXISTS factura (idFactura INT (11),"
				+ "idCliente INT (11),"
				+ "PRIMARY KEY (idFactura),"
				+ "FOREIGN KEY (idCliente) REFERENCES cliente (idCliente))";

		try {
			conn.prepareStatement(create).execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public void create(Factura factura) throws SQLException  {
		//Trae el cliente para saber si existe en la BBDD
		Cliente c = new MySqlImplClienteDao().get(factura.getIdCliente());

		if (c!=null) {
			String sql = "INSERT INTO factura (idFactura, idCliente) VALUES (?,?)";
			PreparedStatement ps;

			ps = conn.prepareStatement(sql);
			ps.setInt(1, factura.getIdFactura());
			ps.setInt(2, factura.getIdCliente());
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} else {
			System.out.println("Cliente Inexistente");

		}
	}


	@SuppressWarnings("deprecation")
	public void fillBills() throws SQLException {
		CSVParser parser;

		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("archivosCsv/facturas.csv"));
			for(CSVRecord row: parser) {
				Factura f = new Factura();
				f.setIdFactura(Integer.parseInt(row.get("idFactura")));
				f.setIdCliente(Integer.parseInt(row.get("idCliente")));
				this.create(f);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	@Override
	public boolean delete(int id) throws SQLException {
		return false;
	}


	@Override
	public boolean delete(int idFactura, int idCliente) throws SQLException {

		int response =0;
		Cliente c = new MySqlImplClienteDao().get(idCliente);
		Factura f = this.get(idCliente);

		if ((c!=null) && (f!=null)) {

			String sql = "DELETE FROM factura WHERE idFactura=? AND idCliente=?";
			PreparedStatement ps;

			ps = conn.prepareStatement(sql);
			ps.setInt(1, idFactura);
			ps.setInt(2, idCliente);
			response = ps.executeUpdate();
			ps.close();
			conn.commit();

		} else {
			System.out.println("Factura o Cliente Inexistente");

		}

		return response!=0;
	}


	@Override
	public Factura get(int id) throws SQLException {
		Factura f = new Factura();
		String sql = "SELECT * FROM factura WHERE idFactura=?";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				f.setIdFactura(rs.getInt(1));
				f.setIdCliente(rs.getInt(2));
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	@Override
	public List<Factura> getAll() throws SQLException {

		List <Factura> listaFacturas = new ArrayList<Factura>();
		String sql = "SELECT * FROM factura";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Factura f = new Factura();
				f.setIdFactura(rs.getInt(1));
				f.setIdCliente(rs.getInt(2));
				listaFacturas.add(f);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaFacturas;
	}




}
