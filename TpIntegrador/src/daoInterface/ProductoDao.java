package daoInterface;

import java.sql.SQLException;

import pojo.Producto;

public interface ProductoDao extends CrudDao<Producto> {

	Producto getProductHighestCollection() throws SQLException;

	void createProductos();

}
