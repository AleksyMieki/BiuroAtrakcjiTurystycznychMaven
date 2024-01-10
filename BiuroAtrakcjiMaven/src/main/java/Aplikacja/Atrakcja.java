package Aplikacja;
public class Atrakcja {
    private String nazwa;
    private float cena;
    private String dataAtrakcji;
    private String lokalizacja;

    /**
     * @param nazwa
     * @param cena
     * @param dataAtrakcji
     * @param lokalizacja
     */
    public Atrakcja(String nazwa, float cena, String dataAtrakcji, String lokalizacja) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.dataAtrakcji = dataAtrakcji;
        this.lokalizacja = lokalizacja;
    }
    public Atrakcja(Dane atrybutyAtrakcji) {
        this.nazwa = atrybutyAtrakcji.getNazwa();
        this.cena = atrybutyAtrakcji.getCena();
        this.dataAtrakcji = atrybutyAtrakcji.getDataAtrakcji();
        this.lokalizacja = atrybutyAtrakcji.getLokalizacja();
    }
    public String getNazwa() {
        return this.nazwa;
    }

    /**
     * @param nazwa
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public float getCena() {
        return this.cena;
    }
    public void setCena(float cena) {
        this.cena = cena;
    }
    public String getDataAtrakcji() {
        return this.dataAtrakcji;
    }
    public void setDataAtrakcji(String czasOtwarcia) {
        this.dataAtrakcji = czasOtwarcia;
    }
    public String getLokalizacja() {
        return this.lokalizacja;
    }
    public void setLokalizacja(String lokalizacja) {
        this.lokalizacja = lokalizacja;
    }
}