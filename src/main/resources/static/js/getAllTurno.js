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
      const turnosDiv = document.getElementById("turnos");
      const listaTurnos = document.createElement("ul");
      listaTurnos.setAttribute("class", "turno-list");

      data.forEach((turno) => {
        listaTurnos.innerHTML +=
          '<li class="paciente-item" id="turno-' +
          turno.id +
          '"><div class="turno-info"><h3 class="turno-paciente">' +
          turno.paciente.nombre +
          " " +
          turno.paciente.apellido +
          '</h3><h3 class="turno-odontologo">' +
          turno.odontologo.nombre +
          " " +
          turno.odontologo.apellido +
          " MP:" +
          turno.odontologo.matricula +
          '</h3><p class="turno-fecha">Fecha: ' +
          turno.fecha +
          '</p></div><div class="turno-actions"><button class="editar-button">Editar</button><button class="borrar-button" onclick="deleteBy(' +
          turno.id +
          ')">Borrar</button></div></li>';
      });

      turnosDiv.appendChild(listaTurnos);
    });
});
