const tableBody = document.querySelector("#odontologosTable tbody");
const editarModal = new bootstrap.Modal(document.getElementById("editarModal"));
const editarForm = document.getElementById("editarForm");
const editarId = document.getElementById("editarId");
const editarNombre = document.getElementById("editarNombre");
const editarApellido = document.getElementById("editarApellido");
const editarMatricula = document.getElementById("editarMatricula");

function fetchOdontologos() {
  // listando los odontologos

  fetch(`/odontologo`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // Limpiar el contenido actual de la tabla
      tableBody.innerHTML = "";

      // Insertar los datos en la tabla
      data.forEach((odontologo, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
                <td>${odontologo.id}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
                <td>${odontologo.nroMatricula}</td>
                <td>
                  <button class="btn btn-primary btn-sm" onclick="editOdontologo(${odontologo.id}, '${odontologo.nombre}', '${odontologo.apellido}', '${odontologo.nroMatricula}')">Modificar</button>
                  <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${odontologo.id})">Eliminar</button>
                </td>
              `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });


}
function deleteOdontologo(id) {
  // Confirmar la eliminación
  const confirmDelete = confirm("¿Estás seguro de que deseas eliminar este odontólogo?");
  if (!confirmDelete) return;

  // Enviar la solicitud DELETE
  fetch(`/odontologo/${id}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error eliminando odontólogo");
      }
      return response.json();
    })
    .then((data) => {
      console.log(data);
      alert("Odontólogo eliminado con éxito");
      fetchOdontologos(); // Actualizar la lista de odontólogos
    })
    .catch((error) => {
      console.error("Error eliminando odontólogo:", error);
    });
}

function editOdontologo(id, nombre, apellido, matricula) {
  editarId.value = id;
  editarNombre.value = nombre;
  editarApellido.value = apellido;
  editarMatricula.value = matricula;
  editarModal.show();
}

editarForm.addEventListener("submit", function (event) {
  event.preventDefault();

  const id = editarId.value;
  const nombre = editarNombre.value;
  const apellido = editarApellido.value;
  const matricula = editarMatricula.value;

  fetch(`/odontologo/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ nombre, apellido, nroMatricula: matricula }),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Odontólogo actualizado con éxito");
      editarModal.hide();
      fetchOdontologos();
    })
    .catch((error) => {
      console.error("Error actualizando odontólogo:", error, 'aaaaaa');
    });
});


fetchOdontologos();
