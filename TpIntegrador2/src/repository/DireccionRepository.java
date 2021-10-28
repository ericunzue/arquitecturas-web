package repository;

import entities.Direccion;

public interface DireccionRepository extends CrudRepository<Direccion> {
		int getId(Direccion dir);




}
