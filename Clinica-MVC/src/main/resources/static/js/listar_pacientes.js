const tableBody = document.querySelector("#odontologosTable tbody");
const editarModal = new bootstrap.Modal(document.getElementById("editarModal"));
const editarForm = document.getElementById("editarForm");
const editarId = document.getElementById("editarId");
const editarNombre = document.getElementById("editarNombre");
const editarApellido = document.getElementById("editarApellido");
const editarMatricula = document.getElementById("editarMatricula");
const editarFechaIngreso = document.getElementById("editarFechaIngreso");

function fetchPacientes() {
  // listando los odontologos

  fetch(`/paciente`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data, "aaa");
      // Limpiar el contenido actual de la tabla
      tableBody.innerHTML = "";

      // Insertar los datos en la tabla
      data.forEach((odontologo) => {
        const row = document.createElement("tr");

        row.innerHTML = `
                <td>${odontologo.id}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
                <td>${odontologo.dni}</td>
  
                <td>
                  <button class="btn btn-primary btn-sm" onclick="editPaciente(${odontologo.id}, '${odontologo.nombre}', '${odontologo.apellido}', '${odontologo.dni}')">Modificar</button>
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
  fetch(`/paciente/${id}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error eliminando Paciente");
      }
      return response.json();
    })
    .then((data) => {
      console.log(data);
      alert("Paciente eliminado con éxito");
      fetchPacientes(); // Actualizar la lista de Pacientes
    })
    .catch((error) => {
      console.error("Error eliminando paciente:", error);
    });
}

function editPaciente(id, nombre, apellido, dni) {
  editarId.value = id;
  editarNombre.value = nombre;
  editarApellido.value = apellido;
  editarMatricula.value = dni;

  editarModal.show();
}

editarForm.addEventListener("submit", function (event) {
  event.preventDefault();

  const id = editarId.value;
  const nombre = editarNombre.value;
  const apellido = editarApellido.value;
  const dni = editarMatricula.value;

  console.log(nombre, apellido, dni);
  fetch(`/paciente/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ nombre, apellido, dni }),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Paciente actualizado con éxito");
      editarModal.hide();
      fetchPacientes();
    })
    .catch((error) => {
      console.error("Error actualizando odontólogo:", error, 'aaaaaa');
    });
});


fetchPacientes();
