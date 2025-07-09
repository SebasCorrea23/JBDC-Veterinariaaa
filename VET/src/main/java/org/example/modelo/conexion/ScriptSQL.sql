DROP DATABASE IF EXISTS veterinariaaa;
CREATE DATABASE veterinariaaa;
USE veterinariaaa;

CREATE TABLE persona (
    documento VARCHAR(15) PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    telefono VARCHAR(20),
    direccion VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE mascota (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    especie VARCHAR(30),
    raza VARCHAR(30),
    edad INT,
    color VARCHAR(100),
    peso DOUBLE,
    propietario_documento VARCHAR(15),
    FOREIGN KEY (propietario_documento) REFERENCES persona(documento)
);




