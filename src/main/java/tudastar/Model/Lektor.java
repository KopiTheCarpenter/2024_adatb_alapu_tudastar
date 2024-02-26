package tudastar.Model;

public class Lektor extends DbObject {
    private String felhasznaloNev;
    private String tudomanyos_fokozat;
    private String intezet;
    private String szakterulet;
    private Integer felhasznaloAdatokID;

    public Lektor() {
        this.tablaNeve = "Lektor";
    }

    public Lektor(Integer ID,String tudomanyos_fokozat, String intezet, String szakterulet, Integer felhasznaloAdatokID) {
        this.tablaNeve = "Lektor";
        this.ID = ID;
        this.tudomanyos_fokozat = tudomanyos_fokozat;
        this.intezet = intezet;
        this.szakterulet = szakterulet;
        this.felhasznaloAdatokID = felhasznaloAdatokID;
    }

    public Lektor(Integer ID, String felhasznaloNev, String tudomanyos_fokozat, String intezet, String szakterulet, Integer felhasznaloAdatokID) {
        this.tablaNeve = "Lektor";
        this.ID = ID;
        this.tudomanyos_fokozat = tudomanyos_fokozat;
        this.intezet = intezet;
        this.szakterulet = szakterulet;
        this.felhasznaloAdatokID = felhasznaloAdatokID;
        this.felhasznaloNev=felhasznaloNev;
    }

    @Override
    public String getCreateQuery() {
        return String.format("INSERT INTO %s VALUES (%d, '%s','%s','%s', %d)",
                this.tablaNeve, this.ID, this.tudomanyos_fokozat, this.intezet, this.szakterulet, this.felhasznaloAdatokID);
    }

    @Override
    public String getUpdateQuery() {
        return String.format("UPDATE %s SET id = %d, tudomanyos_fokozat = '%s', intezet = '%s', szakterulet = '%s', felhasznalo_adatok_id = %d  WHERE id = %d",
                this.tablaNeve, this.ID, this.tudomanyos_fokozat, this.intezet, this.szakterulet, this.felhasznaloAdatokID, this.ID);
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

    public String getTudomanyos_fokozat() {
        return tudomanyos_fokozat;
    }

    public void setTudomanyos_fokozat(String tudomanyos_fokozat) {
        this.tudomanyos_fokozat = tudomanyos_fokozat;
    }

    public String getIntezet() {
        return intezet;
    }

    public void setIntezet(String intezet) {
        this.intezet = intezet;
    }

    public String getSzakterulet() {
        return szakterulet;
    }

    public void setSzakterulet(String szakterulet) {
        this.szakterulet = szakterulet;
    }

    public Integer getFelhasznaloAdatokID() {
        return felhasznaloAdatokID;
    }

    public void setFelhasznaloAdatokID(Integer felhasznaloAdatokID) {
        this.felhasznaloAdatokID = felhasznaloAdatokID;
    }

    @Override
    public String toString() {
        return "Lektor{" +
                "felhasznaloNev='" + felhasznaloNev + '\'' +
                ", tudomanyos_fokozat='" + tudomanyos_fokozat + '\'' +
                ", intezet='" + intezet + '\'' +
                ", szakterulet='" + szakterulet + '\'' +
                '}';
    }
}
