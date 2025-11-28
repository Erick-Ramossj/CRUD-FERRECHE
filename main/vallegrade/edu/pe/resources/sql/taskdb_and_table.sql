create database ferreche;
use ferreche;


CREATE TABLE clientes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tipo_cliente ENUM('persona','empresa') NOT NULL,

  -- Campos comunes / login
  correo VARCHAR(100) NOT NULL UNIQUE,
  celular VARCHAR(20),
  contrasena VARCHAR(255) NOT NULL,

  -- Campos para persona (usuario)
  nombre VARCHAR(100),
  apellidos VARCHAR(100),
  direccion VARCHAR(200),
  tipo_documento VARCHAR(50),
  numero_documento VARCHAR(50),
  profesion VARCHAR(100),
  -- Campos para empresa
  razon_social VARCHAR(150),
  tipo_empresa VARCHAR(100),
  ruc VARCHAR(20),

  fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    discount_price DECIMAL(10,2) DEFAULT 0,
    image VARCHAR(255),

    genero VARCHAR(10) NOT NULL,
    color VARCHAR(20) NOT NULL,
    rubro VARCHAR(50) NOT NULL
);

CREATE TABLE direcciones (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    calle VARCHAR(100) NOT NULL,
    numero VARCHAR(20),
    distrito VARCHAR(50) NOT NULL,
    provincia VARCHAR(50) NOT NULL,
    departamento VARCHAR(50) NOT NULL,
    referencia VARCHAR(255),
    es_principal BOOLEAN DEFAULT FALSE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
