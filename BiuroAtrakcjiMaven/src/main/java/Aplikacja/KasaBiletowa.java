package Aplikacja;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class KasaBiletowa {
    private ArrayList<Bilet> listaBiletow = new ArrayList<>();
    public boolean sprawdzDateWydarzenia(Bilet bilet) {

        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dataNaBilecie = null;
        String dateString = data.format(new Date());
        Date teraz = null;

        try {
            dataNaBilecie = data.parse(bilet.getDataAtrakcji());

            teraz = data.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long timeDifference = dataNaBilecie.getTime() - teraz.getTime();
        // o choldera - te modulo przerzuca ujemne wartosci w dodatnie
        long roznicaDni = (timeDifference / (1000*60*60*24)) % 365;

        if(roznicaDni > 0)
        {
            System.out.println(bilet.getDataAtrakcji());
            System.out.println(dateString);
            return true;
        }
        return false;
    }
    public void zwrocBilet(Bilet znalezionyBilet) {
        listaBiletow.remove(znalezionyBilet);
        System.out.println("Zwrocono bilet poprzez zwroc bilet" );

    }
    public void kupBilet(Atrakcja atrakcja) {

        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = data.format(new Date());
        listaBiletow.add(new Bilet("a@gmail.com",listaBiletow.size()+1,dateString, atrakcja));

    }
    public Bilet wyszukajBilet(int id) {

        Bilet biletDoZwrotu = listaBiletow.stream()
                .filter(bilet -> id == bilet.getNumerBiletu())
                .findAny()
                .orElse(null);
        return biletDoZwrotu;
    }
}