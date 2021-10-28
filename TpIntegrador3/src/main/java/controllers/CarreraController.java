package controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Carrera;
import entityManager.EMF;
import repository.impl.CarreraRepositoryImpl;

@Path("/carrera")
public class CarreraController {
	List<Carrera> carreras;

	public CarreraController() {
		super();
	}
	
	/*
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public List<Carrera> get(){
	 * this.carreras = CarreraRepositoryImpl.getInstance(EMF.createEntityManager()).
	 * getCantidadDeInscriptosPorCarrea(); return this.carreras; }
	 */
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearCarrera(Carrera c){
		CarreraRepositoryImpl.getInstance(EMF.createEntityManager()).create(c);
		return Response.status(201).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carrera> getCarreras(){
		this.carreras = CarreraRepositoryImpl.getInstance(EMF.createEntityManager()).getAll();
		return this.carreras;
	}
	

}
