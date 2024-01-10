package Aplikacja;

import java.util.Locale;

public class MenedzerWiadomosci {
    public void wyslijWiadomoscPracownik(Zgloszenie zgloszenie, String wiadomosc) {

        System.out.println("wyslalem wiadomosc " + wiadomosc + " na zgloszenie o tym temacie: " + zgloszenie.getTemat());
    }
    public void wyslijWiadomosc(Zgloszenie zgloszenie)
    {
        System.out.println("wyslano wiadomosc o podanej tresci " + zgloszenie.getTrescWiadomosci());
    }
    public boolean sprawdzenieMaila(String mail) {

        if(mail.contains("@")) return true;

        return false;
    }
    public boolean sprawdzenieTematu(String temat) {

        temat.toLowerCase(Locale.ROOT);

        if(temat.equals("zwrot biletu"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}