window.addEventListener("load", function () {
  const formulario = this.document.querySelector("#add_new_odontologo");

  formulario.addEventListener("submit", function (event) {
    const formDataPaciente = {
      nombre: document.querySelector("#nombre").value,
      apellido: document.querySelector("#apellido").value,
      matricula: document.querySelector("#matricula").value,
    };

    fetch("http://localhost:8080/odontologos/", {
      method: "POST", // or 'PUT'
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

const botonMostrar = document.getElementById("mostrar-formulario");
const formulario = document.getElementById("add_new_odontologo");

botonMostrar.addEventListener("click", function () {
  if (formulario.style.display === "none") {
    formulario.style.display = "block";
  } else {
    formulario.style.display = "none";
  }
});
