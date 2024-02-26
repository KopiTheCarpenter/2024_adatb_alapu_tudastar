package tudastar.Model;

public abstract class DbObject {
    protected Integer ID;
    protected String tablaNeve;

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public String getDeleteQuery() {
        return "delete from " + this.tablaNeve + " where ID = " + this.ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTablaNeve() {
        return tablaNeve;
    }

    public void setTablaNeve(String tablaNeve) {
        this.tablaNeve = tablaNeve;
    }
}
