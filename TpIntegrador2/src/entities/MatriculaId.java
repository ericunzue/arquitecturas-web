package entities;

import java.io.Serializable;

public class MatriculaId implements Serializable {

		private static final long serialVersionUID = 8405185866043592475L;
		// El tipo de atributo debe ser igual al de la entidad que hace referencia (Carrera, Estudiante)
		// y el nombre igual que en la entidad Matricula
		private int estudiante;
		private int carrera;


		public MatriculaId() {
				super();
		}

		public MatriculaId(int est, int carr) {
				this.estudiante = est;
				this.carrera = carr;
		}

		@Override
		public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + carrera;
				result = prime * result + estudiante;
				return result;
		}

		@Override
		public boolean equals(Object obj) {
				if (this == obj)
						return true;
				if (obj == null)
						return false;
				if (getClass() != obj.getClass())
						return false;
				MatriculaId other = (MatriculaId) obj;
				if (carrera != other.carrera)
						return false;
				if (estudiante != other.estudiante)
						return false;
				return true;
		}




















}
