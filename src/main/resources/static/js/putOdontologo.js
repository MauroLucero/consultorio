window.addEventListener("load", function () {
  const formulario = document.querySelector("#edit_odontologo");
  formulario.addEventListener("submit", function (event) {
    let odontologoId = document.querySelector("#odontologo_id").value;

    const formData = {
      id: document.querySelector("#odontologo_id").value,
      nombre: document.querySelector("#nombre-edit").value,
      apellido: document.querySelector("#apellido-edit").value,
      matricula: document.querySelector("#matricula-edit").value,
    };

    const url = "/odontologos/";
    const settings = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };
    fetch(url, settings).then((response) => response.json());
  });
});

function findBy(id) {
  const url = "/odontologos/" + id;
  const settings = {
    method: "GET",
  };
  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let odontologo = data;
      document.querySelector("#odontologo_id").value = odontologo.id;
      document.querySelector("#nombre-edit").value = odontologo.nombre;
      document.querySelector("#apellido-edit").value = odontologo.apellido;
      document.querySelector("#matricula-edit").value = odontologo.matricula;
      document.querySelector("#div_odontologo_updating").style.display =
        "block";
    })
    .catch((error) => {
      alert("Error: " + error);
    });
}
