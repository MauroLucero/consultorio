window.addEventListener("load", function () {
  fetch("http://localhost:8080/pacientes/")
    .then((response) => response.json())
    .then((data) => {
      data.forEach((paciente) => {
        const newRow = document.createElement("tr");
        newRow.id = "tr_" + paciente.id;
        const newExpediente = document.createElement("td");
        newExpediente.textContent = paciente.id;
        newRow.appendChild(newExpediente);
        const newNombre = document.createElement("td");
        newNombre.textContent = paciente.nombre;
        newRow.appendChild(newNombre);
        const newApellido = document.createElement("td");
        newApellido.textContent = paciente.apellido;
        newRow.appendChild(newApellido);
        const newDni = document.createElement("td");
        newDni.textContent = paciente.dni;
        newRow.appendChild(newDni);
        const newFechaIngreso = document.createElement("td");
        newFechaIngreso.textContent = paciente.fechaIngreso;
        newRow.appendChild(newFechaIngreso);
        const newAcciones = document.createElement("td");
        newAcciones.innerHTML =
          '<button class="editar-button" onclick="findBy(' +
          paciente.id +
          ')">Editar</button><button class="borrar-button" onclick="deleteBy(' +
          paciente.id +
          ')">Borrar</button>';
        newRow.appendChild(newAcciones);
        tablaBody.appendChild(newRow);
      });
    });
});
