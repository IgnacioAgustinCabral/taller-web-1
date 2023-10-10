INSERT INTO Usuario(id, email, password, rol, activo) VALUES(null, 'test@unlam.edu.ar', 'test', 'ADMIN', true);

INSERT INTO ViajeDisplay (nombre, avatar, fechaViaje, background, origen, destino)
VALUES
    ('Carolina Rojas', 'images/avatars/av-1.jpg', STR_TO_DATE('15/03/2024', '%d/%m/%Y'), 'images/products/jujuy_web.jpg', 'Moron, AMBA', 'Purmamarca, Jujuy'),
    ('Mariano Ochoa', 'images/avatars/av-2.jpg', STR_TO_DATE('01/12/2024', '%d/%m/%Y'), 'images/products/marDelPlata_web.jpg', 'Santa Rosa, La Pampa', 'Mar Del Plata, Buenos Aires'),
    ('Julia Ameghino', 'images/avatars/av-3.jpg', STR_TO_DATE('08/06/2024', '%d/%m/%Y'), 'images/products/misiones_web.jpg', 'San Justo, AMBA', 'Puerto Iguazu, Misiones'),
    ('Leandro Ulloa', 'images/avatars/av-4.jpg', STR_TO_DATE('22/03/2024', '%d/%m/%Y'), 'images/products/rioNegro_web.jpg', 'Bahia Blanca, Buenos Aires', 'El Bolsón, Rio Negro'),
    ('Federico Perez', 'images/avatars/av-5.jpg', STR_TO_DATE('15/11/2023', '%d/%m/%Y'), 'images/products/santacruz_web.jpg', 'Olivos, AMBA', 'Río Gallegos, Santa Cruz');