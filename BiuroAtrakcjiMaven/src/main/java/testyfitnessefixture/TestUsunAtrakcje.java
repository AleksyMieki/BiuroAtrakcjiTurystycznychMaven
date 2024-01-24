package testyfitnessefixture;

import Aplikacja.Atrakcja;
import fit.ColumnFixture;

import java.util.Objects;

public class TestUsunAtrakcje extends ColumnFixture {
    String nazwa;

    public boolean usunAtrakcje(){
        int s1 = liczbaAtrakcji();
        Atrakcja atrakcja = SetUp.aplikacja.wyszukajAtrakcje(nazwa);
        SetUp.aplikacja.usunAtrakcje(atrakcja);
        int s2 = liczbaAtrakcji();
        return (Objects.equals(null, SetUp.aplikacja.wyszukajAtrakcje(atrakcja.getNazwa())) && s1 != s2);
    }
    public int liczbaAtrakcji() {
        return SetUp.aplikacja.getListaAtrakcji().size();
    }
}
