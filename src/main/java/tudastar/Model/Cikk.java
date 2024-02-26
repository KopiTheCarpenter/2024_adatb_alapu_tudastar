package tudastar.Model;


public class Cikk extends DbObject {
    private String cim;
    private String tartalom;
    private String feltoltesIdeje;
    private String utolsoModositas;
    private Integer modositasokSzama;
    private Integer lektoralte;
    private String nyelv;
    private Integer szerzoID;
    private Integer utolsoModositoID;

    public Cikk() {
        this.tablaNeve = "Cikk";
    }

    public Cikk(Integer ID) {
        this.tablaNeve = "Cikk";
        this.ID = ID;
    }

    public Cikk(Integer ID, String cim, String tartalom, Integer lektoralte, String nyelv, Integer loggeduser) {
        this.tablaNeve = "Cikk";
        this.ID = ID;
        this.cim = cim;
        this.tartalom = tartalom;
        this.lektoralte = lektoralte;
        this.nyelv = nyelv;
        this.utolsoModositoID = loggeduser;
        this.szerzoID = loggeduser;
    }

    public Cikk(Integer ID, String cim, String tartalom, String feltoltesIdeje, String utolsoModositas, Integer modositasokSzama, Integer lektoralte, String nyelv, Integer szerzoID, Integer utolsoModositoID) {
        this.tablaNeve = "Cikk";
        this.ID = ID;
        this.cim = cim;
        this.tartalom = tartalom;
        this.feltoltesIdeje = feltoltesIdeje;
        this.utolsoModositas = utolsoModositas;
        this.modositasokSzama = modositasokSzama;
        this.lektoralte = lektoralte;
        this.nyelv = nyelv;
        this.szerzoID = szerzoID;
        this.utolsoModositoID = utolsoModositoID;
    }

    @Override
    public String getCreateQuery() {
        return String.format("INSERT INTO %s (ID, CIM, TARTALOM, LEKTORALT_E, NYELV, SZERZO_ID) VALUES (%d,'%s','%s',%d,'%s',%d)", this.tablaNeve,this.ID,this.cim,this.tartalom,this.lektoralte,this.nyelv,this.szerzoID);
    }

    @Override
    public String getUpdateQuery() {
        return String.format("UPDATE %s SET ID = %d, cim = '%s', tartalom = '%s',lektoralt_e = %d, nyelv = '%s', utolso_modosito_id = %d where id = %d",this.tablaNeve,this.ID,this.cim,this.tartalom,this.lektoralte,this.nyelv,this.utolsoModositoID,this.ID);
    }

    public Integer getID() {
        return ID;
    }

    public String getCim() {
        return cim;
    }

    public String getTartalom() {
        return tartalom;
    }

    public String getFeltoltesIdeje() {
        return feltoltesIdeje;
    }

    public String getUtolsoModositas() {
        return utolsoModositas;
    }

    public Integer getModositasokSzama() {
        return modositasokSzama;
    }

    public Integer getLektoralte() {
        return lektoralte;
    }

    public String getNyelv() {
        return nyelv;
    }

    public Integer getSzerzoID() {
        return szerzoID;
    }

    public Integer getUtolsoModositoID() {
        return utolsoModositoID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public void setTartalom(String tartalom) {
        this.tartalom = tartalom;
    }

    public void setFeltoltesIdeje(String feltoltesIdeje) {
        this.feltoltesIdeje = feltoltesIdeje;
    }

    public void setUtolsoModositas(String utolsoModositas) {
        this.utolsoModositas = utolsoModositas;
    }

    public void setModositasokSzama(Integer modositasokSzama) {
        this.modositasokSzama = modositasokSzama;
    }

    public void setLektoralte(Integer lektoralte) {
        this.lektoralte = lektoralte;
    }

    public void setNyelv(String nyelv) {
        this.nyelv = nyelv;
    }

    public void setSzerzoID(Integer szerzoID) {
        this.szerzoID = szerzoID;
    }

    public void setUtolsoModositoID(Integer utolsoModositoID) {
        this.utolsoModositoID = utolsoModositoID;
    }

    @Override
    public String toString() {
        return this.cim + "-" + this.ID;
    }
}
