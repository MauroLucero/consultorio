window.addEventListener("load", function () {
  const formulario = this.document.querySelector("#edit_paciente");

  formulario.addEventListener("submit", function (event) {
    const formDataPaciente = {
      id: document.querySelector("#paciente_id").value,
      nombre: document.querySelector("#nombre-edit").value,
      apellido: document.querySelector("#apellido-edit").value,
      email: document.querySelector("#email-edit").value,
      dni: document.querySelector("#dni-edit").value,
      fechaIngreso: document.querySelector("#fecha-edit").value,
      domicilio: {
        id: document.querySelector("#domicilio_id").value,
        calle: document.querySelector("#calle-edit").value,
        numero: document.querySelector("#numeroCalle-edit").value,
        localidad: document.querySelector("#localidad-edit").value,
        provincia: document.querySelector("#provincia-edit").value,
      },
    };

    fetch("http://localhost:8080/pacientes/", {
      method: "PUT", // or 'PUT'
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formDataPaciente),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Success:", data);
      })

      .catch((error) => {
        console.error("Error:", error);
      });
  });
});

function findBy(id) {
  const url = "/pacientes/" + id;
  const settings = {
    method: "GET",
  };
  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let paciente = data;
      document.querySelector("#paciente_id").value = paciente.id;
      document.querySelector("#nombre-edit").value = paciente.nombre;
      document.querySelector("#apellido-edit").value = paciente.apellido;

      document.querySelector("#email-edit").value = paciente.email;
      document.querySelector("#dni-edit").value = paciente.dni;
      document.querySelector("#fecha-edit").value = paciente.fechaIngreso;
      document.querySelector("#domicilio_id").value = paciente.domicilio.id;
      document.querySelector("#calle-edit").value = paciente.domicilio.calle;
      document.querySelector("#numeroCalle-edit").value =
        paciente.domicilio.numero;
      document.querySelector("#localidad-edit").value =
        paciente.domicilio.localidad;
      document.querySelector("#provincia-edit").value =
        paciente.domicilio.provincia;

      document.querySelector("#div_edit_paciente").style.display = "block";
    })
    .catch((error) => {
      alert("Error: " + error);
    });
}
