package daoInterface;

import java.sql.SQLException;

import pojo.FacturaProducto;

public interface FacturaProductoDao extends CrudDao<FacturaProducto>{
	void delete(int a, int b);

	void fillBillsProducts() throws SQLException;


}
