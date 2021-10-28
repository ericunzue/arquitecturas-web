package repository.impl;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import dto.ReporteCarrera;
import entities.Carrera;
import entities.Estudiante;
import entities.Matricula;
import entities.MatriculaId;
import repository.CarreraRepository;
import repository.DireccionRepository;
import repository.EstudianteRepository;
import repository.MatriculaRepository;

public class MatriculaRepositoryImpl implements MatriculaRepository {

	private EntityManager em;
	private static MatriculaRepositoryImpl soleInstance =null;

	public static MatriculaRepositoryImpl getInstance(EntityManager em) {
		if(soleInstance==null) {
			return (new MatriculaRepositoryImpl(em));
		}
		return soleInstance;
	}

	public MatriculaRepositoryImpl(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public void create(Matricula matricula) {

		CarreraRepositoryImpl carr = new CarreraRepositoryImpl(em);
		EstudianteRepositoryImpl est = EstudianteRepositoryImpl.getInstance(em);

		est.create(matricula.getEstudiante());
		carr.create(matricula.getCarrera());
		em.getTransaction().begin();
		em.merge(matricula);
		em.getTransaction().commit();
	

	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Matricula entity, int id) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public Matricula get(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> getAll() {
		List<Matricula> matriculas = new ArrayList<>();
		try {
			em.getTransaction().begin();
			matriculas= (List<Matricula>) em.createQuery("SELECT m FROM Matricula m ").getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Fallo Get Lista de matriculas" + e.getMessage());
		}

		return matriculas;
	}

	@Override
	public void update(Matricula matricula, int idEstudiante, int idCarrera) {
		
		int fechaE = matricula.getFechaEgreso();

		Estudiante es = em.find(Estudiante.class, idEstudiante);

		Carrera c = em.find(Carrera.class, idCarrera);

		try {
			
			em.getTransaction().begin();
			em.createQuery("UPDATE Matricula m SET  m.fechaEgreso=?1 WHERE m.estudiante=?3 AND m.carrera=?2")
				.setParameter(1, fechaE).setParameter(3, es).setParameter(2, c).executeUpdate();
			em.getTransaction().commit();
	

		} catch (Exception e) {
			System.out.println("Fallo actualizacion matricula" + e.getMessage());
		}

	}

	@Override
	public Matricula getMatricula(int idCarrera,int idEstudiante) {
		Estudiante es = em.find(Estudiante.class, idEstudiante);
		Matricula m =new Matricula();
		Carrera c = em.find(Carrera.class, idCarrera);

		try {
			em.getTransaction().begin();
			m = (Matricula) em.createQuery("SELECT m FROM Matricula m WHERE m.carrera=?1 AND m.estudiante=?2")
					.setParameter(1, c).setParameter(2,es).getSingleResult();
			em.getTransaction().commit();
	
		}catch(Exception e){
			System.out.println("Fallo obtener matricula" + e.getMessage());
			return null;
		}
		return m;
	}
	
	
	public List<ReporteCarrera> reporteCarreras(){
		List<ReporteCarrera> lista = new ArrayList<>();
		try {
			lista = em.createQuery("SELECT new dto.ReporteCarrera(m.carrera,c.nombre,YEAR(m.fechaIngreso),COUNT(m.estudiante),SUM(m.fechaEgreso)) FROM Matricula m JOIN m.carrera c GROUP BY (m.carrera),YEAR(m.fechaIngreso) ORDER BY YEAR(m.fechaIngreso)", ReporteCarrera.class).getResultList();

		} catch (Exception e) {
			System.out.println("Fallo obtener reporte de las carreras" + e.getMessage());
		}
		
		return lista;
		
	}

}
