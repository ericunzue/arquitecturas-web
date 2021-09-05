package com.axample.dao;

public class Direccion {
    String calle;
    int altura;

    public Direccion() {
	super();
    }

    public Direccion(String calle, int altura) {
	super();
	this.calle = calle;
	this.altura = altura;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
	this.calle = calle;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
	return "Direccion [Calle=" + calle + ", altura=" + altura + "]";
    }



}
