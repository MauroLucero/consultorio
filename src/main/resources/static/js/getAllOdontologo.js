window.addEventListener("load", function () {
  fetch("http://localhost:8080/odontologos/")
    .then((response) => response.json())
    .then((data) => {
      const odontologosDiv = document.getElementById("odontologos");
      const listaOdontologos = document.createElement("ul");
      listaOdontologos.setAttribute("class", "odontologo-list");

      data.forEach((odontologo) => {
        listaOdontologos.innerHTML +=
          '<li class="paciente-item" id="odontologo-' +
          odontologo.id +
          '"><div class="odontologo-info"><h3 class="odontologo-nombre">' +
          odontologo.nombre +
          " " +
          odontologo.apellido +
          '</h3><p class="odontologo-matricula">Matricula: ' +
          odontologo.matricula +'</p></div><div class="odontologo-actions"><button class="editar-button">Editar</button><button class="borrar-button" onclick="deleteBy(' +
          odontologo.id +
          ')">Borrar</button></div></li>';
      });

      odontologosDiv.appendChild(listaOdontologos);
    });
});
