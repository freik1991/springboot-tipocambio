DROP TABLE IF EXISTS TIPOCAMBIO;
CREATE TABLE TIPOCAMBIO(
ID INT AUTO_INCREMENT PRIMARY KEY,
MONTO NUMERIC,
TIPO_CAMBIO DECIMAL,
MONEDA_ORIGEN CHAR(3),
MONEDA_DESTINO CHAR(3),
MONTO_TIPO_CAMBIO DECIMAL,
USUARIO_CREACION VARCHAR(30),
FECHA_CREACION VARCHAR(30),
USUARIO_MODIFICACION VARCHAR(30),
FECHA_MODIFICACION VARCHAR(30)
);

DROP TABLE IF EXISTS USUARIOS;
CREATE TABLE USUARIOS(
ID INT AUTO_INCREMENT PRIMARY KEY,
USUARIO VARCHAR(30),
NOMBRE VARCHAR(30),
APELLIDOS VARCHAR(30),
EMAIL VARCHAR(30),
CLAVE  VARCHAR(30),
ESTADO BOOLEAN,
ROLES VARCHAR2(30)
);

INSERT INTO USUARIOS(USUARIO,NOMBRE,APELLIDOS,EMAIL,CLAVE,ESTADO,ROLES) VALUES('admin','jose','sosa','jose@hotmail.com','111111',true,'ROLE_USER');
INSERT INTO USUARIOS(USUARIO,NOMBRE,APELLIDOS,EMAIL,CLAVE,ESTADO,ROLES) VALUES('fran12','francisco','julca','francisco@gmail.com','222222',true,'ROLE_ADMIN');