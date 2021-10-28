package repository;

import java.util.List;

import entities.Carrera;
import entities.Estudiante;

public interface EstudianteRepository extends CrudRepository<Estudiante>{

		Estudiante getEstudianteByLibreta(int libreta);
		List<Estudiante> getEstudianteByGenero(String genero);
		int getId(Estudiante e);
		List<Estudiante> getPorCiudad( String ciudad,int c);


}
