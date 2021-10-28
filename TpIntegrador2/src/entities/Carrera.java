package entities;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
@Entity
public class Carrera {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		int idCarrera;
		@Column
		String nombre;
		//	@ManyToOne
		//	@JoinColumn(name="idFacultad")
		//	Facultad facultad;
		//	@Transient
		//	List<Estudiante> estudiantes;

//		@OneToMany(mappedBy = "carrera", cascade={CascadeType.PERSIST}, fetch=FetchType.LAZY)
//		List<Matricula> matriculados;

		public Carrera() {
				super();
		}

		public Carrera( String nombre) {
				super();
				this.nombre = nombre;
				//		this.facultad = facultad;
		}

		public int getIdCarrera() {
				return idCarrera;
		}

		public void setIdCarrera(int idCarrera) {
				this.idCarrera = idCarrera;
		}

		public String getNombre() {
				return nombre;
		}

		public void setNombre(String nombre) {
				this.nombre = nombre;
		}

		@Override
		public String toString() {
				return "Carrera [idCarrera=" + idCarrera + ", nombre=" + nombre + "]";
		}


		//	public Facultad getFacultad() {
		//		return facultad;
		//	}
		//
		//	public void setFacultad(Facultad facultad) {
		//		this.facultad = facultad;
		//	}


//		public List<Matricula> getMatriculados() {
//				return matriculados;
//		}
//
//		public void setMatriculados(List<Matricula> matriculados) {
//				this.matriculados = matriculados;
//		}






}
