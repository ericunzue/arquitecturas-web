package com.axample.dao;

public class Persona {
    int id;
    String name;
    String apellido;
    int edad;
    Direccion direccion;



    public Persona() {
	super();
    }

    public Persona( String name, String apellido, int edad, Direccion dir) {
	super();
	this.name = name;
	this.apellido = apellido;
	this.edad = edad;
	this.direccion = dir;   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getId() {
        return id;
    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }


    @Override
    public String toString() {
	return "Persona [name=" + name + ", apellido=" + apellido + ", edad=" + edad + "]";
    }












}
