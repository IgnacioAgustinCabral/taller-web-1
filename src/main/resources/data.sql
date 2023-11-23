-- INSERT IGNORE INTO Usuario(id, email, password, rol, activo) VALUES(null, 'test@unlam.edu.ar', 'test', 'ADMIN', true);
--
-- INSERT IGNORE INTO Usuario(email, password,avatar, rol, activo,nombre,apellido,dni,fecha_nac,cod_area,telefono,imagenDePerfil)
-- VALUES
--     ('carolinarojas@unlam.edu.ar', 'test', 'images/avatars/av-1.jpg', 'admin', true, 'Carolina', 'Rojas', 12345678, '2000/01/01', 011, 12345678,null),
--     ('marianoochoa@unlam.edu.ar', 'contra2', 'images/avatars/av-2.jpg', 'admin', true, 'Mariano', 'Ochoa', 23456789, '2000/02/02', 011, 23456789,null),
--     ('juliaameghino@unlam.edu.ar', 'contra3', 'images/avatars/av-3.jpg', 'admin', true, 'Julia', 'Ameghino', 34567890, '2000/03/03', 011, 34567890,null),
--     ('leandroulloa@unlam.edu.ar', 'contra4', 'images/avatars/av-4.jpg', 'admin', true, 'Leandro', 'Ulloa', 45678901, '2000/04/04', 011, 45678901,null),
--     ('federicoperez@unlam.edu.ar', 'contra5', 'images/avatars/av-5.jpg', 'admin', true, 'Federico', 'Perez', 56789012, '2000/05/05', 011, 56789012,null),
--     ('albertosamudio@unlam.edu.ar', 'contra6',null, 'default', true, 'Alberto', 'Samudio', 67890123, '2000/06/06', 011, 67890123,null),
--     ('barbaracampos@unlam.edu.ar', 'contra7',null, 'admin', true, 'Barbara', 'Campos', 78901234, '2000/07/07', 011, 78901234,null),
--     ('cristiantoloza@unlam.edu.ar', 'contra8',null, 'default', true, 'Cristian', 'Toloza', 89012345, '2000/08/08', 011, 89012345,null),
--     ('delfinarojas@unlam.edu.ar', 'contra9',null, 'admin', true, 'Delfina', 'Rojas', 90123456, '2000/09/09', 011, 90123456,null),
--     ('estebanmendez@unlam.edu.ar', 'contra10',null, 'default', true, 'Esteban', 'Mendez', 12345670, '2000/10/10', 011, 12345670,null),
--     ('fiorellagieco@unlam.edu.ar', 'contra11',null, 'admin', true, 'Fiorella', 'Gieco', 23456701, '2000/11/11', 011, 23456701,null),
--     ('guidotosco@unlam.edu.ar', 'contra12',null, 'default', true, 'Guido', 'Tosco', 34567812, '2000/12/12', 011, 34567812,null),
--     ('huilensoria@unlam.edu.ar', 'contra13',null, 'admin', true, 'Huilen', 'Soria', 45678923, '2000/01/13', 011, 45678923,null),
--     ('iñakitedeschi@unlam.edu.ar', 'contra14',null, 'default', true, 'Iñaki', 'Tedeschi', 56789034, '2000/02/14', 011, 56789034,null),
--     ('jazminugarte@unlam.edu.ar', 'contra15',null, 'admin', true, 'Jazmin', 'Ugarte', 67890145, '2000/03/15', 011, 67890145,null),
--     ('lucafigueroa@unlam.edu.ar', 'contra16',null, 'default', true, 'Luca', 'Figueroa', 78901256, '2000/04/16', 011, 78901256,null),
--     ('melinarodriguez@unlam.edu.ar', 'contra17',null, 'admin', true, 'Melina', 'Rodriguez', 89012367, '2000/05/17', 011, 89012367,null),
--     ('nicolasbenitez@unlam.edu.ar', 'contra18',null, 'default', true, 'Nicolas', 'Benitez', 90123478, '2000/06/18', 011, 90123478,null),
--     ('orianasalvatierra@unlam.edu.ar', 'contra19',null, 'admin', true, 'Oriana', 'Salvatierra', 12345689, '2000/07/19', 011, 12345689,null),
--     ('pedroromero@unlam.edu.ar', 'contra20',null, 'default', true, 'Pedro', 'Romero', 23456790, '2000/08/20', 011, 23456790,null);
--
-- INSERT IGNORE INTO Provincia (nombre, imagen)
-- VALUES
--     ('Buenos Aires', "images/products/marDelPlata.jpg"),
--     ('Catamarca', NULL),
--     ('Chaco', NULL),
--     ('Chubut', NULL),
--     ('Córdoba', "images/products/cordoba.jpg"),
--     ('Corrientes', NULL),
--     ('Entre Ríos', NULL),
--     ('Formosa', NULL),
--     ('Jujuy', "images/products/jujuy.jpg"),
--     ('La Pampa', NULL),
--     ('La Rioja', NULL),
--     ('Mendoza', "images/products/san-rafael.jpg"),
--     ('Misiones', "images/products/misiones.jpg"),
--     ('Neuquén', "images/products/neuquen.jpg"),
--     ('Río Negro', "images/products/rioNegro.jpg"),
--     ('Salta', "images/products/salta.jpg"),
--     ('San Juan', NULL),
--     ('San Luis', NULL),
--     ('Santa Cruz', "images/products/santacruz.jpg"),
--     ('Santa Fe', NULL),
--     ('Santiago del Estero', NULL),
--     ('Tierra del Fuego', NULL),
--     ('Tucumán', NULL);

