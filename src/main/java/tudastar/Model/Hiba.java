package tudastar.Model;


public class Hiba extends DbObject {
    private String felhasznaloNev;
    private String bejelentesIdeje;
    private String hibaLeiras;
    private Integer archiv_e;
    private Integer bejelentoFelhasznaloID;

    public Hiba() {
        this.tablaNeve = "Hiba";
    }

    public Hiba(Integer ID, String bejelentesIdeje, String hibaLeiras, Integer archiv_e, Integer bejelentoFelhasznaloID) {
        this.tablaNeve = "Hiba";
        this.ID = ID;
        this.bejelentesIdeje = bejelentesIdeje;
        this.hibaLeiras = hibaLeiras;
        this.archiv_e = archiv_e;
        this.bejelentoFelhasznaloID = bejelentoFelhasznaloID;
    }
    public Hiba(Integer ID, String bejelentesIdeje, String hibaLeiras, Integer archiv_e, Integer bejelentoFelhasznaloID,String felhasznaloNev) {
        this.tablaNeve = "Hiba";
        this.ID = ID;
        this.bejelentesIdeje = bejelentesIdeje;
        this.hibaLeiras = hibaLeiras;
        this.archiv_e = archiv_e;
        this.bejelentoFelhasznaloID = bejelentoFelhasznaloID;
        this.felhasznaloNev = felhasznaloNev;
    }

    public Hiba(Integer ID, String hibaLeiras, Integer bejelentoFelhasznaloID) {
        this.tablaNeve = "Hiba";
        this.ID = ID;
        this.hibaLeiras = hibaLeiras;
        this.bejelentoFelhasznaloID = bejelentoFelhasznaloID;
    }

    @Override
    public String getCreateQuery() {
        return String.format("INSERT INTO %s (id, hiba_leiras, bejelento_felhasznalo_id) VALUES (%d, '%s', %d)",
                this.tablaNeve, this.ID, this.hibaLeiras, this.bejelentoFelhasznaloID);
    }

    @Override
    public String getUpdateQuery() {
        return String.format("UPDATE %s SET archiv_e = 1 where id = %d", this.tablaNeve, this.ID);
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getFelhasznaloNev() {
        return felhasznaloNev;
    }

    public void setFelhasznaloNev(String felhasznaloNev) {
        this.felhasznaloNev = felhasznaloNev;
    }

    public String getBejelentesIdeje() {
        return bejelentesIdeje;
    }

    public void setBejelentesIdeje(String bejelentesIdeje) {
        this.bejelentesIdeje = bejelentesIdeje;
    }

    public String getHibaLeiras() {
        return hibaLeiras;
    }

    public void setHibaLeiras(String hibaLeiras) {
        this.hibaLeiras = hibaLeiras;
    }

    public Integer getArchiv_e() {
        return archiv_e;
    }

    public void setArchiv_e(Integer archiv_e) {
        if (archiv_e > 0) this.archiv_e = archiv_e;
        else this.archiv_e = 0;
    }

    public Integer getBejelentoFelhasznaloID() {
        return bejelentoFelhasznaloID;
    }

    public void setBejelentoFelhasznaloID(Integer bejelentoFelhasznaloID) {
        this.bejelentoFelhasznaloID = bejelentoFelhasznaloID;
    }

    @Override
    public String toString() {
        return ID + " := " + hibaLeiras;
    }
}
