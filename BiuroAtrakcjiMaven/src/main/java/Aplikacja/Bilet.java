package Aplikacja;

public class Bilet {
    private String email;
    private int numerBiletu;
    private Atrakcja atrakcja;
    private String dataZakupu;
    public Bilet(String email, int numerBiletu, String dataZakupu, Atrakcja atrakcja) {
        this.email = email;
        this.numerBiletu = numerBiletu;
        this.dataZakupu = dataZakupu;
        this.atrakcja = atrakcja;
    }

    public int getNumerBiletu() {
        return this.numerBiletu;
    }
    public String getDataAtrakcji()
    {
        return atrakcja.getDataAtrakcji();
    }
}