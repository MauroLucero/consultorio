window.addEventListener("load", function () {
  fetch("http://localhost:8080/pacientes/")
    .then((response) => response.json())
    .then((data) => {
      const pacientesDiv = document.getElementById("pacientes");
      const listaPacientes = document.createElement("ul");
      listaPacientes.setAttribute("class", "paciente-list");

      data.forEach((paciente) => {
        listaPacientes.innerHTML +=
          '<li class="paciente-item" id="paciente-' +
          paciente.id +
          '"><div class="paciente-info"><h3 class="paciente-nombre">' +
          paciente.nombre +
          " " +
          paciente.apellido +
          '</h3><p class="paciente-dni">DNI: ' +
          paciente.dni +
          '</p></div><div class="paciente-actions"><button class="editar-button">Editar</button><button class="borrar-button" onclick="deleteBy(' +
          paciente.id +
          ')">Borrar</button></div></li>';
      });

      pacientesDiv.appendChild(listaPacientes);
    });
});
