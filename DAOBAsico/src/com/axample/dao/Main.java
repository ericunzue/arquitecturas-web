package com.axample.dao;

public class Main {

    public static void main(String[] args) {
	Direccion dir1 = new Direccion("Roca", 168);
	Direccion dir2 = new Direccion("Olascoaga", 434);
	Persona p1 = new Persona("Eli", "Cab", 26, dir1);
	Persona p2 = new Persona("Ana", "Meg", 21, dir2);
	DireccionDAO dirDAO = new DireccionDAO();
	PersonaDAO personaDAO = new PersonaDAO();
	personaDAO.addPerson(p1);
	personaDAO.addPerson(p2);
//	personaDAO.update(2, "Analia", "Mega", 60);
//	System.out.println(personaDAO.getPerson(2));





//	System.out.println(dirDAO.insertDireccion(dir1));





    }

}
