function openModal(viajeId) {
    var modalId = 'myModal-' + viajeId;
    var modal = document.getElementById(modalId);

    if (modal) {
        modal.style.display = "block";
    } else {
        console.error('Modal not found:', modalId);
    }
}

function closeModal() {
    $('.modal').css('display', 'none');
}

/*
function openModalViaje(viajeId) {
    console.log('Botón clicado. Abriendo modal...');
    var modal = document.getElementById('myModal-' + viajeId); // verifica el ID aquí
    console.log(modal); // para asegurarte de que obtenga el elemento
    modal.style.display = 'block';
}
*/

function closeModalViaje() {
    var modal = document.getElementById('myModal');
    modal.style.display = 'none';
}


// Cierra el modal si se hace clic fuera de él
window.onclick = function(event) {
    var modal = document.getElementById('myModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
}

function mostrarGastos(idViaje) {

    $.ajax({
        type: 'GET',
        url: '/spring/mostrar-gastos',  // Corregir la URL aquí
        data: { idViaje: idViaje },
        dataStyle: {JSON},
        success: function(response) {
            // Limpiar la tabla de gastos existente
            $('#tablaGastos').empty();

            // Verificar si la respuesta es un objeto y tiene la propiedad 'gastos'
            if (response && response.gastos) {
                // Agregar los nuevos gastos a la tabla
                $.each(response.gastos, function(index, gasto) {
                    var row = '<tr><td>' + gasto.descripcion + '</td><td>' + gasto.monto + '</td></tr>';
                    $('#tablaGastos').append(row);
                });

                // Mostrar la sección de gastos
                $('.gastos-section').show();

                // Acceder al montoTotal y mostrarlo donde desees
                var montoTotal = response.montoTotal;
                $('#montoTotal').text(montoTotal);
            } else {
                console.error('La respuesta del servidor no tiene la estructura esperada.');
            }
        },
        error: function() {
            console.error('Error al obtener los gastos del servidor');
        }
    });
}


function calcularMontoPorPersona() {
    var montoTotal = parseFloat($('#montoTotal').text());
    var cantidadPersonasEnViaje = parseInt('${viaje.cantidad}'); // Obtener la cantidad de personas desde Thymeleaf

    if (!isNaN(montoTotal) && !isNaN(cantidadPersonasEnViaje) && cantidadPersonasEnViaje > 0) {
        var montoPorPersona = montoTotal / cantidadPersonasEnViaje;
        $('#montoPorPersona').text(montoPorPersona.toFixed(2));
    } else {
        console.error('Error al calcular el monto por persona. Asegúrate de tener un monto total y una cantidad de personas válidos.');
    }
}

// Actualizar la cantidad de personas en el viaje cuando alguien se une o se va
function actualizarCantidadPersonasEnViaje(cantidadActualizada) {
    // Actualizar la cantidad de personas en el objeto viaje (por ejemplo, desde una respuesta AJAX)
    '${viaje.cantidad}' = cantidadActualizada;
    // Volver a calcular el monto por persona con la nueva cantidad
    calcularMontoPorPersona();
}