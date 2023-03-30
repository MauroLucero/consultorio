window.addEventListener('load',function(){


    const formulario = this.document.querySelector('#add_new_paciente')

    formulario.addEventListener('submit',function(event){



       

        const formDataPaciente ={
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            email: document.querySelector('#email').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fecha').value,
            domicilio:{
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numeroCalle').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value
            }
        };

          console.log(formDataPaciente);



       fetch("http://localhost:8080/pacientes/", {
               method: "POST", // or 'PUT'
               headers: {"Content-Type": "application/json",},
               body: JSON.stringify(formDataPaciente),
           })
           .then((response) => response.json())
           .then((data) => {

               console.log("Success:", data);
           })

           .catch((error) => {
               console.error("Error:", error);
           });

    })
});