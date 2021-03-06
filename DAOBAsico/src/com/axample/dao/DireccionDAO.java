package com.axample.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DireccionDAO {

	private String driver = "com.mysql.cj.jdbc.Driver";


	public DireccionDAO() {

		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		createTable();

	}

	private void createTable() {

		String sql = "CREATE TABLE IF NOT EXISTS direccion(" + "id INT NOT NULL AUTO_INCREMENT,"
				+ "calle VARCHAR(500)," + "altura INT," + "PRIMARY KEY (id))";
		Connection conn = getConnection();

		try {
			conn.prepareStatement(sql).execute();
			closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int insertDireccion(Direccion dir) {

		String sql = "INSERT INTO direccion (calle, altura) VALUES (?,?)";
		int id =0;
		Connection conn = getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dir.getCalle());
			ps.setInt(2, dir.getAltura());
			ps.executeUpdate();

			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next()){
				id=rs.getInt(1);
			}

			ps.close();
			closeConnection(conn);

		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("Insert direction successfull");
		return id;
	}


	public Direccion getDireccion(int id) {

		Direccion dir = new Direccion();
		String sql = "SELECT * FROM direccion WHERE id=" + id;
		Connection conn = getConnection();

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dir.setCalle(rs.getString(2));
				dir.setAltura(rs.getInt(3));
			}
			closeConnection(conn);
		} catch (Exception e) {
			System.out.println("Get direccion error");
		}

		return dir;

	}



	private Connection getConnection() {

		String uri = "jdbc:mysql://localhost:3306/demo2";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(uri,"root","1234");
			conn.setAutoCommit(false);
		} catch (SQLException e) {

			System.out.println(e.getMessage() + " Get Connection");
		}
		return conn;
	}


	private void closeConnection(Connection con) {
		try {
			con.commit();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}















}
