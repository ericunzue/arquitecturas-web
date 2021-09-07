package daoInterface;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> {
	void create(T entities) throws SQLException;
	boolean delete(int id) throws SQLException;
	T get(int id) throws SQLException;
	List<T> getAll() throws SQLException;
	void initializer();



}
