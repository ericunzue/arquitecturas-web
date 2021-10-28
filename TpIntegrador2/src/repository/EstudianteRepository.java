package repository;

import java.util.List;

import entities.Carrera;
import entities.Estudiante;

public interface EstudianteRepository extends CrudRepository<Estudiante>{

		Estudiante getEstudianteByLibreta(int libreta);
		List<Estudiante> getEstudianteByGenero(String genero);
		List<Estudiante> getPorCiudad(Carrera c, String ciudad);
		int getId(Estudiante e);


}
