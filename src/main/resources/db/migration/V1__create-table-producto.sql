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
    activo BIT(1) NOT NULL,

    PRIMARY KEY (id_categoria)
);

CREATE TABLE proveedor (
    id_proveedor BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL UNIQUE,
    dni VARCHAR(8) NOT NULL UNIQUE,
    telefono VARCHAR(9) NOT NULL,
    activo BIT(1) NOT NULL,
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
    activo BIT(1) NOT NULL,
    id_categoria BIGINT NOT NULL,
    id_proveedor BIGINT NOT NULL,
    PRIMARY KEY (id_producto),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

CREATE TABLE movimiento_stock (
    id_movimiento BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_producto BIGINT NOT NULL,
    fecha_movimiento DATE NOT NULL,
    cantidad INT NOT NULL,
    tipo_movimiento ENUM('ENTRADA', 'SALIDA') NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

INSERT INTO empresa (nombre, ruc, website) VALUES
('Johnnie Walker', '10123456789', 'www.johnniewalker.com'),
('Bacardi', '20123456789', 'www.bacardi.com'),
('Heineken', '30123456789', 'www.theheinekencompany.com'),
('Gonzalez Byass', '40123456789', 'www.gonzalezbyass.com'),
('Moët Hennessy', '50123456789', 'www.moet-hennessy.com'),
('Diageo', '60123456789', 'www.diageo.com'),
('Pernod Ricard', '70123456789', 'www.pernod-ricard.com'),
('Beam Suntory', '80123456789', 'www.beamsuntory.com'),
('Constellation Brands', '90123456789', 'www.cbrands.com'),
('Brown-Forman', '01123456789', 'www.brown-forman.com');

INSERT INTO categoria (nombre_categoria, activo) VALUES
('Cervezas', 1),
('Vinos', 1),
('Licores', 1),
('Destilados', 1),
('Champagnes', 1),
('Tequilas', 1),
('Whiskys', 1),
('Rones', 1),
('Vodkas', 1),
('Gins', 1),
('Sidras', 1);

INSERT INTO proveedor (nombre, apellido, correo, dni, telefono, id_empresa, activo) VALUES
('José', 'Martínez', 'jose.martinez@johnnie.com', '12345678', '987654321', 1, 1),
('Lucía', 'Fernández', 'lucia.fernandez@bacardi.com', '87654321', '912345678', 2, 1),
('Carlos', 'Gómez', 'carlos.gomez@heineken.com', '23456789', '934567890', 3, 1),
('Ana', 'Ruiz', 'ana.ruiz@gonzalezbyass.com', '34567890', '921234567', 4, 1),
('Miguel', 'Cruz', 'miguel.cruz@moet-hennessy.com', '45678901', '923456789', 5, 1),
('Roberto', 'Santana', 'roberto.santana@diageo.com', '56789012', '934567123', 6, 1),
('Elena', 'García', 'elena.garcia@pernod-ricard.com', '67890123', '912345654', 7, 1),
('David', 'Morales', 'david.morales@beamsuntory.com', '78901234', '987654987', 8, 1),
('María', 'Hernández', 'maria.hernandez@cbrands.com', '89012345', '923456321', 9, 1),
('Juan', 'Perez', 'juan.perez@brown-forman.com', '90123456', '934567876', 10, 1);

INSERT INTO producto (nombre, descripcion, cantidad, precio, estado_cantidad, fecha_produccion, fecha_vencimiento, id_categoria, id_proveedor, activo) VALUES
-- Productos con proveedores y categorías válidas
('Johnnie Walker Red Label', 'Whisky escocés de mezcla', 150, 20.00, 1, '2023-01-01', '2025-01-01', 7, 1, 1),
('Bacardi Carta Blanca', 'Ron blanco', 100, 15.00, 1, '2024-01-10', '2025-01-10', 8, 2, 1),
('Heineken Lager', 'Cerveza rubia', 200, 3.00, 1, '2024-02-01', '2025-02-01', 1, 3, 1),
('Gonzalez Byass Tio Pepe', 'Vino de Jerez', 80, 18.00, 1, '2023-06-01', '2024-06-01', 2, 4, 1),
('Moët & Chandon Brut', 'Champán de alta gama', 50, 50.00, 1, '2023-04-15', '2025-04-15', 5, 5, 1),

-- Productos de nuevas categorías y proveedores
('Don Julio Blanco', 'Tequila blanco premium', 120, 45.00, 1, '2023-05-10', '2025-05-10', 6, 6, 1),
('Jameson Irish Whiskey', 'Whisky irlandés suave', 150, 30.00, 1, '2023-03-01', '2027-03-01', 7, 7, 1),
('Captain Morgan Spiced Rum', 'Ron especiado clásico', 200, 25.00, 1, '2024-01-15', '2025-01-15', 8, 6, 1),
('Absolut Vodka', 'Vodka sueco premium', 250, 20.00, 1, '2023-06-20', '2026-06-20', 9, 7, 1),
('Tanqueray London Dry Gin', 'Gin seco de Londres', 180, 35.00, 1, '2023-02-10', '2026-02-10', 10, 6, 1),

-- Más productos variados
('Jack Daniel', 'Whisky americano de Tennessee', 180, 28.00, 1, '2023-04-25', '2027-04-25', 7, 10, 1),
('Corona Extra', 'Cerveza mexicana rubia', 500, 2.50, 1, '2024-01-05', '2025-01-05', 1, 9, 1),
('Angry Orchard Crisp Apple', 'Sidra de manzana refrescante', 150, 5.00, 1, '2023-07-15', '2025-07-15', 11, 9, 1),
('Patrón Silver', 'Tequila premium', 100, 60.00, 1, '2023-05-05', '2026-05-05', 6, 8, 1),
('Smirnoff Vodka', 'Vodka clásico', 300, 15.00, 1, '2023-08-01', '2026-08-01', 9, 7, 1),

-- Productos adicionales para completar 30 registros
('Budweiser', 'Cerveza americana lager', 250, 3.50, 1, '2023-09-10', '2025-09-10', 1, 9, 1),
('Casillero del Diablo Cabernet Sauvignon', 'Vino tinto chileno', 80, 12.00, 1, '2023-02-20', '2026-02-20', 2, 4, 1),
('Baileys Irish Cream', 'Licor cremoso de whisky', 100, 25.00, 1, '2023-03-10', '2025-03-10', 3, 7, 1),
('Grey Goose', 'Vodka francés premium', 90, 45.00, 1, '2023-06-30', '2027-06-30', 9, 7, 1),
('Hennessy VS', 'Cognac francés', 70, 55.00, 1, '2023-05-01', '2028-05-01', 4, 5, 1),

('Jose Cuervo Tradicional', 'Tequila reposado', 200, 25.00, 1, '2023-07-15', '2026-07-15', 6, 6, 1),
('Chivas Regal 12', 'Whisky escocés blended', 150, 40.00, 1, '2023-08-05', '2028-08-05', 7, 1, 1),
('Malibu Coconut', 'Licor de ron con coco', 120, 18.00, 1, '2023-04-20', '2025-04-20', 8, 2, 1),
('Belvedere Vodka', 'Vodka polaco premium', 110, 50.00, 1, '2023-05-30', '2027-05-30', 9, 7, 1),
('Bombay Sapphire', 'Gin inglés premium', 140, 30.00, 1, '2023-09-05', '2026-09-05', 10, 6, 1);

INSERT INTO movimiento_stock (id_movimiento, id_producto, fecha_movimiento, cantidad, tipo_movimiento)
VALUES
    (1, 1, '2024-01-15', 20, 'ENTRADA'),
    (2, 1, '2024-02-10', 30, 'SALIDA'),
    (3, 1, '2024-03-05', 25, 'ENTRADA'),
    (4, 1, '2024-04-12', 15, 'SALIDA'),
    (5, 1, '2024-05-20', 40, 'ENTRADA'),
    (6, 1, '2024-06-25', 10, 'SALIDA'),
    (7, 1, '2024-07-10', 50, 'ENTRADA'),
    (8, 1, '2024-08-15', 20, 'SALIDA'),
    (9, 1, '2024-09-01', 30, 'ENTRADA'),
    (10, 1, '2024-10-18', 40, 'SALIDA'),
    (11, 1, '2024-11-02', 60, 'ENTRADA'),
    (12, 1, '2024-12-05', 20, 'SALIDA'),
    (13, 1, '2024-01-25', 35, 'ENTRADA'),
    (14, 1, '2024-02-18', 45, 'SALIDA'),
    (15, 1, '2024-03-12', 50, 'ENTRADA'),
    (16, 1, '2024-04-20', 30, 'SALIDA'),
    (17, 1, '2024-05-03', 40, 'ENTRADA'),
    (18, 1, '2024-06-12', 20, 'SALIDA'),
    (19, 1, '2024-07-15', 25, 'ENTRADA'),
    (20, 1, '2024-08-07', 35, 'SALIDA'),
    (21, 1, '2024-09-10', 60, 'ENTRADA'),
    (22, 1, '2024-10-22', 10, 'SALIDA'),
    (23, 1, '2024-11-18', 45, 'ENTRADA'),
    (24, 1, '2024-12-03', 30, 'SALIDA'),
    (25, 1, '2024-01-05', 25, 'ENTRADA'),
    (26, 1, '2024-02-22', 40, 'SALIDA'),
    (27, 1, '2024-03-17', 20, 'ENTRADA'),
    (28, 1, '2024-04-30', 35, 'SALIDA'),
    (29, 1, '2024-05-25', 50, 'ENTRADA'),
    (30, 1, '2024-06-28', 30, 'SALIDA'),
    (31, 1, '2024-07-22', 40, 'ENTRADA'),
    (32, 1, '2024-08-10', 20, 'SALIDA'),
    (33, 1, '2024-09-30', 50, 'ENTRADA'),
    (34, 1, '2024-10-14', 30, 'SALIDA'),
    (35, 1, '2024-11-05', 60, 'ENTRADA'),
    (36, 1, '2024-12-01', 20, 'SALIDA'),
    (37, 1, '2024-01-18', 45, 'ENTRADA'),
    (38, 1, '2024-02-03', 25, 'SALIDA'),
    (39, 1, '2024-03-25', 50, 'ENTRADA'),
    (40, 1, '2024-04-14', 30, 'SALIDA'),
    (41, 1, '2024-05-12', 40, 'ENTRADA'),
    (42, 1, '2024-06-19', 20, 'SALIDA'),
    (43, 1, '2024-07-05', 60, 'ENTRADA'),
    (44, 1, '2024-08-25', 10, 'SALIDA'),
    (45, 1, '2024-09-20', 45, 'ENTRADA'),
    (46, 1, '2024-10-09', 35, 'SALIDA'),
    (47, 1, '2024-11-15', 30, 'ENTRADA'),
    (48, 1, '2024-12-10', 50, 'SALIDA'),
    (49, 1, '2024-01-30', 40, 'ENTRADA'),
    (50, 1, '2024-02-28', 30, 'SALIDA');
