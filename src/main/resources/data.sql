INSERT INTO Usuario(id, email, password, rol, activo) VALUES(null, 'test@unlam.edu.ar', 'test', 'ADMIN', true);

INSERT INTO ViajeDisplay (nombre, avatar, fechaViaje, background, origen, destino)
VALUES
    ('Carolina Rojas', 'images/avatars/av-1.jpg', STR_TO_DATE('15/03/2024', '%d/%m/%Y'), 'images/products/jujuy_web.jpg', 'Moron, AMBA', 'Purmamarca, Jujuy'),
    ('Mariano Ochoa', 'images/avatars/av-2.jpg', STR_TO_DATE('01/12/2024', '%d/%m/%Y'), 'images/products/marDelPlata_web.jpg', 'Santa Rosa, La Pampa', 'Mar Del Plata, Buenos Aires'),
    ('Julia Ameghino', 'images/avatars/av-3.jpg', STR_TO_DATE('08/06/2024', '%d/%m/%Y'), 'images/products/misiones_web.jpg', 'San Justo, AMBA', 'Puerto Iguazu, Misiones'),
    ('Leandro Ulloa', 'images/avatars/av-4.jpg', STR_TO_DATE('22/03/2024', '%d/%m/%Y'), 'images/products/rioNegro_web.jpg', 'Bahia Blanca, Buenos Aires', 'El Bolsón, Rio Negro'),
    ('Federico Perez', 'images/avatars/av-5.jpg', STR_TO_DATE('15/11/2023', '%d/%m/%Y'), 'images/products/santacruz_web.jpg', 'Olivos, AMBA', 'Río Gallegos, Santa Cruz');

INSERT INTO Usuario(email, password,avatar, rol, activo,nombre,apellido,dni,fecha_nac,cod_area,telefono)
VALUES
    ('carolinarojas@unlam.edu.ar', 'contra1', 'images/avatars/av-1.jpg', 'admin', true, 'Carolina', 'Rojas', 12345678, '2000/01/01', 011, 12345678),
    ('marianoochoa@unlam.edu.ar', 'contra2', 'images/avatars/av-2.jpg', 'admin', true, 'Mariano', 'Ochoa', 23456789, '2000/02/02', 011, 23456789),
    ('juliaameghino@unlam.edu.ar', 'contra3', 'images/avatars/av-3.jpg', 'admin', true, 'Julia', 'Ameghino', 34567890, '2000/03/03', 011, 34567890),
    ('leandroulloa@unlam.edu.ar', 'contra4', 'images/avatars/av-4.jpg', 'admin', true, 'Leandro', 'Ulloa', 45678901, '2000/04/04', 011, 45678901),
    ('federicoperez@unlam.edu.ar', 'contra5', 'images/avatars/av-5.jpg', 'admin', true, 'Federico', 'Perez', 56789012, '2000/05/05', 011, 56789012),
    ('albertosamudio@unlam.edu.ar', 'contra6',null, 'default', true, 'Alberto', 'Samudio', 67890123, '2000/06/06', 011, 67890123),
    ('barbaracampos@unlam.edu.ar', 'contra7',null, 'admin', true, 'Barbara', 'Campos', 78901234, '2000/07/07', 011, 78901234),
    ('cristiantoloza@unlam.edu.ar', 'contra8',null, 'default', true, 'Cristian', 'Toloza', 89012345, '2000/08/08', 011, 89012345),
    ('delfinarojas@unlam.edu.ar', 'contra9',null, 'admin', true, 'Delfina', 'Rojas', 90123456, '2000/09/09', 011, 90123456),
    ('estebanmendez@unlam.edu.ar', 'contra10',null, 'default', true, 'Esteban', 'Mendez', 12345670, '2000/10/10', 011, 12345670),
    ('fiorellagieco@unlam.edu.ar', 'contra11',null, 'admin', true, 'Fiorella', 'Gieco', 23456701, '2000/11/11', 011, 23456701),
    ('guidotosco@unlam.edu.ar', 'contra12',null, 'default', true, 'Guido', 'Tosco', 34567812, '2000/12/12', 011, 34567812),
    ('huilensoria@unlam.edu.ar', 'contra13',null, 'admin', true, 'Huilen', 'Soria', 45678923, '2000/01/13', 011, 45678923),
    ('iñakitedeschi@unlam.edu.ar', 'contra14',null, 'default', true, 'Iñaki', 'Tedeschi', 56789034, '2000/02/14', 011, 56789034),
    ('jazminugarte@unlam.edu.ar', 'contra15',null, 'admin', true, 'Jazmin', 'Ugarte', 67890145, '2000/03/15', 011, 67890145),
    ('lucafigueroa@unlam.edu.ar', 'contra16',null, 'default', true, 'Luca', 'Figueroa', 78901256, '2000/04/16', 011, 78901256),
    ('melinarodriguez@unlam.edu.ar', 'contra17',null, 'admin', true, 'Melina', 'Rodriguez', 89012367, '2000/05/17', 011, 89012367),
    ('nicolasbenitez@unlam.edu.ar', 'contra18',null, 'default', true, 'Nicolas', 'Benitez', 90123478, '2000/06/18', 011, 90123478),
    ('orianasalvatierra@unlam.edu.ar', 'contra19',null, 'admin', true, 'Oriana', 'Salvatierra', 12345689, '2000/07/19', 011, 12345689),
    ('pedroromero@unlam.edu.ar', 'contra20',null, 'default', true, 'Pedro', 'Romero', 23456790, '2000/08/20', 011, 23456790);

