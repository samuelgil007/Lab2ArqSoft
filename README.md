# Lab2ArqSoft


Este es el script que se debe usar en la base de datos:
CREATE TABLE transaccion(
id_transaccion int PRIMARY KEY NOT NULL AUTO_INCREMENT,
id_usuario VARCHAR(20) NOT NULL,
nom_usuario VARCHAR(80) NOT NULL,
email_usuario VARCHAR(60) NOT NULL,
numero_tarjeta VARCHAR(20) NOT NULL,
csv_tarjeta VARCHAR(20) NOT NULL,
tipo_tarjeta VARCHAR(30) NOT NULL,
expiracion_tarj VARCHAR(20) NOT NULL,
valor_pago DOUBLE NOT NULL,
fecha_pago VARCHAR(20) NOT NULL,
ref_pago VARCHAR(200) NOT NULL
);

Se debe hacer la conexion a la base de datos donde haya creado la tabla.
