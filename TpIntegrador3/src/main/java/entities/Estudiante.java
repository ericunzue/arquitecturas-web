package entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Estudiante {
		@Column (name="nyAp")
		String nombreyApellido;
		@Column
		int edad;
		@Column
		String genero;
		@Id
		int dni;
		@ManyToOne()
		Direccion direccion;
		@Column
		int numeroDeLibreta;
		//	@Transient
		//	List<Carrera> carreras;
//		@OneToMany(mappedBy = "estudiante", cascade={CascadeType.PERSIST}, fetch=FetchType.LAZY)
//		List<Matricula> carrerasMatriculadas;

		public Estudiante() {
				super();
		}

		public Estudiante(String nombreyApellido, int edad, String genero, int dni, Direccion direccion, int numeroDeLibreta) {
				this.nombreyApellido = nombreyApellido.toLowerCase();
				this.edad = edad;
				this.genero = genero.toLowerCase();
				this.dni = dni;
				this.direccion = direccion;
				this.numeroDeLibreta = numeroDeLibreta;
		}
		public String getNombreyApellido() {
				return nombreyApellido;
		}
		public void setNombreyApellido(String nombreyApellido) {
				this.nombreyApellido = nombreyApellido.toLowerCase();
		}
		public int getEdad() {
				return edad;
		}
		public void setEdad(int edad) {
				this.edad = edad;
		}
		public String getGenero() {
				return genero;
		}
		public void setGenero(String genero) {
				this.genero = genero.toLowerCase();
		}
		public int getDni() {
				return dni;
		}
		public void setDni(int dni) {
				this.dni = dni;
		}
		public Direccion getDireccion() {
				return direccion;
		}
		public void setDireccion(Direccion direccion) {
				this.direccion = direccion;
		}
		public int getNumeroDeLibreta() {
				return numeroDeLibreta;
		}
		public void setNumeroDeLibreta(int numeroDeLibreta) {
				this.numeroDeLibreta = numeroDeLibreta;
		}

		public void addMatricula(Matricula matricula) {

		}

		@Override
		public String toString() {
				return "Estudiante [nombreyApellido=" + nombreyApellido + ", edad="
								+ edad + ", genero=" + genero + ", dni=" + dni + ", direccion="
								+ direccion + ", numeroDeLibreta=" + numeroDeLibreta + "]";
		}



//		public List<Matricula> getCarrerasMatriculadas() {
//				return carrerasMatriculadas;
//		}
//
//		public void setCarrerasMatriculadas(List<Matricula> carrerasMatriculadas) {
//				this.carrerasMatriculadas = carrerasMatriculadas;
//		}



}