INSERT INTO Provincia (nombre, imagen)
VALUES
    ('Buenos Aires', "images/products/marDelPlata.jpg"),
    ('Catamarca', NULL),
    ('Chaco', NULL),
    ('Chubut', NULL),
    ('Córdoba', "images/products/cordoba.jpg"),
    ('Corrientes', NULL),
    ('Entre Ríos', NULL),
    ('Formosa', NULL),
    ('Jujuy', "images/products/jujuy.jpg"),
    ('La Pampa', NULL),
    ('La Rioja', NULL),
    ('Mendoza', "images/products/san-rafael.jpg"),
    ('Misiones', "images/products/misiones.jpg"),
    ('Neuquén', "images/products/neuquen.jpg"),
    ('Río Negro', "images/products/rioNegro.jpg"),
    ('Salta', "images/products/salta.jpg"),
    ('San Juan', NULL),
    ('San Luis', NULL),
    ('Santa Cruz', "images/products/santacruz.jpg"),
    ('Santa Fe', NULL),
    ('Santiago del Estero', NULL),
    ('Tierra del Fuego', NULL),
    ('Tucumán', NULL);

INSERT INTO Ciudad (background, nombre, provincia_id)
VALUES
    ('images/ciudades/Bahía-Blanca.jpg', 'Bahía Blanca', 1),
    ('images/ciudades/colon.jpg', 'Colón', 7),
    ('images/ciudades/concordia.jpg', 'Concordia', 7),
    ('images/ciudades/gualeguaychu.jpg', 'Gualeguaichu', 7),
    ('images/ciudades/la-falda.jpg', 'La Falda', 5),
    ('images/ciudades/mar-del-plata.jpg', 'Mar del Plata', 1),
    ('images/ciudades/bariloche.jpg', 'San Carlos de Bariloche', 15),
    ('images/ciudades/rio-gallegos.webp', 'Río Gallegos', 19),
    ('images/ciudades/iruya.jpg', 'Iruya', 16),
    ('images/ciudades/san-rafael.jpg', 'San Rafael', 12),
    ('images/ciudades/partido-de-la-costa.jpg', 'Partido de La Costa', 1),
    ('images/ciudades/rosario.webp', 'Rosario', 20),
    ('images/ciudades/resistencia.jpg', 'Resistencia', 3),
    ('images/ciudades/Posadas-Misiones.jpg', 'Posadas', 13),
    ('images/ciudades/villa-langostura.jpg', 'Villa La Angostura', 14),
    ('images/ciudades/villa-maria.jpg', 'Villa María', 5),
    ('images/ciudades/villa-mercedes.JPG', 'Villa Mercedes', 18),
    ('images/ciudades/puerto-madryn.jpg', 'Puerto Madryn', 4),
    ('images/ciudades/Rawson.jpg', 'Rawson', 4),
    ('images/ciudades/san-salvador.jpg', 'San Salvador de Jujuy', 9),
    ('images/ciudades/tandil.webp', 'Tandil', 1),
    ('images/ciudades/trelew-chubut.webp', 'Trelew', 4),
    ('images/ciudades/usuahia.png', 'Ushuaia', 22);

INSERT INTO Viaje ( cantidad, descripcion, fecha_hora, destino_id, origen_id, usuario_id)
VALUES (2,null,STR_TO_DATE('15/03/2024', '%d/%m/%Y'),3 , 6, 2),
       (3,null,STR_TO_DATE('25/09/2024', '%d/%m/%Y'),9,12,3),
       (3,null,STR_TO_DATE('05/04/2024', '%d/%m/%Y'),22,10,4),
       (3,null,STR_TO_DATE('22/12/2023', '%d/%m/%Y'),15,8,5),
       (3,null,STR_TO_DATE('10/11/2023', '%d/%m/%Y'),1,7,6);

INSERT INTO Viaje_Compa (id_viaje, id_usuario)
VALUES
    (1,2),
    (1,6),
    (2,3),
    (2,5),
    (3,4),
    (3,7);
