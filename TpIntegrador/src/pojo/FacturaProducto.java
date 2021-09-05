package pojo;

public class FacturaProducto {

	int idFactura;
	int idProducto;
	int cantidad;


	public FacturaProducto() {
		super();
	}


	public FacturaProducto(int idFactura, int idProducto, int cantidad) {
		super();
		this.idFactura = idFactura;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}


	public int getIdFactura() {
		return idFactura;
	}


	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}


	public int getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}





}
