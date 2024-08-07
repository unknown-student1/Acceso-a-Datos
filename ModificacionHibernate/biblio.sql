CREATE DATABASE IF NOT EXISTS Biblio
DEFAULT CHARACTER SET utf8 
DEFAULT COLLATE utf8_general_ci;

USE Biblio;

DROP TABLE IF EXISTS Prestamo;
DROP TABLE IF EXISTS Libro;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS Autor;


CREATE TABLE Autor (
	idAutor INT NOT NULL,
	NombreAutor VARCHAR(60) NOT NULL,
	PRIMARY KEY (idAutor)
) ENGINE=InnoDB;


CREATE TABLE Usuario (
	idUsuario INT NOT NULL,
	NombreCompleto VARCHAR(60) NOT NULL,
	Direccion VARCHAR(100) NOT NULL,
	Correo VARCHAR(25) NULL DEFAULT 'Sin Correo',
	Telefono VARCHAR(15) NOT NULL,
	Foto VARCHAR(20) NOT NULL,
	PRIMARY KEY (idUsuario)
) ENGINE=InnoDB;

CREATE TABLE Libro (
	idLibro INT NOT NULL,
	ISBN VARCHAR(20) NOT NULL UNIQUE,
	Titulo VARCHAR(65) NOT NULL,
	NumeroEjemplares TINYINT NOT NULL,
	idAutor INT NOT NULL,
	PRIMARY KEY (idLibro),
	FOREIGN KEY (idAutor) REFERENCES Autor (idAutor)
	) ENGINE=InnoDB;

CREATE TABLE Prestamo (
	idPrestamo INT NOT NULL AUTO_INCREMENT,
	idUsuario INT NOT NULL,
	idLibro INT NOT NULL,
	fecha DATE,
	PRIMARY KEY (idPrestamo),
	FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario),
	FOREIGN KEY (idLibro) REFERENCES Libro (idLibro)
) ENGINE=InnoDB;

DELETE FROM PRESTAMO;
DELETE FROM LIBRO;
DELETE FROM USUARIO;
DELETE FROM AUTOR;

INSERT INTO Autor VALUES (1, "Fernando López Segura");
INSERT INTO Autor VALUES (2, "Eduardo Cruz Ruiz");
INSERT INTO Autor VALUES (3, "Lilian Valecia Carrillo");
INSERT INTO Autor VALUES (4, "Juan Carlos Segundo Elias");
INSERT INTO Autor VALUES (5, "Jair Cuellar Artega");
INSERT INTO Autor VALUES (6, "Karla Rojas García");

INSERT INTO Usuario VALUES (1, "Alfredo Hernández Mendoza", "Dirección 1", "alfred123@gmail.com", "12345678", 'Foto_1.png');
INSERT INTO Usuario VALUES (2, "Juan Alberto Ramírez Sandoval", "Dirección 2", "juanal_66@hotmail.com", "91847567", 'Foto_2.png');
INSERT INTO Usuario VALUES (3, "Enrique Alberto García Aguado", "Dirección 3", "", "22885534", 'Foto_3.png');
INSERT INTO Usuario VALUES (4, "Esmeralda López Cabrera", "Dirección 4", "esme27_p@yahoo.com.mx", "45263489", 'Foto_4.png');
INSERT INTO Usuario VALUES (5, "Janeth Soto Cruz", "Dirección 5", "janeth11@hotmail.com", "64829164", 'Foto_5.png');
INSERT INTO Usuario VALUES (6, "Marco Antonio Pérez Díaz", "Dirección 6", "makr@gmail.com", "88335522", 'Foto_6.png');

INSERT INTO Libro VALUES (1, "1234567891", "El Lengu de Prgramación C", 27, 1);
INSERT INTO Libro VALUES (2, "1357935799", "Fundamentos de Programación", 12, 1);
INSERT INTO Libro VALUES (3, "1010345655", "The Book of Ruby", 9, 1);
INSERT INTO Libro VALUES (4, "3456789212", "Programación en C/C++", 25, 1);
INSERT INTO Libro VALUES (5, "7578799145", "Introducción a la teoría general de la administración", 45, 6);
INSERT INTO Libro VALUES (6, "3238845533", "Administración Moderna", 12, 6);
INSERT INTO Libro VALUES (7, "5267174899", "Generación de Modelos de Negocio", 16, 6);
INSERT INTO Libro VALUES (8, "2456789011", "Fundamentos de Administración Financiera", 99, 6);
INSERT INTO Libro VALUES (9, "3454565890", "Invitación a la Biología", 11, 3);
INSERT INTO Libro VALUES (10, "2224579900", "Biología molecular de la célula", 14, 3);
INSERT INTO Libro VALUES (11, "0988277777", "Biología: ciencia y naturaleza", 22, 3);
INSERT INTO Libro VALUES (12, "6372653847", "Atlas de Biología", 1, 3);
INSERT INTO Libro VALUES (13, "9823525255", "Sistemas de Control para Ingeniería", 5, 4);
INSERT INTO Libro VALUES (14, "2324611234", "Circuitos Eléctricos", 10, 4);
INSERT INTO Libro VALUES (15, "7774828288", "Sismas de Comunicaciones", 7, 4);
INSERT INTO Libro VALUES (16, "2343454577", "Química General", 2, 5);
INSERT INTO Libro VALUES (17, "5568883333", "Química Orgánica", 3, 5);
INSERT INTO Libro VALUES (18, "1111166988", "Principios de Economía", 1, 2);
INSERT INTO Libro VALUES (19, "0044523255", "La riqueza de las naciones", 4, 2);
INSERT INTO Libro VALUES (20, "5767676722", "Economía y Sociedad", 17, 2);
INSERT INTO Libro VALUES (21, "3235567986", "Marketi de Guerra", 1, 2);

INSERT INTO Prestamo VALUES (1,1, 6,NOW());
INSERT INTO Prestamo VALUES (2,4, 4,NOW());
INSERT INTO Prestamo VALUES (3,5, 1,NOW());
INSERT INTO Prestamo VALUES (4,1, 2,NOW());
INSERT INTO Prestamo VALUES (5,3, 3,NOW());
INSERT INTO Prestamo VALUES (6,1, 5,NOW());
INSERT INTO Prestamo VALUES (7,4, 3,NOW());
INSERT INTO Prestamo VALUES (8,5, 19,NOW());
INSERT INTO Prestamo VALUES (9,4, 5,NOW());
INSERT INTO Prestamo VALUES (10,1, 12,NOW());
INSERT INTO Prestamo VALUES (11,1, 15,NOW());

INSERT INTO Prestamo VALUES (12,1, 7,NOW());
INSERT INTO Prestamo VALUES (13,3, 8,NOW());
INSERT INTO Prestamo VALUES (14,1, 9,NOW());
INSERT INTO Prestamo VALUES (15,4, 10,NOW());
INSERT INTO Prestamo VALUES (16,5, 11,NOW());
INSERT INTO Prestamo VALUES (17,4, 18,NOW());
INSERT INTO Prestamo VALUES (18,1, 20,NOW());

SHOW WARNINGS;