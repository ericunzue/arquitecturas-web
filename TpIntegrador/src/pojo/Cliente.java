package pojo;

public class Cliente {

	private int idCliente;
	private String nombre;
	private String email;
	private double total;

	public Cliente() {
		super();
	}

	public Cliente(int idCliente, String nombre, String email) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
		this.total = 0;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre
				+ ", email=" + email + ", total=" + total + "]";
	}




}
