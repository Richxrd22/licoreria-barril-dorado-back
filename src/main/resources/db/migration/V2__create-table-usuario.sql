CREATE TABLE empleado (
    id_empleado BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    dni VARCHAR(8) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(9) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    activo BIT(1) NOT NULL,
    PRIMARY KEY (id_empleado),
);

CREATE TABLE rol (
    id_rol BIGINT NOT NULL AUTO_INCREMENT,
    nombre_rol VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_rol)
);

CREATE TABLE usuario (
    id_usuario BIGINT NOT NULL AUTO_INCREMENT,
    clave VARCHAR(255) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    id_empleado BIGINT NOT NULL UNIQUE,
    id_rol BIGINT NOT NULL,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);