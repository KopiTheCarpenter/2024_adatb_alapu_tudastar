package tudastar.Model;

public class CikkTemakor extends DbObject {
    private Integer CikkID;
    private Integer TemakorID;

    public CikkTemakor(Integer ID, Integer cikkID, Integer temakorID) {
        this.ID = ID;
        CikkID = cikkID;
        TemakorID = temakorID;
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
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

    public Integer getTemakorID() {
        return TemakorID;
    }

    public void setTemakorID(Integer temakorID) {
        TemakorID = temakorID;
    }

    @Override
    public String toString() {
        return "CikkTemakor{" +
                "ID=" + ID +
                ", CikkID=" + CikkID +
                ", TemakorID=" + TemakorID +
                '}';
    }
}
