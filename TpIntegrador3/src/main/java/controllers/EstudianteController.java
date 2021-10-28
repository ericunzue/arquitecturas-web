package controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Direccion;
import entities.Estudiante;
import entityManager.EMF;
import repository.impl.DireccionRepositoryImpl;
import repository.impl.EstudianteRepositoryImpl;

@Path("/estudiante")
public class EstudianteController {
	List<Estudiante> estudiantes;
	public EstudianteController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> get(){
		this.estudiantes = EstudianteRepositoryImpl.getInstance(EMF.createEntityManager()).getAll();
		return this.estudiantes;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudiante getEstudiante(@PathParam("id") int id){
		Estudiante e= EstudianteRepositoryImpl.getInstance(EMF.createEntityManager()).get(id);
		return e;
	}
	// buscar por numero libreta/parametro
	@GET
	@Path("/libreta/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudiante getEstudianteByLibreta(@PathParam("id") int id){
		Estudiante e= EstudianteRepositoryImpl.getInstance(EMF.createEntityManager()).getEstudianteByLibreta(id);
		return e;
	}

	@GET
	@Path("/filtro-genero")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getEstudianteByGenero(@QueryParam("genero") String genero){
		 estudiantes = EstudianteRepositoryImpl.getInstance(EMF.createEntityManager()).getEstudianteByGenero(genero);
		return estudiantes;
	}


	@GET
	@Path("/filtrado-ciudad-carrera")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getEstudianteByCiudad(@QueryParam ("ciudad") String ciudad,@QueryParam("carrera") int carrera){
		estudiantes = EstudianteRepositoryImpl.getInstance(EMF.createEntityManager()).getPorCiudad(ciudad,carrera);
		return estudiantes;
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearEstudiente(Estudiante e){
		EstudianteRepositoryImpl.getInstance(EMF.createEntityManager()).create(e);
		return Response.status(201).build();
	}
}
