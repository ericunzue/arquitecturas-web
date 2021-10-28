package entities;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@IdClass(value=MatriculaId.class)
@Entity
public class Matricula {
		@Id
		@ManyToOne
		@JoinColumn(name="dni")
		Estudiante estudiante;
		@Id
		@ManyToOne
		@JoinColumn(name="idCarrera")
		Carrera carrera;
		@Column(nullable = false)
		Date fechaIngreso;
		@Column
		Date fechaEgreso;



		public Matricula() {
				super();
		}

		public Matricula(Estudiante estudiante, Carrera carrera) {
				super();

				this.estudiante = estudiante;
				this.carrera = carrera;
				long miliseconds = System.currentTimeMillis();
				this.fechaIngreso = new Date(miliseconds);
				this.fechaEgreso = null;
		}

		public Estudiante getEstudiante() {
				return estudiante;
		}
		public void setEstudiante(Estudiante estudiante) {
				this.estudiante = estudiante;
		}
		public Carrera getCarrera() {
				return carrera;
		}
		public void setCarrera(Carrera carrera) {
				this.carrera = carrera;
		}
		public Date getFechaIngreso() {
				return fechaIngreso;
		}
		public void setFechaIngreso(Date fechaIngreso) {
				this.fechaIngreso = fechaIngreso;
		}
		public Date getFechaEgreso() {
				return fechaEgreso;
		}
		public void setFechaEgreso(Date fechaEgreso) {
				this.fechaEgreso = fechaEgreso;
		}



		@Override
		public String toString() {
				return "Matricula [estudiante=" + estudiante + ", carrera=" + carrera
								+ ", fechaIngreso=" + fechaIngreso + ", fechaEgreso=" + fechaEgreso
								+ "]";
		}


}
