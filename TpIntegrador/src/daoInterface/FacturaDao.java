package daoInterface;

import java.sql.SQLException;

import pojo.Factura;

public interface FacturaDao extends CrudDao<Factura> {

	boolean delete(int idfactura, int idCliente)throws SQLException;

}
