package controllers;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Direccion;
import entityManager.EMF;
import repository.impl.DireccionRepositoryImpl;

@Path("/direccion")
public class DireccionController {


	public DireccionController() {
		super();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Direccion getDireccion(@PathParam("id") int id){
		Direccion d= DireccionRepositoryImpl.getInstance(EMF.createEntityManager()).get(id);
		return d;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearDireccion(Direccion d){
		DireccionRepositoryImpl.getInstance(EMF.createEntityManager()).create(d);
		return Response.status(201).build();
	}


}
