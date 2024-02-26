package tudastar.Model;

public class Kategoria extends DbObject {
    private String megnevezes;
    private Integer foKategoriaID;

    public Kategoria() {
        this.tablaNeve = "Kategoria";
    }

    public Kategoria(Integer ID, String megnevezes, Integer foKategoriaID) {
        this.tablaNeve = "Kategoria";
        this.ID = ID;
        this.megnevezes = megnevezes;
        this.foKategoriaID = foKategoriaID;
    }

    @Override
    public String getCreateQuery() {
        return String.format("INSERT INTO %s VALUES (%d, '%s', %d)",
                this.tablaNeve, this.ID, this.megnevezes, this.foKategoriaID);
    }

    @Override
    public String getUpdateQuery() {
        return String.format("UPDATE %s SET id = %d, megnevezes = '%s',fo_kategoria_id = %d  WHERE id = %d",
                this.tablaNeve, this.ID, this.megnevezes, this.foKategoriaID, this.ID);
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

    public Integer getFoKategoriaID() {
        return foKategoriaID;
    }

    public void setFoKategoriaID(Integer foKategoriaID) {
        this.foKategoriaID = foKategoriaID;
    }

    @Override
    public String toString() {
        return this.ID + ":="+ this.megnevezes;
    }
}
