package repository.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import entities.Carrera;
import entities.Estudiante;
import entities.Matricula;
import entities.MatriculaId;
import repository.MatriculaRepository;

public class MatriculaRepositoryImpl implements MatriculaRepository {

		private EntityManager em;



		public MatriculaRepositoryImpl(EntityManager em) {
				super();
				this.em = em;
		}

		@Override
		public void create(Matricula matricula) {

				CarreraRepositoryImpl carr = new CarreraRepositoryImpl(em);
				EstudianteRepositoryImpl est = new EstudianteRepositoryImpl(em);

				est.create(matricula.getEstudiante());
				carr.create(matricula.getCarrera());
				em.getTransaction().begin();
				em.merge(matricula);
				em.getTransaction().commit();
				em.clear();

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

		@Override
		public List<Matricula> getAll() {
				// TODO Auto-generated method stub
				return null;
		}

		@Override
		public void update(Matricula matricula, int idEstudiante, int idCarrera) {

			Date fechaE = matricula.getFechaEgreso();

			Estudiante es = em.find(Estudiante.class, idEstudiante);

			Carrera c = em.find(Carrera.class, idCarrera);

			try {

				em.getTransaction().begin();
				em.createQuery("UPDATE Matricula m SET  m.fechaEgreso=?1 WHERE m.estudiante=?3 AND m.carrera=?2")
					.setParameter(1, fechaE).setParameter(3, es).setParameter(2, c).executeUpdate();
				em.getTransaction().commit();
				em.clear();

			} catch (Exception e) {
				System.out.println("Fallo actualizacion matricula" + e.getMessage());
			}

		}


		@Override
		public Matricula getMatricula(int idEstudiante, int idCarrera) {
			Estudiante es = em.find(Estudiante.class, idEstudiante);
			Matricula m =new Matricula();
			Carrera c = em.find(Carrera.class, idCarrera);

			try {
				em.getTransaction().begin();
				m = (Matricula) em.createQuery("SELECT m FROM Matricula m WHERE m.carrera=?1 AND m.estudiante=?2")
						.setParameter(1, c).setParameter(2,es).getSingleResult();
				em.getTransaction().commit();
				em.clear();
			}catch(Exception e){
				System.out.println("Fallo obtener matricula" + e.getMessage());
			}
			return m;
		}






}
