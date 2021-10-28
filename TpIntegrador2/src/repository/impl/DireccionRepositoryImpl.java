package repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PreRemove;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Direccion;
import entities.Estudiante;
import repository.DireccionRepository;

public class DireccionRepositoryImpl implements DireccionRepository {

		EntityManager em;


		public DireccionRepositoryImpl(EntityManager em) {
				super();
				this.em = em;
		}

		@Override
		public void create(Direccion direccion) {

				//Busca si existe la Direccion
				int id = this.getId(direccion);

				em.getTransaction().begin();
				if (id==0) {
						em.persist(direccion);
				} else {

						direccion.setIdDireccion(id);

				}
				em.getTransaction().commit();
				em.clear();

		}


		@Override
		public boolean delete(int id) {

				Direccion dir = (Direccion) em.find(Direccion.class, id);

				em.getTransaction().begin();
				em.remove(dir);
				em.getTransaction().commit();
				em.clear();
				return true;
		}


		@Override
		public boolean update(Direccion direccionMod, int id) {

				String ciudad = direccionMod.getCiudad();
				String provincia = direccionMod.getProvincia();
				String pais = direccionMod.getPais();
				String calle = direccionMod.getCalle();
				int altura = direccionMod.getNumero();

				try {
						em.getTransaction().begin();
						Direccion dir = em.getReference(Direccion.class, id);
						dir.setCiudad(ciudad);
						dir.setProvincia(provincia);
						dir.setPais(pais);
						dir.setCalle(calle);
						dir.setNumero(altura);
						em.getTransaction().commit();
						em.clear();
						return true;

				} catch (Exception e) {
						return false;
				}


		}

		@Override
		public Direccion get(int id) {

				Direccion dir=null;

				try {
						dir = em.find(Direccion.class, id);

				} catch (IllegalArgumentException | EntityNotFoundException e) {

						return null;

				}

				return dir;

		}

		@Override
		public List<Direccion> getAll() {
				// TODO Auto-generated method stub
				return null;
		}

		@Override
		public int getId(Direccion direccion) {

				String ciudad = direccion.getCiudad();
				String provincia = direccion.getProvincia();
				String pais = direccion.getPais();
				String calle = direccion.getCalle();
				int id =0;

				try {
						//						em.getTransaction().begin();
						id = (int) em.createQuery(

										"SELECT d.idDireccion FROM Direccion d WHERE d.ciudad =?1 "
														+ "AND d.provincia =?2 "
														+ "AND d.pais =?3 "
														+ "AND d.calle =?4")
										.setParameter(1, ciudad)
										.setParameter(2, provincia)
										.setParameter(3, pais)
										.setParameter(4, calle)
										.getSingleResult();

						em.clear();

				} catch (NoResultException  | NonUniqueResultException e) {
						return 0;
				}

				return id;

		}

}
