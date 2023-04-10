function deleteBy(id) {
  if (confirm("Eliminar turno?")) {
    const url = "http://localhost:8080/turnos/" + id;
    const settings = {
      method: "DELETE",
    };

    fetch(url, settings).then((response) => response.JSON);

    let idTurno = "#turno-" + id;
    document.querySelector(idTurno).remove();
  }
}
