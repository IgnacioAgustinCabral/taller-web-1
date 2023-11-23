function openModal(viajeId) {
    var modalId = 'myModal-' + viajeId;
    var modal = document.getElementById(modalId);

    if (modal) {
        modal.style.display = "block";
    } else {
        console.error('Modal not found:', modalId);
    }
}

function closeModal(viajeId) {
    var modal = document.getElementById('myModal-' + viajeId);
    if (modal) {
        modal.style.display = 'none';
    }
}

function openModalViaje(idViaje) {
    var modal = document.getElementById('myModal-' + idViaje);
    modal.style.display = 'block';

    // Realiza una solicitud AJAX para obtener los gastos del viaje
    $.get('/mostrar-gastos?idViaje=' + idViaje, function(data) {
        $('#modal-gastos-container').html(data);
    });
}

// Cierra el modal si se hace clic fuera de él
window.onclick = function(event) {
    var modal = document.getElementById('myModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }

}