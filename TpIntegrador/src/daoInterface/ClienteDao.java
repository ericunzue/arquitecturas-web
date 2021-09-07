package daoInterface;

import java.sql.SQLException;
import java.util.List;

import pojo.Cliente;

public interface ClienteDao extends CrudDao<Cliente> {

	List<Cliente> getClientByBilling() throws SQLException;

	void createClientes()throws SQLException;

}
