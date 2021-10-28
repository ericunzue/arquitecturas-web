/**
 *
 */
let ESTUDIANTES = [];
let CARRERAS = [];
let MATRICULAS = [];


const baseUrl = "http://localhost:8080/TpIntegrador3/api/";

document.addEventListener("DOMContentLoaded", (e) => {
  let btnAlta = document.getElementById("guardarEstudiante");
  btnAlta.addEventListener("click", (e) => {
    e.preventDefault();
    let estudiante = {
      nombreyApellido: document.getElementById("nombre").value,
      edad: Number(document.getElementById("edad").value),
      genero: document.getElementById("genero").value,
      dni: Number(document.getElementById("dni").value),
      numeroDeLibreta: Number(document.getElementById("libreta").value),
      direccion: {
        calle: document.getElementById("calle").value,
        numero: Number(document.getElementById("numero").value),
        ciudad: document.getElementById("ciudad").value,
        provincia: document.getElementById("provincia").value,
        pais: document.getElementById("pais").value,
      },
    };

    fetch("http://localhost:8080/TpIntegrador3/api/estudiante", {
      method: "POST",

      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(estudiante),
    })
      .then((res) => {
		res.json;

})
      .catch((err) => console.error(err));
  });


  const obtenerMatriculas = async () => {
	let matriculas=[];
    try {
      let response = await fetch(
        "http://localhost:8080/TpIntegrador3/api/matricula"
      );
      let json = await response.json();

      matriculas = json;
		console.log(matriculas)
		return matriculas;

    } catch (error) {
      console.log(error);
    }

  };

obtenerEstudiantes= async ()=>{
	try{
	let response = await fetch("http://localhost:8080/TpIntegrador3/api/estudiante");
	let json = await response.json();
	ESTUDIANTES = json;
	return ESTUDIANTES;
	}catch(error){
		console.log(error);
	}

}

obtenerCarreras = async ()=>{
	let carreras;
	try{
	let response = await fetch("http://localhost:8080/TpIntegrador3/api/carrera");
	let json = response.json();
	carreras = json;
	return carreras;

	}catch(error){
	console.log(error)
	}

}

  llenarTablaMatricular= async()=>{
		let container = document.getElementById("tablaMatricular");
		let listaEstudiantes = await obtenerEstudiantes();
		let listaCarreras = await obtenerCarreras();
		console.log(listaCarreras)
		container.innerHTML = " ";
		for (let estudiante of listaEstudiantes) {

			 container.innerHTML+= `<tr class="matricular" value="${estudiante.dni}">
                  <td>${estudiante.numeroDeLibreta}</td>
		  						<td>${estudiante.nombreyApellido}</td>
							    <td>${estudiante.dni}</td>
                  <td><button type="submit" class="btn btn-primary w-50" value="${estudiante.dni}" class="btnGetEstId">Agregar</button</td>
							    </tr>`;


				}
        let botones = document.querySelectorAll(".btnGetEstId");
        for (let boton of botones) {
          boton.addEventListener("click", ()=>{
            console.log(boton);
          })
        }
        
			

};

	agregaEventoTablaMatricular= async()=>{

	
	}





	llenarTablaMatricular();
	agregaEventoTablaMatricular();

 llenarListaEstudiantes = async ()=>{
	const container = document.getElementById("listadeEstudiantes");
	let listaMatriculas = [];
	listaMatriculas = await obtenerMatriculas();

      container.innerHTML = "";

      for (let matricula of listaMatriculas) {

        container.innerHTML += `<tr>
								  <td>${matricula.estudiante.numeroDeLibreta}</td>
		  						<td>${matricula.estudiante.nombreyApellido}</td>
							  	<td>${matricula.estudiante.edad}</td>
							  	<td>${matricula.estudiante.genero}</td>
							    <td>${matricula.estudiante.dni}</td>
							    <td>${matricula.estudiante.direccion.ciudad}</td>
							    <td>${matricula.carrera.nombre}</td>
								</tr>`;
	}
};

llenarListaEstudiantes();

  const getCarreras = async () => {
    try {
      let response = await fetch(
        "http://localhost:8080/TpIntegrador3/api/carrera"
      );
      let json = await response.json();

      CARRERAS = json;
      const selectCarrera = document.getElementById("carrera");

      //selectCarrera.innerHTML = '';

      for (let carr of CARRERAS) {
        selectCarrera.innerHTML += `<option value=${carr.idCarrera}>${carr.nombre}</option>`;
      }
    } catch (error) {
      console.log(error);
    }
  };

  getCarreras();

  let btnFiltroPorCarrera = document.getElementById("filtroCarrera");
  console.log(btnFiltroPorCarrera);

  btnFiltroPorCarrera.addEventListener("click", async (e) => {
    e.preventDefault();

    try {
      let idCarrera = document.getElementById("carrera").value;
      let ciudad = document.getElementById("inputCiudad").value;
      let response = await fetch(
        "http://localhost:8080/TpIntegrador3/api/estudiante/filtrado-ciudad-carrera" +
          "?ciudad=" +
          ciudad +
          "&carrera=" +
          idCarrera
      );
      let json = await response.json();
      //console.log(idCarrera);
      ESTUDIANTES = json;

      const container = document.getElementById("listadeEstudiantes");
      container.innerHTML = "";

      for (let est of ESTUDIANTES) {
        container.innerHTML += `<tr><td>${est.numeroDeLibreta}</td>
												  <td>${est.nombreyApellido}</td>
												  <td>${est.edad}</td>
												  <td>${est.genero}</td>
												  <td>${est.dni}</td>
												  <td>${est.direccion.ciudad}</td>
												</tr>`;
      }
    } catch (error) {
      console.log(error);
    }
  });

  let btnFiltroPorGenero = document.getElementById("filtroGenero");
  btnFiltroPorGenero.addEventListener("click", async (e) => {
    e.preventDefault();

    try {
      let idGenero = document.getElementById("idGenero").value;

      let response = await fetch(
        "http://localhost:8080/TpIntegrador3/api/estudiante/filtro-genero?genero=" +
          idGenero
      );
      let json = await response.json();
      //console.log(idCarrera);
      ESTUDIANTES = json;

      const container = document.getElementById("listadeEstudiantes");
      container.innerHTML = "";

      for (let est of ESTUDIANTES) {
        container.innerHTML += `<tr><td>${est.numeroDeLibreta}</td>
  											  <td>${est.nombreyApellido}</td>
  											  <td>${est.edad}</td>
  											  <td>${est.genero}</td>
  											  <td>${est.dni}</td>
  											  <td>${est.direccion.ciudad}</td>
  											</tr>`;
      }
    } catch (error) {
      console.log(error);
    }
  });
});
