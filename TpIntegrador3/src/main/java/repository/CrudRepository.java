package repository;


import java.util.List;

import entities.Carrera;
import entities.Estudiante;

public interface CrudRepository<T> {
		void create(T entity);
		boolean delete(int id) ;
		boolean update(T entity, int id);
		T get(int id);

		List<T> getAll();
	


}
