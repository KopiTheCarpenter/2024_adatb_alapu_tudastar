package tudastar.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class SpeckoController {


    public class SzerzoCikkCount {
        private String szerzo;
        private Integer cikkekSzama;

        public SzerzoCikkCount(String szerzo, Integer cikkekSzama) {
            this.szerzo = szerzo;
            this.cikkekSzama = cikkekSzama;
        }

        public String getSzerzo() {
            return szerzo;
        }

        public void setSzerzo(String szerzo) {
            this.szerzo = szerzo;
        }

        public Integer getCikkekSzama() {
            return cikkekSzama;
        }

        public void setCikkekSzama(Integer cikkekSzama) {
            this.cikkekSzama = cikkekSzama;
        }
    }
    public class LektorNyelvCount {
        private String lektor;
        private Integer nyelvekSzama;

        public LektorNyelvCount(String lektor, Integer nyelvekSzama) {
            this.lektor = lektor;
            this.nyelvekSzama = nyelvekSzama;
        }

        public String getLektor() {
            return lektor;
        }

        public void setLektor(String lektor) {
            this.lektor = lektor;
        }

        public Integer getNyelvekSzama() {
            return nyelvekSzama;
        }

        public void setNyelvekSzama(Integer nyelvekSzama) {
            this.nyelvekSzama = nyelvekSzama;
        }
    }
    public class FelhasznaloHibaCount{
        private String email;
        private Integer hibakSzama;

        public FelhasznaloHibaCount(String email, Integer hibakSzama) {
            this.email = email;
            this.hibakSzama = hibakSzama;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getHibakSzama() {
            return hibakSzama;
        }

        public void setHibakSzama(Integer hibakSzama) {
            this.hibakSzama = hibakSzama;
        }
    }
    private ResultSet rs;
    private Statement stmt;
    private OracleDataSource ods;
    private Connection conn;
    private String username = "system";
    private String password = "oracle";
    @FXML
    private TableView tv_szerzo_cikk_count;
    @FXML
    private TableView tv_lektor_nyelv_count;
    @FXML
    private TableView tv_felhasznalo_hiba_count;

    @FXML
    public void initialize() {
        TableColumn szerzoCol = new TableColumn("szerzo");
        szerzoCol.setCellValueFactory(new PropertyValueFactory<>("szerzo"));
        TableColumn cikkekSzamaCol = new TableColumn("cikkekSzama");
        cikkekSzamaCol.setCellValueFactory(new PropertyValueFactory<>("cikkekSzama"));
        tv_szerzo_cikk_count.getColumns().addAll(szerzoCol, cikkekSzamaCol);

        TableColumn nyelvCol = new TableColumn("lektor");
        nyelvCol.setCellValueFactory(new PropertyValueFactory<>("lektor"));
        TableColumn lektorokSzamaCol = new TableColumn("nyelvekSzama");
        lektorokSzamaCol.setCellValueFactory(new PropertyValueFactory<>("nyelvekSzama"));
        tv_lektor_nyelv_count.getColumns().addAll(nyelvCol, lektorokSzamaCol);

        TableColumn nevCol = new TableColumn("email");
        nevCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn hibakSzamaCol = new TableColumn("hibakSzama");
        hibakSzamaCol.setCellValueFactory(new PropertyValueFactory<>("hibakSzama"));
        tv_felhasznalo_hiba_count.getColumns().addAll(nevCol, hibakSzamaCol);
    }

    public ArrayList<SzerzoCikkCount> readSzerzoCikkCount() {
        ArrayList<SzerzoCikkCount> list = new ArrayList<SzerzoCikkCount>();
        try {
            String query = "SELECT f.nev, COUNT(*) FROM cikk c JOIN felhasznalo f ON c.szerzo_id = f.id GROUP BY f.nev";
            ods = new OracleDataSource();
            Class.forName("oracle.jdbc.OracleDriver");
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                SzerzoCikkCount c = new SzerzoCikkCount(rs.getString(1), rs.getInt(2));
                list.add(c);
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
        return list;
    }
    private ArrayList<LektorNyelvCount> readLektorNyelvCount() {
        ArrayList<LektorNyelvCount> list = new ArrayList<LektorNyelvCount>();
        try {
            String query = "SELECT f.nev, COUNT(*) FROM nyelvtudas n JOIN lektor l ON n.lektor_id = l.id JOIN felhasznalo f ON l.felhasznalo_adatok_id = f.id GROUP BY f.nev";
            ods = new OracleDataSource();
            Class.forName("oracle.jdbc.OracleDriver");
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                LektorNyelvCount c = new LektorNyelvCount(rs.getString(1), rs.getInt(2));
                list.add(c);
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
        return list;
    }
    private ArrayList<FelhasznaloHibaCount> readFelhasznaloHibaCount() {
        ArrayList<FelhasznaloHibaCount> list = new ArrayList<FelhasznaloHibaCount>();
        try {
            String query = "select f.email,count(*) from hiba h LEFT JOIN Felhasznalo f ON f.id = h.bejelento_felhasznalo_id group by f.email";
            ods = new OracleDataSource();
            Class.forName("oracle.jdbc.OracleDriver");
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            conn = ods.getConnection(username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                FelhasznaloHibaCount c = new FelhasznaloHibaCount(rs.getString(1), rs.getInt(2));
                list.add(c);
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
        return list;
    }
    public void pressSzerzoCikkCount(ActionEvent actionEvent) {
        ArrayList<SzerzoCikkCount> list = readSzerzoCikkCount();
        tv_szerzo_cikk_count.getItems().clear();
        tv_szerzo_cikk_count.getItems().addAll(list);
    }

    public void pressLektorLanguageCount(ActionEvent actionEvent) {
        ArrayList<LektorNyelvCount> list = readLektorNyelvCount();
        tv_lektor_nyelv_count.getItems().clear();
        tv_lektor_nyelv_count.getItems().addAll(list);
    }
    public void pressFelhasznaloHibaCount(ActionEvent actionEvent) {
        ArrayList<FelhasznaloHibaCount> list = readFelhasznaloHibaCount();
        tv_felhasznalo_hiba_count.getItems().clear();
        tv_felhasznalo_hiba_count.getItems().addAll(list);

    }



}
