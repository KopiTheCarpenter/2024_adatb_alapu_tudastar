package tudastar.DB;


import oracle.jdbc.datasource.impl.OracleDataSource;
import tudastar.Model.*;

import java.sql.*;
import java.util.ArrayList;

public class DbObjectHandler {
    public static Integer loggedUserID;
    private ResultSet rs;
    private Statement stmt;
    private OracleDataSource ods;
    private Connection conn;
    private String username = "system";
    private String password = "oracle";

    private boolean log =false;

    public DbObjectHandler() {
        try {
            ods = new OracleDataSource();
            Class.forName("oracle.jdbc.OracleDriver");
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean authenticateUser_nonprocedure(String p_email, String p_password) {
        loggedUserID = -1;
        try {
            conn = ods.getConnection(this.username, this.password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("select ID from felhasznalo where email = '%s' AND jelszo = '%s'", p_email, p_password));
            while (rs.next()) {
                loggedUserID = rs.getInt(1);
            }
            if(log)System.out.println("USERID: "+loggedUserID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (rs != null) {
                    if (!rs.isClosed()) {
                        rs.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return loggedUserID != -1;
    }
    public boolean authenticateUser(String p_email, String p_password){
        try {
            conn = ods.getConnection(this.username, this.password);
            CallableStatement cstmt = conn.prepareCall("{CALL authenticate_user(?,?,?)}");
            cstmt.setString(1, p_email);
            cstmt.setString(2, p_password);
            cstmt.registerOutParameter(3, java.sql.Types.NUMERIC);
            cstmt.execute();
            int userId = cstmt.getInt(3);
            loggedUserID = userId;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (rs != null) {
                    if (!rs.isClosed()) {
                        rs.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return loggedUserID != -1;
    }

    public ArrayList<Cikk> readCikk() {
        ArrayList<Cikk> cikkek = new ArrayList<Cikk>();
        try {
            String query = "Select * from CIKK";

            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            if(log)System.out.println(query);
            //System.out.println("Column count: " + rs.getMetaData().getColumnCount());
            while (rs.next()) {
                Cikk c = new Cikk(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
                cikkek.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (rs != null) {
                    if (!rs.isClosed()) {
                        rs.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cikkek;
    }
    public ArrayList<Hiba> readHiba() {
        ArrayList<Hiba> hibak = new ArrayList<Hiba>();
        try {
            String query = "SELECT hiba.id, hiba.bejelentes_ideje, hiba.hiba_leiras, hiba.archiv_e,hiba.BEJELENTO_FELHASZNALO_ID, felhasznalo.nev FROM hiba LEFT JOIN felhasznalo ON hiba.bejelento_felhasznalo_id = felhasznalo.id";
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            if(log)System.out.println(query);
            while (rs.next()) {
                Hiba h = new Hiba(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
                hibak.add(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (rs != null) {
                    if (!rs.isClosed()) {
                        rs.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return hibak;
        }
    }
    public ArrayList<Felhasznalo> readFelhasznalo() {
        ArrayList<Felhasznalo> felhasznalok = new ArrayList<Felhasznalo>();
        try {
            String query = "Select * from felhasznalo";
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            if(log)System.out.println(query);
            while (rs.next()) {
                Felhasznalo f = new Felhasznalo(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),  rs.getInt(5));
                felhasznalok.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (rs != null) {
                    if (!rs.isClosed()) {
                        rs.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return felhasznalok;
    }
    public ArrayList<Lektor> readLektor() {
        ArrayList<Lektor> lektorok = new ArrayList<Lektor>();
        try {
            String query = "SELECT Lektor.id, Felhasznalo.nev,Lektor.tudomanyos_fokozat, Lektor.intezet, Lektor.szakterulet, lektor.FELHASZNALO_ADATOK_ID FROM Lektor JOIN Felhasznalo ON Lektor.felhasznalo_adatok_id = Felhasznalo.id";
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            if(log)System.out.println(query);
            while (rs.next()) {
                Lektor l = new Lektor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),  rs.getInt(6));
                lektorok.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (rs != null) {
                    if (!rs.isClosed()) {
                        rs.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lektorok;
    }
    public ArrayList<Nyelvtudas> readNyelvtudas() {
        ArrayList<Nyelvtudas> nyelvtudasok = new ArrayList<Nyelvtudas>();
        try {
            String query = "SELECT n.id, f.nev, n.nyelv, n.szint, n.lektor_id FROM Nyelvtudas n JOIN Lektor l ON n.lektor_id = l.id JOIN Felhasznalo f ON l.felhasznalo_adatok_id = f.id";
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            if(log)System.out.println(query);
            while (rs.next()) {
                Nyelvtudas ny = new Nyelvtudas(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),  rs.getInt(5));
                nyelvtudasok.add(ny);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (rs != null) {
                    if (!rs.isClosed()) {
                        rs.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nyelvtudasok;
    }
    public ArrayList<Temakor> readTemakor() {
        ArrayList<Temakor> temakorok = new ArrayList<Temakor>();
        try {
            String query = "Select * from temakor";
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            if(log)System.out.println(query);
            while (rs.next()) {
                Temakor t = new Temakor(rs.getInt(1), rs.getString(2), rs.getInt(3));
                temakorok.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (rs != null) {
                    if (!rs.isClosed()) {
                        rs.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return temakorok;
    }
    public ArrayList<Kategoria> readKategoria() {
        ArrayList<Kategoria> kategoriak = new ArrayList<Kategoria>();
        try {
            String query = "Select * from kategoria";
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            if(log)System.out.println(query);
            while (rs.next()) {
                Kategoria k = new Kategoria(rs.getInt(1), rs.getString(2), rs.getInt(3));
                kategoriak.add(k);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (rs != null) {
                    if (!rs.isClosed()) {
                        rs.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return kategoriak;
    }

    public ArrayList<CikkKategoriaTemakor> readCKT(){
        ArrayList<CikkKategoriaTemakor> ckts = new ArrayList<CikkKategoriaTemakor>();
        try {
            String query = "SELECT cikk.cim, kategoria.megnevezes, temakor.megnevezes " +
                    "FROM Cikk cikk " +
                    "LEFT JOIN Cikk_kategoria ck ON cikk.id = ck.cikk_id " +
                    "LEFT JOIN Kategoria kategoria ON ck.kategoria_id = kategoria.id " +
                    "LEFT JOIN Cikk_temakor ct ON cikk.id = ct.cikk_id " +
                    "LEFT JOIN Temakor temakor ON ct.temakor_id = temakor.id";
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            if(log)System.out.println(query);
            while (rs.next()) {
                CikkKategoriaTemakor ckt = new CikkKategoriaTemakor( rs.getString(1), rs.getString(2), rs.getString(3));
                ckts.add(ckt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (rs != null) {
                    if (!rs.isClosed()) {
                        rs.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ckts;
        }
    }

    public boolean insert(DbObject dbo) {
        try {
            String query = dbo.getCreateQuery();
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.executeUpdate(query);
            if(log)System.out.println(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean update(DbObject dbo) {
        try {
            String query = dbo.getUpdateQuery();
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.executeUpdate(query);
            if(log)System.out.println(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean delete(DbObject dbo) {
        try {
            String query = dbo.getDeleteQuery();
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.executeUpdate(query);
            if(log)System.out.println(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public Integer maxID(String tn) {
        try {
            String query = "SELECT MAX(ID) FROM " + tn;
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            if(log) System.out.println(query);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
                if (rs != null) {
                    if (!rs.isClosed()) {
                        rs.close();
                    }
                }
                if (stmt != null) {
                    if (!stmt.isClosed()) {
                        stmt.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static Integer parseInt(String s) {
        Integer i = null;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException n) {
            System.out.println(n);
        }
        return i;
    }


}
