package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

@Entity

public class Direccion {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		int idDireccion;
		@OneToMany(mappedBy = "direccion", cascade={CascadeType.PERSIST}, fetch=FetchType.LAZY)
		List<Estudiante> habitantes;
		String calle;
		int numero;
		String ciudad;
		String provincia;
		String pais;

		public Direccion() {
				super();
		}

		public Direccion(String calle, int numero, String ciudad, String provincia, String pais) {
				this.calle = calle.toLowerCase();
				this.numero = numero;
				this.ciudad = ciudad.toLowerCase();
				this.provincia = provincia.toLowerCase();
				this.pais = pais.toLowerCase();
		}

		public int getIdDireccion() {
				return idDireccion;
		}

		public void setIdDireccion(int idDireccion) {
				this.idDireccion = idDireccion;
		}

		public String getCalle() {
				return calle;
		}

		public void setCalle(String calle) {
				this.calle = calle.toLowerCase();
		}

		public int getNumero() {
				return numero;
		}

		public void setNumero(int numero) {
				this.numero = numero;
		}

		public String getCiudad() {
				return ciudad;
		}

		public void setCiudad(String ciudad) {
				this.ciudad = ciudad.toLowerCase();
		}

		public String getProvincia() {
				return provincia;
		}

		public void setProvincia(String provincia) {
				this.provincia = provincia.toLowerCase();
		}

		public String getPais() {
				return pais;
		}

		public void setPais(String pais) {
				this.pais = pais.toLowerCase();
		}



		public List<Estudiante> getHabitantes() {
				return habitantes;
		}

		@PreRemove
		private void preRemove() {
	   habitantes.forEach( habitante -> habitante.setDireccion(null));
	}

		@Override
		public String toString() {
				return "Direccion [idDireccion=" + idDireccion + ", calle=" + calle
								+ ", numero=" + numero + ", ciudad=" + ciudad + ", provincia="
								+ provincia + ", pais=" + pais + "]";
		}




}
