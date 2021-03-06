package com.axample.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaDAO {

    private String driver = "com.mysql.cj.jdbc.Driver";


    public PersonaDAO() {

	try {
	    Class.forName(driver).getDeclaredConstructor().newInstance();
	} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
		| InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e1) {

	    e1.printStackTrace();
	}

	    createTablePersona();

    }

    private void createTablePersona() {
	String table = "CREATE TABLE IF NOT EXISTS persona(" + "id INT NOT NULL AUTO_INCREMENT," + "nombre VARCHAR(500),"
		+ "apellido VARCHAR(500)," +
		"edad INT," +"id_direccion INT," + "PRIMARY KEY (id),"
		+ "FOREIGN KEY (id_direccion) REFERENCES direccion(id))";
	Connection conn = getConnection();

	try {
	    conn.prepareStatement(table).execute();
	    closeConnection(conn);

	} catch (SQLException e) {

	    e.printStackTrace();
	}


    }


    public void addPerson(Persona newPerson) {

	DireccionDAO direccionDAO = new DireccionDAO();
	int id = direccionDAO.insertDireccion(newPerson.getDireccion());
	String insert = "INSERT INTO persona (nombre, apellido, edad, id_direccion) VALUES (?,?,?,?)";
	Connection conn = getConnection();

	try {

	    PreparedStatement ps = conn.prepareStatement(insert);
	    ps.setString(1, newPerson.getName());
	    ps.setString(2, newPerson.getApellido());
	    ps.setInt(3, newPerson.getEdad());
	    ps.setInt(4, id);
	    ps.executeUpdate();
	    ps.close();
	    closeConnection(conn);


	} catch (Exception e) {
	    System.err.println(e.getMessage());

	}
    }


    public Persona getPerson(int id) {

	Persona p = new Persona();
	String sql = "SELECT * FROM persona WHERE id=" + id;
	Connection conn = getConnection();
	try {

	    PreparedStatement ps = conn.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		p.setId(rs.getInt(1));
		p.setName(rs.getString(2));
		p.setApellido(rs.getString(3));
		p.setEdad(rs.getInt(4));
	    }
	    closeConnection(conn);


	} catch (Exception e) {
	    System.err.println("Get error: "  + e.getMessage());
	}

	return p;
    }



    public void update(int id, String nombre, String apellido, int edad) {

	String sql = "UPDATE persona SET nombre=?, apellido=?, edad=? WHERE id=?";
	Connection conn = getConnection();

	try {

	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, nombre);
	    ps.setString(2, apellido);
	    ps.setInt(3, edad);
	    ps.setInt(4,id);
	    ps.executeUpdate();
	    closeConnection(conn);

	} catch (Exception e) {
	    System.err.println("Update error: " + e.getMessage());
	}

    }

    private Connection getConnection() {

	String uri = "jdbc:mysql://localhost:3306/demo2";
	Connection conn = null;

	try {
	    conn = DriverManager.getConnection(uri,"root","1234");
	    conn.setAutoCommit(false);
	} catch (SQLException e) {

	    e.printStackTrace();
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