-- INSERT IGNORE INTO Ciudad (background, nombre, provincia_id)
-- VALUES
--     ('images/ciudades/Bahía-Blanca.jpg', 'Bahía Blanca', 1),
--     ('images/ciudades/colon.jpg', 'Colón', 7),
--     ('images/ciudades/concordia.jpg', 'Concordia', 7),
--     ('images/ciudades/gualeguaychu.jpg', 'Gualeguaichu', 7),
--     ('images/ciudades/la-falda.jpg', 'La Falda', 5),
--     ('images/ciudades/mar-del-plata.jpg', 'Mar del Plata', 1),
--     ('images/ciudades/bariloche.jpg', 'San Carlos de Bariloche', 15),
--     ('images/ciudades/rio-gallegos.webp', 'Río Gallegos', 19),
--     ('images/ciudades/iruya.jpg', 'Iruya', 16),
--     ('images/ciudades/san-rafael.jpg', 'San Rafael', 12),
--     ('images/ciudades/partido-de-la-costa.jpg', 'Partido de La Costa', 1),
--     ('images/ciudades/rosario.webp', 'Rosario', 20),
--     ('images/ciudades/resistencia.jpg', 'Resistencia', 3),
--     ('images/ciudades/Posadas-Misiones.jpg', 'Posadas', 13),
--     ('images/ciudades/villa-langostura.jpg', 'Villa La Angostura', 14),
--     ('images/ciudades/villa-maria.jpg', 'Villa María', 5),
--     ('images/ciudades/villa-mercedes.JPG', 'Villa Mercedes', 18),
--     ('images/ciudades/puerto-madryn.jpg', 'Puerto Madryn', 4),
--     ('images/ciudades/Rawson.jpg', 'Rawson', 4),
--     ('images/ciudades/san-salvador.jpg', 'San Salvador de Jujuy', 9),
--     ('images/ciudades/tandil.webp', 'Tandil', 1),
--     ('images/ciudades/trelew-chubut.webp', 'Trelew', 4),
--     ('images/ciudades/usuahia.png', 'Ushuaia', 22);
--
/*INSERT IGNORE INTO Viaje ( cantidad, descripcion, fecha, destino_id, origen_id, usuario_id)
VALUES (2,null,STR_TO_DATE('15/03/2024', '%d/%m/%Y'),3 , 5, 2),
       (5,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vulputate vehicula mauris, ac interdum nunc porttitor vel. In hac habitasse platea dictumst. Aliquam erat volutpat. Vestibulum ornare rutrum interdum. Sed faucibus orci ac rhoncus egestas' ,STR_TO_DATE('15/03/2024', '%d/%m/%Y'),9 , 4, 2),
       (4,null,STR_TO_DATE('15/03/2024', '%d/%m/%Y'),22 , 6, 2),
       (2,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nec tempus urna. In hac habitasse platea dictumst. Nullam feugiat tristique tellus, viverra dictum leo ultricies in. In hac habitasse platea dictumst. In hac habitasse platea dictumst.' ,STR_TO_DATE('15/03/2024', '%d/%m/%Y'),3 , 6, 2),
       (5,'Probando viajesitoo',STR_TO_DATE('25/12/2023', '%d/%m/%Y'),9 , 12, 2),
       (4,'Probando viaje de nuevoo',STR_TO_DATE('30/09/2024', '%d/%m/%Y'),22 , 8, 2),
       (3,null,STR_TO_DATE('25/09/2024', '%d/%m/%Y'),9,12,3),
       (3,null,STR_TO_DATE('05/04/2024', '%d/%m/%Y'),22,10,4),
       (3,null,STR_TO_DATE('22/12/2023', '%d/%m/%Y'),15,8,5),
       (3,null,STR_TO_DATE('22/12/2023', '%d/%m/%Y'),15,8,5),
       (3,null,STR_TO_DATE('10/11/2023', '%d/%m/%Y'),1,7,6);*/

-- INSERT INTO Viaje_Compa (id_viaje, id_usuario)
-- VALUES
--     (1,2),
--     (1,6),
--     (2,3),
--     (2,5),
--     (3,4),
--     (3,7);*/


