package tudastar.Model;

public class CikkKategoriaTemakor {
    private String cim;
    private String temakor;
    private String kategoria;

    public CikkKategoriaTemakor(String cim, String temakor, String kategoria) {
        this.cim = cim;
        this.temakor = temakor;
        this.kategoria = kategoria;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getTemakor() {
        return temakor;
    }

    public void setTemakor(String temakor) {
        this.temakor = temakor;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }
}
