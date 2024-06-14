const form = document.getElementById("agregarFormPaciente");

form.addEventListener("submit", function (event) {
  event.preventDefault();

  const nombre = document.getElementById("nombre").value;
  const apellido = document.getElementById("apellido").value;
  const dni = document.getElementById("matricula").value;


  // llamando al endpoint de agregar

  fetch(`/paciente`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ nombre, apellido, nroMatricula: dni }),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Paciente agregado con éxito");
      form.reset(); // Resetear el formulario
    })
    .catch((error) => {
      console.error("Error agregando paciente:", error);
    });
});
