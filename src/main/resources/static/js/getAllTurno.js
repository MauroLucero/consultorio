window.addEventListener("load", function () {
  fetch("http://localhost:8080/odontologos/")
    .then((response) => response.json())
    .then((data) => {
      const opcionesOdontologo = document.getElementById("odontologo-turno");

      data.forEach((odontologo) => {
        opcionesOdontologo.innerHTML +=
          "<option value=" +
          odontologo.id +
          ">" +
          odontologo.nombre +
          " " +
          odontologo.apellido +
          "</option>";
      });
    });

  fetch("http://localhost:8080/pacientes/")
    .then((response) => response.json())
    .then((data) => {
      const opcionesPaciente = document.getElementById("paciente-turno");

      data.forEach((paciente) => {
        opcionesPaciente.innerHTML +=
          "<option value=" +
          paciente.id +
          ">" +
          paciente.nombre +
          " " +
          paciente.apellido +
          "</option>";
      });
    });

  fetch("http://localhost:8080/turnos/")
    .then((response) => response.json())
    .then((data) => {
      data.forEach((turno) => {
        const newRow = document.createElement("tr");
        newRow.id = "tr_" + turno.id;
        const newId = document.createElement("td");
        newId.textContent = turno.id;
        newRow.appendChild(newId);
        const newPaciente = document.createElement("td");
        newPaciente.textContent =
          turno.paciente.nombre + " " + turno.paciente.apellido;
        newRow.appendChild(newPaciente);
        const newOdontologo = document.createElement("td");
        newOdontologo.textContent =
          turno.odontologo.nombre + " " + turno.odontologo.apellido;
        newRow.appendChild(newOdontologo);
        const newFecha = document.createElement("td");
        newFecha.textContent = turno.fecha;
        newRow.appendChild(newFecha);
        const newAcciones = document.createElement("td");
        newAcciones.innerHTML =
          '<button class="editar-button" onclick="findBy(' +
          turno.id +
          ')">Editar</button><button class="borrar-button" onclick="deleteBy(' +
          turno.id +
          ')">Borrar</button>';
        newRow.appendChild(newAcciones);
        tablaBody.appendChild(newRow);
      });
    });
});
