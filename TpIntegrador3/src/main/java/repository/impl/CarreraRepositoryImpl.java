package repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import entities.Carrera;
import entities.Estudiante;
import repository.CarreraRepository;
import repository.CrudRepository;

public class CarreraRepositoryImpl implements CarreraRepository {
		EntityManager em;
		private static CarreraRepositoryImpl soleInstance =null;

		public static CarreraRepositoryImpl getInstance(EntityManager em) {
				if(soleInstance==null) {
						return (new CarreraRepositoryImpl(em));
				}
				return soleInstance;
		}


		public CarreraRepositoryImpl(EntityManager em) {
				super();
				this.em = em;
		}

		@Override
		public void create(Carrera carrera) {
				int id = this.getId(carrera);
				em.getTransaction().begin();
				if (id ==0) {
						em.persist(carrera);

				}else {
						carrera.setIdCarrera(id);
						System.out.println("ya esta guardada");
				}
				em.getTransaction().commit();




		}

		@Override
		public boolean delete(int id) {
				// TODO Auto-generated method stub
				return false;
		}

		@Override
		public boolean update(Carrera entity, int id) {
				// TODO Auto-generated method stub
				return false;
		}

		@Override
		public Carrera get(int id) {
				Carrera est=null;

				try {
						est = em.find(Carrera.class, id);

				} catch (IllegalArgumentException | EntityNotFoundException e) {

						return null;

				}

				return est;
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Carrera> getAll() {
				List<Carrera> carreras = new ArrayList<Carrera>();
				try {
						em.getTransaction().begin();
						carreras = em.createQuery("SELECT c FROM Carrera c")
										.getResultList();
						em.getTransaction().commit();
				} catch (Exception e) {
						e.printStackTrace();
				}
				return carreras;

		}

		@Override
		public int getId(Carrera carrera) {
				String nombre = carrera.getNombre();
				int id = 0;
				try {
						id= (int) em.createQuery("SELECT c.idCarrera FROM Carrera c WHERE c.nombre =?1 ")
										.setParameter(1, nombre)
										.getSingleResult();


				} catch (Exception e) {
						return 0;
				}
				return id;
		}
		@SuppressWarnings("unchecked")
		@Override
		public List<Carrera> getCantidadDeInscriptosPorCarrea() {
				List<Carrera> matriculas = new ArrayList<Carrera>();
				try {
						em.getTransaction().begin();
						matriculas = em.createQuery("SELECT c FROM Matricula m JOIN m.carrera c WHERE m.fechaEgreso IS NULL GROUP BY m.carrera ORDER BY COUNT(m.estudiante) DESC")
										.getResultList();
						em.getTransaction().commit();
				} catch (Exception e) {
						e.printStackTrace();
				}
				return matriculas;
		}





}
