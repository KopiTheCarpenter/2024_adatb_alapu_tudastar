SELECT Lektor.id, Felhasznalo.nev,Lektor.tudomanyos_fokozat, Lektor.intezet, Lektor.szakterulet, lektor.FELHASZNALO_ADATOK_ID FROM Lektor JOIN Felhasznalo ON Lektor.felhasznalo_adatok_id = Felhasznalo.id;
SELECT n.id, f.nev, n.nyelv, n.szint, n.lektor_id FROM Nyelvtudas n JOIN Lektor l ON n.lektor_id = l.id JOIN Felhasznalo f ON l.felhasznalo_adatok_id = f.id
SELECT hiba.id, hiba.bejelentes_ideje, hiba.hiba_leiras, hiba.archiv_e,hiba.BEJELENTO_FELHASZNALO_ID, felhasznalo.nev FROM hiba LEFT JOIN felhasznalo ON hiba.bejelento_felhasznalo_id = felhasznalo.id

SELECT cikk.cim, kategoria.megnevezes, temakor.megnevezes
FROM Cikk cikk
LEFT JOIN Cikk_kategoria ck ON cikk.id = ck.cikk_id
LEFT JOIN Kategoria kategoria ON ck.kategoria_id = kategoria.id
LEFT JOIN Cikk_temakor ct ON cikk.id = ct.cikk_id
LEFT JOIN Temakor temakor ON ct.temakor_id = temakor.id;

SELECT f.nev, COUNT(*)
FROM cikk c
JOIN felhasznalo f ON c.szerzo_id = f.id
GROUP BY f.nev;

SELECT f.nev, COUNT(*)
FROM nyelvtudas n
JOIN lektor l ON n.lektor_id = l.id
JOIN felhasznalo f ON l.felhasznalo_adatok_id = f.id
GROUP BY f.nev;

select f.email,count(*) from hiba h
LEFT JOIN Felhasznalo f ON f.id = h.bejelento_felhasznalo_id
group by f.email