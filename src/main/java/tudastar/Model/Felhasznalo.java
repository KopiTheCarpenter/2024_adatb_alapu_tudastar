package tudastar.Model;

public class Felhasznalo extends DbObject {
    private String nev;
    private String jelszo;
    private String email;
    private Integer adminisztrator_e;

    public Felhasznalo() {
        this.tablaNeve = "Felhasznalo";
    }

    public Felhasznalo(Integer ID, String nev, String jelszo, String email, Integer adminisztrator_e) {
        this.tablaNeve = "Felhasznalo";
        this.ID = ID;
        this.nev = nev;
        this.jelszo = jelszo;
        this.email = email;
        this.adminisztrator_e = adminisztrator_e;
    }

    @Override
    public String getCreateQuery() {
        return String.format("INSERT INTO %s VALUES (%d, '%s','%s','%s', %d)",
                this.tablaNeve, this.ID, this.nev, this.jelszo, this.email, this.adminisztrator_e);
    }

    @Override
    public String getUpdateQuery() {
        return String.format("UPDATE %s SET id = %d, nev = '%s', jelszo = '%s', email = '%s', adminisztrator_e = %d  WHERE id = %d",
                this.tablaNeve, this.ID, this.nev, this.jelszo, this.email, this.adminisztrator_e, this.ID);
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer isAdminisztrator_e() {
        return adminisztrator_e;
    }

    public void setAdminisztrator_e(Integer adminisztrator_e) {
        if (adminisztrator_e > 0) this.adminisztrator_e = adminisztrator_e;
        else this.adminisztrator_e = 0;
    }

    @Override
    public String toString() {
        return ID + ":= "+this.nev;
    }
}
