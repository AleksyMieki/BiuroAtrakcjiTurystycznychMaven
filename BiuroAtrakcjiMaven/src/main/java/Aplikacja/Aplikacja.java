package Aplikacja;

import java.text.SimpleDateFormat;
import java.util.*;

public class Aplikacja {

    private static Aplikacja instance; // Statyczna instancja

    private ArrayList<Atrakcja> listaAtrakcji = new ArrayList<>();
    private ArrayList<Zgloszenie> listaZgloszen = new ArrayList<>();
    public KasaBiletowa kasaBiletowa = new KasaBiletowa();
    public MenedzerWiadomosci menedzerWiadomosci = new MenedzerWiadomosci();

    public static Aplikacja getInstance() {
        if (instance == null) {
            instance = new Aplikacja();
        }
        return instance;
    }

    private Aplikacja() {
        Dane dane = new Dane("piza", 1, "2023-12-03 10:00:00", "aaa");
        Dane dane2 = new Dane("luwr", 1, "2024-01-17 13:00:00", "aaa");
        Dane dane3 = new Dane("mona", 1, "2023-01-17 14:00:00", "aaa");
        listaAtrakcji.add(new Atrakcja(dane));
        listaAtrakcji.add(new Atrakcja(dane2));
        listaAtrakcji.add(new Atrakcja(dane3));
        Zgloszenie zgloszenie = new Zgloszenie("zwrot biletu", "a@.pl", 1, "tresc wiadomosci numer 1", "2023-12-02 00:00:00");
        Zgloszenie zgloszenie2 = new Zgloszenie("czy jest promka?", "a@.pl", 2, "tresc wiadomosci numer 2", "2023-12-02 00:00:00");
        Zgloszenie zgloszenie3 = new Zgloszenie("kiedy otwarcie", "a@.pl", 3, "tresc wiadomosci numer 3", "2024-01-02 00:00:00");

        kasaBiletowa.kupBilet(listaAtrakcji.get(0));
        kasaBiletowa.kupBilet(listaAtrakcji.get(1));
        kasaBiletowa.kupBilet(listaAtrakcji.get(2));

        listaZgloszen.add(zgloszenie);
        listaZgloszen.add(zgloszenie2);
        listaAtrakcji.add(new Atrakcja(dane));
        listaAtrakcji.add(new Atrakcja(dane2));
        listaAtrakcji.add(new Atrakcja(dane3));
    }

    public Atrakcja wyszukajAtrakcje(String nazwa) {

        Atrakcja znalezionaAtrakcja = listaAtrakcji.stream()
                .filter(atrakcja -> nazwa.equals(atrakcja.getNazwa()))
                .findAny()
                .orElse(null);

        return znalezionaAtrakcja;
    }

    public boolean sprawdzPoprawnosc(Dane dane) {

        if (dane.getLokalizacja() == "luwr" || dane.getLokalizacja() == "Paryz") return true;

        return false;
    }

    public ArrayList<Zgloszenie> getListaZgloszen() {
        return this.listaZgloszen;
    }

    public void usunAtrakcje(Atrakcja atrakcja) {
        listaAtrakcji.remove(atrakcja);
    }


    public void edytujAtrakcje(Atrakcja atrakcja, Dane daneDoEdycji) {
        atrakcja.setNazwa(daneDoEdycji.getNazwa());
        atrakcja.setCena(daneDoEdycji.getCena());
        atrakcja.setDataAtrakcji(daneDoEdycji.getDataAtrakcji());
        atrakcja.setLokalizacja(daneDoEdycji.getLokalizacja());
    }

    public Zgloszenie getZgloszenieById(int id) {
        if (id < 0 || id > this.listaZgloszen.size()) {
            throw new IndexOutOfBoundsException();
        }

        Zgloszenie znalezioneZgloszenie = listaZgloszen.stream()
                .filter(zgloszenie -> id == zgloszenie.getId())
                .findAny()
                .orElse(null);

        return znalezioneZgloszenie;
    }

    public void utworzZgloszenie(String email, String temat, String wiadomosc) {
        if (email == null || (temat == null) || (wiadomosc == null)) {
            throw new NullPointerException();
        }
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataWyslania = data.format(new Date());
        Zgloszenie zgloszenie = new Zgloszenie(temat, email, listaZgloszen.size() + 1, wiadomosc, dataWyslania);
        listaZgloszen.add(zgloszenie);
        menedzerWiadomosci.wyslijWiadomosc(zgloszenie);

    }

    public void utworzAtrakcje(Dane atrybutyAtrakcji) {

        Atrakcja atrakcja = new Atrakcja(atrybutyAtrakcji.getNazwa(), atrybutyAtrakcji.getCena(),
                atrybutyAtrakcji.getDataAtrakcji(), atrybutyAtrakcji.getLokalizacja());

        listaAtrakcji.add(atrakcja);

    }

}