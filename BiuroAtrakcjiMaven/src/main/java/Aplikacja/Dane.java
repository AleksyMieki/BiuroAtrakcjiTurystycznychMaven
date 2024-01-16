package Aplikacja;

public class Dane {
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
    public Dane(String nazwa, float cena, String dataAtrakcji, String lokalizacja) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.dataAtrakcji = dataAtrakcji;
        this.lokalizacja = lokalizacja;
    }

    public String getNazwa() {
        return this.nazwa;
    }
    public float getCena() {
        return this.cena;
    }
    public String getDataAtrakcji() {
        return this.dataAtrakcji;
    }
    public String getLokalizacja() {
        return this.lokalizacja;
    }
}