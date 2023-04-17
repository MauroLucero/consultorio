window.addEventListener("load", function () {
  fetch("http://localhost:8080/odontologos/")
    .then((response) => response.json())
    .then((data) => {
      data.forEach((odontologo) => {
        const newRow = document.createElement("tr");
        newRow.id = "tr_" + odontologo.id;
        const newExpediente = document.createElement("td");
        newExpediente.textContent = odontologo.id;
        newRow.appendChild(newExpediente);
        const newNombre = document.createElement("td");
        newNombre.textContent = odontologo.nombre;
        newRow.appendChild(newNombre);
        const newApellido = document.createElement("td");
        newApellido.textContent = odontologo.apellido;
        newRow.appendChild(newApellido);
        const newMatricula = document.createElement("td");
        newMatricula.textContent = odontologo.matricula;
        newRow.appendChild(newMatricula);
        const newAcciones = document.createElement("td");
        newAcciones.innerHTML =
          '<button class="editar-button" onclick="findBy(' +
          odontologo.id +
          ')">Editar</button><button class="borrar-button" onclick="deleteBy(' +
          odontologo.id +
          ')">Borrar</button>';
        newRow.appendChild(newAcciones);
        tablaBody.appendChild(newRow);
      });
    });
});
