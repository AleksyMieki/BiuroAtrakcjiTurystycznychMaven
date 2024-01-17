package Aplikacja;

import java.util.Locale;
import java.util.regex.Pattern;

public class MenedzerWiadomosci {

    public void wyslijWiadomoscPracownik(Zgloszenie zgloszenie, String wiadomosc) {

        System.out.println("wyslalem wiadomosc " + wiadomosc + " na zgloszenie o tym temacie: " + zgloszenie.getTemat());
    }
    public void wyslijWiadomosc(Zgloszenie zgloszenie) {
        if(zgloszenie == null){
            throw new NullPointerException();
        }
        if(zgloszenie.getTrescWiadomosci() == null){
            throw new NullPointerException();
        }
        System.out.println("wyslano wiadomosc o podanej tresci " + zgloszenie.getTrescWiadomosci());
    }
    public boolean sprawdzenieMaila(String mail) {

            String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" +
                    "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            if(mail == null) {
                throw new NullPointerException();
            }
            return Pattern.compile(regexPattern).matcher(mail).matches();
    }
    public boolean sprawdzenieTematu(String temat) {

        temat = temat.toLowerCase(Locale.ROOT);


        if(temat.contains("zwrot biletu"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}