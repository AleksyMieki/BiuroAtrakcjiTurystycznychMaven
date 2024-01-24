package testyfitnessefixture;

import Aplikacja.Atrakcja;

import java.util.Objects;

public class TestUtworzAtrakcje {
    String[] dane;


    public int liczbaAtrakcji() {
        return SetUp.aplikacja.getListaAtrakcji().size();
    }


    public boolean utworzAtrakcjePoAtrybutach() throws NullPointerException {
        try {
            int s1 = SetUp.aplikacja.getListaAtrakcji().size();
            Atrakcja atrakcja = new Atrakcja(dane[0], Float.parseFloat(dane[1]), dane[2], dane[3]);
            SetUp.aplikacja.getListaAtrakcji().add(atrakcja);
            int s2 = SetUp.aplikacja.getListaAtrakcji().size();
            return (Objects.equals(atrakcja.getNazwa(), SetUp.aplikacja.wyszukajAtrakcje(atrakcja.getNazwa()).getNazwa()) && s1 != s2);
        } catch (NullPointerException ignored) {
        }
        return false;
    }
}
