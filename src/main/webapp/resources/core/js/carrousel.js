$(document).ready(function () {
// Inicializa el carrusel al cargar la página
$('#provinciasCarousel').carousel();

// Controla la velocidad del carrusel (opcional)
$('#provinciasCarousel').carousel({
    interval: 3000 // Cambia este valor para ajustar la velocidad (en milisegundos)
});

// Pausa el carrusel al pasar el ratón sobre él (opcional)
$('#provinciasCarousel').hover(function () {
    $(this).carousel('pause');
}, function () {
    $(this).carousel('cycle');
});
});