package entities;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
		int fechaEgreso;

		public Matricula() {
				super();
		}

		public Matricula(Estudiante estudiante, Carrera carrera) {
				super();

				this.estudiante = estudiante;
				this.carrera = carrera;
				long miliseconds = System.currentTimeMillis();
				this.fechaIngreso = new Date(miliseconds);
				this.fechaEgreso = 0;
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
		public int getFechaEgreso() {
				return fechaEgreso;
		}
		public void setFechaEgreso(int fechaEgreso) {
				this.fechaEgreso = fechaEgreso;
		}

		@Override
		public String toString() {
			return "Matricula [estudiante=" + estudiante + ", carrera=" + carrera + ", fechaIngreso=" + fechaIngreso
					+ ", fechaEgreso=" + fechaEgreso + "]";
		}


}
