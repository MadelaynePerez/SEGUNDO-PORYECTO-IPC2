CREATE DATABASE SalonBelleza;
USE SalonBelleza;

CREATE TABLE rol (
    idRol INT PRIMARY KEY AUTO_INCREMENT,
    nombreRol VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE usuario (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    direccion TEXT,
    password VARCHAR(10) NOT NULL,  
    idRol INT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    descripcion VARCHAR(200) NOT NULL,
    FOREIGN KEY (idRol) REFERENCES rol(idRol) ON DELETE CASCADE
);

CREATE TABLE cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    idUsuario INT UNIQUE NOT NULL,
    dpi VARCHAR(13) UNIQUE NOT NULL,
    hobbi VARCHAR(200),
    gusto VARCHAR(200),
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario) ON DELETE CASCADE
);
ALTER TABLE cliente
ADD COLUMN dpi VARCHAR(13) UNIQUE NOT NULL AFTER idUsuario;


CREATE TABLE empleado (
    id_empleado INT PRIMARY KEY AUTO_INCREMENT,
    idUsuario INT UNIQUE NOT NULL,
    especialidad VARCHAR(100),
    foto_perfil VARCHAR(255),
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario) ON DELETE CASCADE
);

CREATE TABLE servicio (
    id_servicio INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    duracion INT NOT NULL, 
    estado BOOLEAN DEFAULT TRUE,
    ruta_catalogo VARCHAR(255) 
);

CREATE TABLE cita (
    id_cita INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_empleado INT NOT NULL,
    id_servicio INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado ENUM('Pendiente', 'Completada', 'Cancelada') DEFAULT 'Pendiente',
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado) ON DELETE CASCADE,
    FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio) ON DELETE CASCADE
);

CREATE TABLE anuncio (
    id_anuncio INT PRIMARY KEY AUTO_INCREMENT,
    id_marketing INT NOT NULL,
    tipo ENUM('Texto', 'Imagen', 'Video') NOT NULL,
    contenido TEXT NOT NULL,
    url VARCHAR(255),
    duracion_dias INT NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_marketing) REFERENCES usuario(idUsuario) ON DELETE CASCADE
);

CREATE TABLE factura (
    id_factura INT PRIMARY KEY AUTO_INCREMENT,
    id_cita INT NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    fecha_emision TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cita) REFERENCES cita(id_cita) ON DELETE CASCADE
);
