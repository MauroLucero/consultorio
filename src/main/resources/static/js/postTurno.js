window.addEventListener("load", function () {
  const formulario = this.document.querySelector("#add_new_turno");

  formulario.addEventListener("submit", function (event) {
    const formDataTurno = {
      paciente: {
        id: parseInt(document.querySelector("#paciente-turno").value),
      },
      odontologo: {
        id: parseInt(document.querySelector("#odontologo-turno").value),
      },
      fecha: document.querySelector("#fecha").value,
    };

    fetch("http://localhost:8080/turnos/", {
      method: "POST", // or 'PUT'
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formDataTurno),
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

const botonMostrar = document.getElementById("mostrar-formulario");
const formulario = document.getElementById("add_new_turno");

botonMostrar.addEventListener("click", function () {
  if (formulario.style.display === "none") {
    formulario.style.display = "block";
  } else {
    formulario.style.display = "none";
  }
});
