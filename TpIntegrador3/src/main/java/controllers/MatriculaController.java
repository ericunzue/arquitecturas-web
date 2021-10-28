package controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dto.ReporteCarrera;
import entities.Matricula;
import repository.impl.MatriculaRepositoryImpl;
import entityManager.EMF;

@Path("/matricula")
public class MatriculaController {
	List<Matricula> matriculas;
	public MatriculaController() {
		super();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Matricula> get(){
		this.matriculas = MatriculaRepositoryImpl.getInstance(EMF.createEntityManager()).getAll();
		return this.matriculas;
	}
	@GET
	@Path("/{idCar},{idEst}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMatricula(@PathParam("idCar") int idC,@PathParam("idEst") int idE){
		Matricula m=MatriculaRepositoryImpl.getInstance(EMF.createEntityManager()).getMatricula(idC, idE);
		if(m==null) {
			return Response.status(404).build();
		}
		return Response.status(201).entity(m).build();
	}
	@GET
	@Path("/reporteCarrera")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReporteCarrera> getReporteCarrera(){
		List<ReporteCarrera> m= MatriculaRepositoryImpl.getInstance(EMF.createEntityManager()).reporteCarreras();
		System.out.println(m);
		return m;
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearMatricula(Matricula m){
		MatriculaRepositoryImpl.getInstance(EMF.createEntityManager()).create(m);
		return Response.status(201).build();
	}

}
