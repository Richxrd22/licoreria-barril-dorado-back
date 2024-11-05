CREATE TABLE empresa (
    id_empresa BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    ruc VARCHAR(11) NOT NULL UNIQUE,
    website VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_empresa)
);

CREATE TABLE categoria (
    id_categoria BIGINT NOT NULL AUTO_INCREMENT,
    nombre_categoria VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_categoria)
);

CREATE TABLE proveedor (
    id_proveedor BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL UNIQUE,
    dni VARCHAR(8) NOT NULL UNIQUE,
    telefono VARCHAR(9) NOT NULL,
    id_empresa BIGINT NOT NULL,
    PRIMARY KEY (id_proveedor),
    FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa)
);

CREATE TABLE producto (
    id_producto BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(50) NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    precio DOUBLE NOT NULL CHECK (precio >= 0),
    estado_cantidad BIT(1) NOT NULL,
    fecha_produccion DATE NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    id_categoria BIGINT NOT NULL,
    id_proveedor BIGINT NOT NULL,
    PRIMARY KEY (id_producto),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

INSERT INTO empresa (nombre, ruc, website) VALUES
('Johnnie Walker', '10123456789', 'www.johnniewalker.com'),
('Bacardi', '20123456789', 'www.bacardi.com'),
('Heineken', '30123456789', 'www.theheinekencompany.com'),
('Gonzalez Byass', '40123456789', 'www.gonzalezbyass.com'),
('Moët Hennessy', '50123456789', 'www.moet-hennessy.com');

INSERT INTO categoria (nombre_categoria) VALUES
('Cervezas'),
('Vinos'),
('Licores'),
('Destilados'),
('Champagnes');

INSERT INTO proveedor (nombre, apellido, correo, dni, telefono, id_empresa) VALUES
('José', 'Martínez', 'jose.martinez@johnnie.com', '12345678', '987654321', 1),
('Lucía', 'Fernández', 'lucia.fernandez@bacardi.com', '87654321', '912345678', 2),
('Carlos', 'Gómez', 'carlos.gomez@heineken.com', '23456789', '934567890', 3),
('Ana', 'Ruiz', 'ana.ruiz@gonzalezbyass.com', '34567890', '921234567', 4),
('Miguel', 'Cruz', 'miguel.cruz@moet-hennessy.com', '45678901', '923456789', 5);

INSERT INTO producto (nombre, descripcion, cantidad, precio, estado_cantidad, fecha_produccion, fecha_vencimiento, id_categoria, id_proveedor) VALUES
('Johnnie Walker Red Label', 'Whisky escocés de mezcla', 150, 20.00, 1, '2023-01-01', '2025-01-01', 4, 1),
('Bacardi Carta Blanca', 'Ron blanco', 100, 15.00, 1, '2024-01-10', '2025-01-10', 3, 2),
('Heineken Lager', 'Cerveza rubia', 200, 3.00, 1, '2024-02-01', '2025-02-01', 1, 3),
('Gonzalez Byass Tio Pepe', 'Vino de Jerez', 80, 18.00, 1, '2023-06-01', '2024-06-01', 2, 4),
('Moët & Chandon Brut', 'Champán de alta gama', 50, 50.00, 1, '2023-04-15', '2025-04-15', 5, 5);
