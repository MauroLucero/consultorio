function deleteBy(id) {
  if (confirm("Eliminar odontologo?")) {
    const url = "http://localhost:8080/odontologos/" + id;
    const settings = {
      method: "DELETE",
    };

    fetch(url, settings).then((response) => response.JSON);

    let row_id = "#tr_" + id;
    document.querySelector(row_id).remove();
  }
}
