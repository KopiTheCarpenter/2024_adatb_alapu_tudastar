package tudastar.Model;

public class CikkKategoria extends DbObject {
    private Integer CikkID;
    private Integer KategoriaID;

    public CikkKategoria(Integer ID, Integer cikkID, Integer kategoriaID) {
        this.ID = ID;
        CikkID = cikkID;
        KategoriaID = kategoriaID;
    }

    @Override
    public String getCreateQuery() {
        //TODO:
        return null;
    }

    @Override
    public String getUpdateQuery() {
        //TODO:
        return null;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getCikkID() {
        return CikkID;
    }

    public void setCikkID(Integer cikkID) {
        CikkID = cikkID;
    }

    public Integer getKategoriaID() {
        return KategoriaID;
    }

    public void setKategoriaID(Integer kategoriaID) {
        KategoriaID = kategoriaID;
    }

    @Override
    public String toString() {
        return "CikkKategoria{" +
                "ID=" + ID +
                ", CikkID=" + CikkID +
                ", KategoriaID=" + KategoriaID +
                '}';
    }
}
