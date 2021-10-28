package repository;

import java.util.List;

import entities.Carrera;

public interface CarreraRepository extends CrudRepository<Carrera> {
		int getId(Carrera carrera);

		List<Carrera> getCantidadDeInscriptosPorCarrea();

}
