package daoInterface;

import java.sql.SQLException;

import pojo.Producto;

public interface ProductoDao extends CrudDao<Producto> {

	Producto topBillingProduct() throws SQLException;

	void fillProducts();

}
