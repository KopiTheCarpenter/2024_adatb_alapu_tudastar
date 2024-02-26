CREATE TABLE Felhasznalo (
  id number,
  nev varchar(255) NOT NULL,
  jelszo varchar(255),
  email varchar(255) NOT NULL,
  adminisztrator_e number(1),
  CONSTRAINT felhasznalo_pk PRIMARY KEY (id),
  CONSTRAINT egyedi_email UNIQUE(email)
);
CREATE TABLE Cikk(
  id NUMBER,
  cim varchar(255) NOT NULL,
  tartalom varchar(255),
  feltoltes_ideje date,
  utolso_modositas_ideje date,
  modositasok_szama number,
  lektoralt_e number,
  nyelv varchar(255),
  szerzo_id number,
  utolso_modosito_id number,
  CONSTRAINT cikk_pk PRIMARY KEY (id),
  CONSTRAINT egyedi_cim UNIQUE(cim),
  CONSTRAINT cikk_fk FOREIGN KEY (utolso_modosito_id) REFERENCES felhasznalo(id)
);
CREATE TABLE Lektor (
  id number,
  tudomanyos_fokozat varchar(255),
  intezet varchar(255),
  szakterulet varchar(255),
  felhasznalo_adatok_id number,
  CONSTRAINT lektor_pk PRIMARY KEY (id),
  CONSTRAINT lektor_fk FOREIGN KEY (felhasznalo_adatok_id) REFERENCES felhasznalo(id)
);
CREATE TABLE Kategoria(
  id number,
  megnevezes varchar(255),
  fo_kategoria_id number,
  CONSTRAINT kategoria_pk PRIMARY KEY (id),
  CONSTRAINT kategoria_fk FOREIGN KEY (fo_kategoria_id) REFERENCES kategoria(id)
);
CREATE TABLE Temakor(
  id number,
  megnevezes varchar(255),
  fo_temakor_id number,
  CONSTRAINT temakor_pk PRIMARY KEY (id),
  CONSTRAINT temakor_fk FOREIGN KEY (fo_temakor_id) REFERENCES temakor(id)
);
CREATE TABLE Nyelvtudas(
  id number,
  nyelv varchar(255),
  szint varchar(255),
  lektor_id number,
  CONSTRAINT nyelvtudas_pk PRIMARY KEY (id),
  CONSTRAINT nyelvtudas_fk FOREIGN KEY (lektor_id) REFERENCES lektor(id)
);
CREATE TABLE Hiba(
  id number,
  bejelentes_ideje date,
  hiba_leiras varchar(255),
  archiv_e number(1),
  bejelento_felhasznalo_id number,
  CONSTRAINT hiba_pk PRIMARY KEY (id),
  CONSTRAINT hiba_fk FOREIGN KEY (bejelento_felhasznalo_id) REFERENCES felhasznalo(id)
);
CREATE TABLE Cikk_kategoria(
  id number,
  cikk_id number,
  kategoria_id number,
  CONSTRAINT cikk_kategoria_pk PRIMARY KEY (id),
  CONSTRAINT cikkkc_fk FOREIGN KEY (cikk_id) REFERENCES cikk(id),
  CONSTRAINT cikkkk_fk FOREIGN KEY (kategoria_id) REFERENCES kategoria(id)
);
CREATE TABLE Cikk_temakor(
  id number,
  cikk_id number,
  temakor_id number,
  CONSTRAINT cikk_temakor_pk PRIMARY KEY (id),
  CONSTRAINT cikktc_fk FOREIGN KEY (cikk_id) REFERENCES cikk(id),
  CONSTRAINT cikktt_fk FOREIGN KEY (temakor_id) REFERENCES temakor(id)
);