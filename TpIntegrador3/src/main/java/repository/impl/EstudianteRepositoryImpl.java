package repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

import dto.ReporteCarrera;
import entities.Carrera;
import entities.Direccion;
import entities.Estudiante;
import repository.DireccionRepository;
import repository.EstudianteRepository;

public class EstudianteRepositoryImpl implements EstudianteRepository {

	private EntityManager em;
	private static EstudianteRepositoryImpl soleInstance =null;

	public static EstudianteRepositoryImpl getInstance(EntityManager em) {
		if(soleInstance==null) {
			return (new EstudianteRepositoryImpl(em));
		}
		return soleInstance;
	}

	private EstudianteRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public void create(Estudiante estudiante) {
		int id = this.getId(estudiante);
		DireccionRepository dir = DireccionRepositoryImpl.getInstance(em);
		dir.create(estudiante.getDireccion());
		this.em.getTransaction().begin();
		if (id==0) {
			em.persist(estudiante);
		} else {
			estudiante.setDni(id);
			System.out.println("Estudiante " + id + "ya existe");
		}
		em.getTransaction().commit();
		
	}

	@Override
	public boolean delete(int id) {
		Estudiante est = em.find(Estudiante.class, id);

		em.getTransaction().begin();
		if(em.contains(est)) {
			em.remove(est);
			em.getTransaction().commit();
		
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Estudiante estudiante, int dni) {

		DireccionRepository dir = DireccionRepositoryImpl.getInstance(em);
		String nombreYApellido = estudiante.getNombreyApellido();
		int edad = estudiante.getEdad();
		String genero = estudiante.getGenero();
		int numeroLibreta = estudiante.getNumeroDeLibreta();

		dir.create(estudiante.getDireccion());

		try {
			em.getTransaction().begin();
			Estudiante est = em.getReference(Estudiante.class, dni);
			est.setNombreyApellido(nombreYApellido);
			est.setEdad(edad);
			est.setGenero(genero);
			est.setNumeroDeLibreta(numeroLibreta);
			est.setDireccion(estudiante.getDireccion());
			em.getTransaction().commit();
		
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Estudiante get(int dni) {
		Estudiante est=null;

		try {
			est = em.find(Estudiante.class, dni);

		} catch (IllegalArgumentException | EntityNotFoundException e) {

			return null;

		}

		return est;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Estudiante> getAll() {
		List<Estudiante> estudiantes= new ArrayList<Estudiante>();
		try {
			em.getTransaction().begin();
			estudiantes=(List<Estudiante>) em.createQuery("SELECT e FROM Estudiante e ORDER BY e.nombreyApellido ASC").getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return estudiantes;
	}

	@Override
	public Estudiante getEstudianteByLibreta(int libreta) {
		Estudiante estudiante = new Estudiante();


		try {
			estudiante= (Estudiante) em.createQuery("SELECT e FROM Estudiante e  WHERE e.numeroDeLibreta = ?1")
					.setParameter(1, libreta)
					.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return estudiante;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Estudiante> getEstudianteByGenero(String genero) {
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		try {
			estudiantes = em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = ?1")
					.setParameter(1, genero)
					.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return estudiantes;
	}

	@Override
	public int getId(Estudiante e) {
		int id = e.getDni();

		try {
			id= (int) em.createQuery("SELECT e.dni FROM Estudiante e WHERE e.dni =?1 ")
					.setParameter(1, id)
					.getSingleResult();
		

		} catch (Exception e2) {
			return 0;
		}
		return id;

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Estudiante> getPorCiudad(String ciudad,int c) {
		CarreraRepositoryImpl repo = new CarreraRepositoryImpl(em);
		Carrera carrera=repo.get(c);
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		try {
			estudiantes = em.createQuery("SELECT m.estudiante FROM Matricula m JOIN m.estudiante  e JOIN e.direccion d WHERE d.ciudad = ?1"
					+ "AND m.carrera =?2")
					.setParameter(1, ciudad)
					.setParameter(2, carrera)
					.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return estudiantes;

	}






}
