package pojo;

public class Producto {

	private int idProducto;
	private String nombre;
	private float valor;
	private float totalFacturado;

	public Producto() {
		super();
	}

	public Producto(int idProducto, String nombre, float valor) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.valor = valor;
		this.totalFacturado=0;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}



	public float getTotalFacturado() {
		return totalFacturado;
	}

	public void setTotalFacturado(float totalFacturado) {
		this.totalFacturado = totalFacturado;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre
				+ ", valor=" + valor + ", totalFacturado=" + totalFacturado + "]";
	}





}
