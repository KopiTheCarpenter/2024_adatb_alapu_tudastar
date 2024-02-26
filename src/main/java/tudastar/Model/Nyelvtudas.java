package tudastar.Model;

public class Nyelvtudas extends DbObject {
    private String felhasznaloNev;
    private String nyelv;
    private String szint;
    private Integer lektorID;

    public Nyelvtudas() {
        this.tablaNeve = "Nyelvtudas";
    }

    public Nyelvtudas(Integer ID,String felhasznaloNev, String nyelv, String szint, Integer lektorID) {
        this.tablaNeve = "Nyelvtudas";
        this.ID = ID;
        this.felhasznaloNev = felhasznaloNev;
        this.nyelv = nyelv;
        this.szint = szint;
        this.lektorID = lektorID;
    }

    public Nyelvtudas(Integer ID, String nyelv, String szint, Integer lektorID) {
        this.tablaNeve = "Nyelvtudas";
        this.ID = ID;
        this.nyelv = nyelv;
        this.szint = szint;
        this.lektorID = lektorID;
    }

    @Override
    public String getCreateQuery() {
        return String.format("INSERT INTO %s VALUES (%d, '%s','%s', %d)",
                this.tablaNeve, this.ID, this.nyelv, this.szint, this.lektorID);
    }

    @Override
    public String getUpdateQuery() {
        return String.format("UPDATE %s SET id = %d, nyelv = '%s', szint = '%s', lektor_id = '%d' WHERE id = %d",
                this.tablaNeve, this.ID, this.nyelv, this.szint, this.lektorID, this.ID);
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

    public String getNyelv() {
        return nyelv;
    }

    public void setNyelv(String nyelv) {
        this.nyelv = nyelv;
    }

    public String getSzint() {
        return szint;
    }

    public void setSzint(String szint) {
        this.szint = szint;
    }

    public Integer getLektorID() {
        return lektorID;
    }

    public void setLektorID(Integer lektorID) {
        this.lektorID = lektorID;
    }

    @Override
    public String toString() {
        return "Nyelvtudas{" +
                "felhasznaloNev='" + felhasznaloNev + '\'' +
                ", nyelv='" + nyelv + '\'' +
                ", szint='" + szint + '\'' +
                '}';
    }
}
