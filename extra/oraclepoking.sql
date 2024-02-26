SELECT * FROM all_objects WHERE object_type IN ('TABLE') AND OBJECT_NAME ='CIKK';
SELECT SYS_CONTEXT('USERENV', 'CURRENT_SCHEMA') FROM DUAL;
SELECT * FROM v$lock;
delete from cikk where id > 13;

SELECT o.owner, o.object_name, l.session_id, l.locked_mode
FROM dba_objects o, v$locked_object l
WHERE o.object_id = l.object_id;

SELECT o.owner, o.object_name, l.session_id, s.serial#
FROM dba_objects o, v$locked_object l, v$session s
WHERE o.object_id = l.object_id AND l.session_id = s.sid;

SELECT l.session_id, s.serial#
FROM dba_objects o, v$locked_object l, v$session s
where  o.object_id = l.object_id AND l.session_id = s.sid
AND o.object_name = 'CIKK';


SELECT o.object_name,'ALTER SYSTEM KILL SESSION ''' || l.session_id || ',' || s.serial# || ''';'
FROM dba_objects o, v$locked_object l, v$session s
WHERE o.object_id = l.object_id AND l.session_id = s.sid
  AND (o.object_name = 'CIKK'
  OR o.object_name = 'FELHASZNALO'
  OR o.object_name = 'LEKTOR'
  OR o.object_name = 'KATEGORIA'
  OR o.object_name = 'TEMAKOR'
  OR o.object_name = 'NYELVTUDAS'
  OR o.object_name = 'CIKK_KATEGORIA'
  OR o.object_name = 'CIKK_TEMAKOR'
  OR o.object_name = 'HIBA'
  );