function deleteBy(id) {
  if (confirm("Eliminar paciente?")) {
    const url = "http://localhost:8080/pacientes/" + id;
    const settings = {
      method: "DELETE",
    };

    fetch(url, settings).then((response) => response.JSON);

    let idPaciente = "#paciente-" + id;
    document.querySelector(idPaciente).remove();
  }
}
