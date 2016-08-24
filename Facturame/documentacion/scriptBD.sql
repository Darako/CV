-- Table: "Persona"

-- DROP TABLE "Persona";

CREATE TABLE "Persona"
(
  dni character varying(10) NOT NULL,
  nombre character varying(20) NOT NULL,
  apellidos character varying(60) NOT NULL,
  "fechaNacimiento" date NOT NULL,
  sexo character varying(20) NOT NULL,
  CONSTRAINT "personaPK" PRIMARY KEY (dni)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Persona"
  OWNER TO postgres;


---------------------------------------------------------------

-- Table: "Empleado"

-- DROP TABLE "Empleado";

CREATE TABLE "Empleado"
(
  "fechaAltaEmpleado" date NOT NULL,
  sueldo double precision NOT NULL,
  rango character varying(20) NOT NULL,
  CONSTRAINT "empleadoPK" PRIMARY KEY (dni)
)
INHERITS ("Persona")
WITH (
  OIDS=FALSE
);
ALTER TABLE "Empleado"
  OWNER TO postgres;


---------------------------------------------------------------

-- Table: "Empresa"

-- DROP TABLE "Empresa";

CREATE TABLE "Empresa"
(
  "NIF" character varying(9) NOT NULL,
  "nEmpresa" character varying(30),
  "Dirección" character varying(50),
  email character varying(200),
  telefono integer,
  CONSTRAINT "empresaPK" PRIMARY KEY ("NIF")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Empresa"
  OWNER TO postgres;


---------------------------------------------------------------

-- Table: "Camion"

-- DROP TABLE "Camion";

CREATE TABLE "Camion"
(
  "nBastidor" character varying(17) NOT NULL,
  matricula character varying(8) NOT NULL,
  combustible character varying(20) NOT NULL,
  "nPasajeros" bigint NOT NULL,
  "potenciaCV" bigint NOT NULL,
  "potenciaKWh" bigint NOT NULL,
  "kmTotales" bigint NOT NULL,
  peso bigint NOT NULL,
  largo double precision NOT NULL,
  ancho double precision NOT NULL,
  "longCaja" double precision NOT NULL,
  "anchoCaja" double precision NOT NULL,
  "pesoMaxCaja" double precision NOT NULL,
  "volumenCaja" double precision NOT NULL,
  trampilla boolean NOT NULL,
  descripcion character varying(60) NOT NULL,
  "altoCaja" double precision NOT NULL,
  galibo double precision NOT NULL,
  CONSTRAINT "camionPK" PRIMARY KEY ("nBastidor")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Camion"
  OWNER TO postgres;


---------------------------------------------------------------

-- Table: "Porte"

-- DROP TABLE "Porte";

CREATE TABLE "Porte"
(
  "idPorte" serial NOT NULL,
  "nBastidor" character varying(17) NOT NULL,
  dni character(10) NOT NULL,
  "kgCarga" bigint NOT NULL,
  "volumenCarga" bigint NOT NULL,
  concepto character varying(50) NOT NULL,
  precio bigint NOT NULL,
  "esGrupaje" boolean NOT NULL,
  descripcion character varying(60),
  "NIF" character(9),
  CONSTRAINT "portePK" PRIMARY KEY ("idPorte"),
  CONSTRAINT conduce FOREIGN KEY (dni)
      REFERENCES "Empleado" (dni) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT portepara FOREIGN KEY ("NIF")
      REFERENCES "Empresa" ("NIF") MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT realizado FOREIGN KEY ("nBastidor")
      REFERENCES "Camion" ("nBastidor") MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Porte"
  OWNER TO postgres;

-- Index: "dniFK"

-- DROP INDEX "dniFK";

CREATE INDEX "dniFK"
  ON "Porte"
  USING btree
  (dni COLLATE pg_catalog."default");

-- Index: "nBastidorFK"

-- DROP INDEX "nBastidorFK";

CREATE INDEX "nBastidorFK"
  ON "Porte"
  USING btree
  ("nBastidor" COLLATE pg_catalog."default");

---------------------------------------------------------------

-- Table: "UsuarioSistema"

-- DROP TABLE "UsuarioSistema";

CREATE TABLE "UsuarioSistema"
(
  dni character varying(20) NOT NULL,
  nickname character varying(20) NOT NULL UNIQUE,
  "hashContrasena" character varying(50) NOT NULL,
  admin boolean NOT NULL,
  "fechaAltaUsuario" date NOT NULL,
  CONSTRAINT "usSisPK" PRIMARY KEY (dni),
  CONSTRAINT esde FOREIGN KEY (dni)
      REFERENCES "Empleado" (dni) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "UsuarioSistema"
  OWNER TO postgres;

-- Index: "dniUsSisFK"

-- DROP INDEX "dniUsSisFK";

CREATE INDEX "dniUsSisFK"
  ON "UsuarioSistema"
  USING btree
  (dni COLLATE pg_catalog."default");


---------------------------------------------------------------

-- Table: "Viaje"

-- DROP TABLE "Viaje";

CREATE TABLE "Viaje"
(
  "idViaje" serial NOT NULL,
  "lugarInicio" character varying(40) NOT NULL,
  "lugarDestino" character varying(40) NOT NULL,
  "horaInico" time with time zone NOT NULL,
  "horaLlegada" time with time zone NOT NULL,
  "fechaInicio" date NOT NULL,
  "fechaLlegada" date NOT NULL,
  "kmViaje" bigint NOT NULL,
  "idPorte" integer NOT NULL,
  CONSTRAINT "viajePK" PRIMARY KEY ("idViaje"),
  CONSTRAINT "incluyeDatos" FOREIGN KEY ("idPorte")
      REFERENCES "Porte" ("idPorte") MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Viaje"
  OWNER TO postgres;

-- Index: "porteFK"

-- DROP INDEX "porteFK";

CREATE INDEX "porteFK"
  ON "Viaje"
  USING btree
  ("idPorte");


---------------------------------------------------------------

-- Table: "Subordinado"

-- DROP TABLE "Subordinado";

CREATE TABLE "Subordinado"
(
  "dniSubordinado" character varying(17) NOT NULL,
  "dniJefe" character varying(17) NOT NULL,
  CONSTRAINT "subordinadoPK" PRIMARY KEY ("dniSubordinado", "dniJefe"),
  CONSTRAINT "dniJefeFK" FOREIGN KEY ("dniJefe")
      REFERENCES "Empleado" (dni) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT "dniSubFK" FOREIGN KEY ("dniSubordinado")
      REFERENCES "Empleado" (dni) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Subordinado"
  OWNER TO postgres;

--inserciones en la BBDD para poder usar la aplicación.

INSERT INTO public."Empleado"(
            dni, nombre, apellidos, "fechaNacimiento", sexo, "fechaAltaEmpleado", 
            sueldo, rango)
    VALUES ('0000000000', 'usuario1', 'prueba', now(), 'Masculino', now(), 
            1500, 'BECARIO');

INSERT INTO public."Empleado"(
            dni, nombre, apellidos, "fechaNacimiento", sexo, "fechaAltaEmpleado", 
            sueldo, rango)
    VALUES ('1111111111', 'usuario2', 'prueba', now(), 'Femenino', now(), 
            1500, 'BECARIO');

INSERT INTO public."UsuarioSistema"(
            dni, nickname, "hashContrasena", admin, "fechaAltaUsuario")
    VALUES ('0000000000', 'admin', '21232f297a57a5a743894a0e4a801fc3', true, now());

INSERT INTO public."UsuarioSistema"(
            dni, nickname, "hashContrasena", admin, "fechaAltaUsuario")
    VALUES ('1111111111', 'noAdmin', '9ac729301a5194c1d7db12342d26dae1', false, now());



