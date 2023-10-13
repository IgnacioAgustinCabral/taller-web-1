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
    ('carolinarojas@unlam.edu.ar', 'contraseña1', 'images/avatars/av-1.jpg', 'admin', true, 'Carolina', 'Rojas', 12345678, '2000/01/01', 011, 12345678),
    ('marianoochoa@unlam.edu.ar', 'contraseña2', 'images/avatars/av-2.jpg', 'admin', true, 'Mariano', 'Ochoa', 23456789, '2000/02/02', 011, 23456789),
    ('juliaameghino@unlam.edu.ar', 'contraseña3', 'images/avatars/av-3.jpg', 'admin', true, 'Julia', 'Ameghino', 34567890, '2000/03/03', 011, 34567890),
    ('leandroulloa@unlam.edu.ar', 'contraseña4', 'images/avatars/av-4.jpg', 'admin', true, 'Leandro', 'Ulloa', 45678901, '2000/04/04', 011, 45678901),
    ('federicoperez@unlam.edu.ar', 'contraseña5', 'images/avatars/av-5.jpg', 'admin', true, 'Federico', 'Perez', 56789012, '2000/05/05', 011, 56789012),
    ('albertosamudio@unlam.edu.ar', 'contraseña6',null, 'default', true, 'Alberto', 'Samudio', 67890123, '2000/06/06', 011, 67890123),
    ('barbaracampos@unlam.edu.ar', 'contraseña7',null, 'admin', true, 'Barbara', 'Campos', 78901234, '2000/07/07', 011, 78901234),
    ('cristiantoloza@unlam.edu.ar', 'contraseña8',null, 'default', true, 'Cristian', 'Toloza', 89012345, '2000/08/08', 011, 89012345),
    ('delfinarojas@unlam.edu.ar', 'contraseña9',null, 'admin', true, 'Delfina', 'Rojas', 90123456, '2000/09/09', 011, 90123456),
    ('estebanmendez@unlam.edu.ar', 'contraseña10',null, 'default', true, 'Esteban', 'Mendez', 12345670, '2000/10/10', 011, 12345670),
    ('fiorellagieco@unlam.edu.ar', 'contraseña11',null, 'admin', true, 'Fiorella', 'Gieco', 23456701, '2000/11/11', 011, 23456701),
    ('guidotosco@unlam.edu.ar', 'contraseña12',null, 'default', true, 'Guido', 'Tosco', 34567812, '2000/12/12', 011, 34567812),
    ('huilensoria@unlam.edu.ar', 'contraseña13',null, 'admin', true, 'Huilen', 'Soria', 45678923, '2000/01/13', 011, 45678923),
    ('iñakitedeschi@unlam.edu.ar', 'contraseña14',null, 'default', true, 'Iñaki', 'Tedeschi', 56789034, '2000/02/14', 011, 56789034),
    ('jazminugarte@unlam.edu.ar', 'contraseña15',null, 'admin', true, 'Jazmin', 'Ugarte', 67890145, '2000/03/15', 011, 67890145),
    ('lucafigueroa@unlam.edu.ar', 'contraseña16',null, 'default', true, 'Luca', 'Figueroa', 78901256, '2000/04/16', 011, 78901256),
    ('melinarodriguez@unlam.edu.ar', 'contraseña17',null, 'admin', true, 'Melina', 'Rodriguez', 89012367, '2000/05/17', 011, 89012367),
    ('nicolasbenitez@unlam.edu.ar', 'contraseña18',null, 'default', true, 'Nicolas', 'Benitez', 90123478, '2000/06/18', 011, 90123478),
    ('orianasalvatierra@unlam.edu.ar', 'contraseña19',null, 'admin', true, 'Oriana', 'Salvatierra', 12345689, '2000/07/19', 011, 12345689),
    ('pedroromero@unlam.edu.ar', 'contraseña20',null, 'default', true, 'Pedro', 'Romero', 23456790, '2000/08/20', 011, 23456790);

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

INSERT INTO Viaje(cantidad,descripcion,destino,fecha_hora,origen,usuario_id) VALUES(2, 'contraseña1', 'purmamarca', '15/03/2024', 'casa',2),
                                                                                   (4, 'contraseña2', 'la rioja','15/03/2024','casa',3),
                                                                                   (3, 'contraseña3', 'arbol solo','15/03/2024', 'casa',4),
                                                                                   (2, 'contraseña4', 'trenque lauquen','15/03/2024', 'casa',5),
                                                                                   (4, 'contraseña5', 'la matanza','15/03/2024', 'casa',6);