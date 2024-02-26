create or replace TRIGGER hibaTrig
BEFORE INSERT ON hiba
FOR EACH ROW
BEGIN
    :new.BEJELENTES_IDEJE := TO_CHAR(SYSDATE, 'YYYY-MM-DD');
END;

create or replace TRIGGER cikkTrig
BEFORE INSERT OR UPDATE ON cikk
FOR EACH ROW
BEGIN
IF INSERTING THEN
    :new.FELTOLTES_IDEJE := TO_CHAR(SYSDATE, 'YYYY-MM-DD');
ELSIF UPDATING THEN
    :new.UTOLSO_MODOSITAS_IDEJE := TO_CHAR(SYSDATE, 'YYYY-MM-DD');
    :new.modositasok_szama := :old.modositasok_szama+1;
END IF;
END;

CREATE OR REPLACE PROCEDURE authenticate_user(
    p_email IN VARCHAR2,
    p_password IN VARCHAR2,
    p_user_id OUT NUMBER
) AS
BEGIN
    SELECT id INTO p_user_id FROM felhasznalo WHERE email = p_email AND jelszo = p_password;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_user_id := -1; -- invalid credentials
END;
