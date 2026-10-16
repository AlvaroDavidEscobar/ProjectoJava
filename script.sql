BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "clientes" (
	"id_cliente"	integer,
	"nombre"	text,
	"documento"	text,
	"telefono"	text,
	PRIMARY KEY("id_cliente" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "empleados" (
	"id_empleado"	INTEGER,
	"nombre"	text,
	"documento"	text,
	"tipo"	integer,
	"salario"	real,
	"direccion"	text,
	"usuario"	TEXT,
	"contrasena"	TEXT,
	PRIMARY KEY("id_empleado" AUTOINCREMENT),
	FOREIGN KEY("tipo") REFERENCES "tipos_empleado"("id_tipo")
);
CREATE TABLE IF NOT EXISTS "paquetes" (
	"id_paquete"	integer,
	"nombre"	text,
	"ciudad"	text,
	"pais"	text,
	"estado"	integer,
	"descripcion"	text,
	"precio"	real,
	"dias_duracion"	integer,
	PRIMARY KEY("id_paquete" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "tipos_empleado" (
	"id_tipo"	integer,
	"nombre"	text,
	PRIMARY KEY("id_tipo" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "ventas" (
	"id_venta"	integer,
	"cliente"	integer,
	"paquete"	integer,
	"empleado"	integer,
	"fecha"	date,
	PRIMARY KEY("id_venta" AUTOINCREMENT),
	FOREIGN KEY("cliente") REFERENCES "clientes"("id_cliente"),
	FOREIGN KEY("empleado") REFERENCES "empleados"("id_empleado"),
	FOREIGN KEY("paquete") REFERENCES "paquetes"("id_paquete")
);
INSERT INTO "clientes" VALUES (1,'La Llorona','432673889','345324543');
INSERT INTO "clientes" VALUES (2,'Vegeta','345345','3004959292');
INSERT INTO "clientes" VALUES (3,'Freezer','70043352','3225564325');
INSERT INTO "clientes" VALUES (4,'Master Chief','676534543','3667436734');
INSERT INTO "clientes" VALUES (5,'Xi Jing Ping','234324542','6587622443');
INSERT INTO "empleados" VALUES (1,'Goku','1004847392',1,12000000.0,'Casa de Milk','Admin','12345');
INSERT INTO "empleados" VALUES (2,'Patricio Estrella','1000000003',2,2300000.0,'Piedra debajo del mar','Patricio','Rocky');
INSERT INTO "empleados" VALUES (3,'Krillin','545645645',2,1600000.0,'Avenida del rio','Calvo','Dragon');
INSERT INTO "empleados" VALUES (4,'Kratos','67',2,3000000.0,'Esparta','GodOfWar','Atena');
INSERT INTO "paquetes" VALUES (1,'Paraiso en las Islas','San Andres','Colombia',1,'Disfrute de las playas paradisiacas de la region insular de Colombia con hotel, transporte y comida incluidos.',5000000.0,3);
INSERT INTO "paquetes" VALUES (2,'Dias de Playa en Malta','Valleta','Malta',1,'Visite las famosas playas de la isla de malta y visite los museos y sitios historicos.',2500000.0,2);
INSERT INTO "paquetes" VALUES (3,'Recorrido de Sitios Historicos en Atenas','Atenas','Grecia',0,'Recorrido guiado por todos los sitios historicos de la ciudad de Atenas, sus grandiosas ruinas de la ciudad antigua y sus numerosos museos, hotel y transpote incluido.',6500000.0,5);
INSERT INTO "paquetes" VALUES (4,'Templos de Tailandia','Bangkok','Tailandia',1,'Viaje por los templos y palacios mas famosos de la ciuidad de Bangkok.',2000000.0,4);
INSERT INTO "tipos_empleado" VALUES (1,'Admin');
INSERT INTO "tipos_empleado" VALUES (2,'Asesor');
INSERT INTO "ventas" VALUES (1,4,1,2,'2026-08-29');
INSERT INTO "ventas" VALUES (2,2,4,4,'2026-09-10');
INSERT INTO "ventas" VALUES (3,1,3,3,'2026-09-13');
INSERT INTO "ventas" VALUES (4,3,2,2,'2026-09-16');
INSERT INTO "ventas" VALUES (5,4,3,2,'2026-09-27');
INSERT INTO "ventas" VALUES (6,2,1,4,'2026-10-04');
INSERT INTO "ventas" VALUES (7,5,4,3,'2026-10-15');
COMMIT;
