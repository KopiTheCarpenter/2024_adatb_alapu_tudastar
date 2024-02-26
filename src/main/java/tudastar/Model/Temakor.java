package tudastar.Model;

public class Temakor extends DbObject {
    private String megnevezes;
    private Integer foTemakorID;

    public Temakor() {
        this.tablaNeve = "Temakor";
    }

    public Temakor(Integer ID, String megnevezes, Integer foTemakorID) {
        this.tablaNeve = "Temakor";
        this.ID = ID;
        this.megnevezes = megnevezes;
        this.foTemakorID = foTemakorID;
    }


    @Override
    public String getCreateQuery() {
        return String.format("INSERT INTO %s VALUES (%d, '%s', %d)",
                this.tablaNeve, this.ID, this.megnevezes, this.foTemakorID);
    }

    @Override
    public String getUpdateQuery() {
        return String.format("UPDATE %s SET id = %d, megnevezes = '%s',fo_temakor_id = %d  WHERE id = %d",
                this.tablaNeve, this.ID, this.megnevezes, this.foTemakorID, this.ID);
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    public Integer getFoTemakorID() {
        return foTemakorID;
    }

    public void setFoTemakorID(Integer foTemakorID) {
        this.foTemakorID = foTemakorID;
    }

    @Override
    public String toString() {
        return this.ID + ":="+ this.megnevezes;
    }
}
