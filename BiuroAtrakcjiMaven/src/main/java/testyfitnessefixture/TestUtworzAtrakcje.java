package testyfitnessefixture;

import Aplikacja.Atrakcja;
import fit.ColumnFixture;

import java.util.Objects;

public class TestUtworzAtrakcje extends ColumnFixture {
    String[] dane;

    public boolean utworzAtrakcje() {
        int s1 = liczbaAtrakcji();
        Atrakcja atrakcja = new Atrakcja(dane[0], Float.parseFloat(dane[1]), dane[2], dane[3]);
        SetUp.aplikacja.getListaAtrakcji().add(atrakcja);
        int s2 = liczbaAtrakcji();
        return (Objects.equals(atrakcja.getNazwa(), SetUp.aplikacja.wyszukajAtrakcje(atrakcja.getNazwa()).getNazwa()) && s1 != s2);
    }
    public int liczbaAtrakcji() {
        return SetUp.aplikacja.getListaAtrakcji().size();
    }
}
