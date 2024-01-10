package Aplikacja;

public class Zgloszenie {

    private String temat;
    private String email;
    private int id;
    private String trescWiadomosci;
    private String dataWyslania;

    public Zgloszenie(String temat, String email, int id, String trescWiadomosci, String dataWyslania) {
        this.temat = temat;
        this.email = email;
        this.id = id;
        this.trescWiadomosci = trescWiadomosci;
        this.dataWyslania = dataWyslania;
    }

    public String getDataWyslania() {
        return this.dataWyslania;
    }
    public String getTemat() {
        return temat;
    }
    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
    public String getTrescWiadomosci() {
        return trescWiadomosci;
    }
}