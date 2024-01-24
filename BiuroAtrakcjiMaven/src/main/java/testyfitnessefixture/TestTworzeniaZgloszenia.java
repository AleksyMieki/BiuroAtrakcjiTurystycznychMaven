package testyfitnessefixture;

import Aplikacja.Zgloszenie;
import fit.ColumnFixture;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class TestTworzeniaZgloszenia extends ColumnFixture {
    String[] dane;

    public boolean dodajZgloszenieDoListy(){
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataWyslania = data.format(new Date());
        Zgloszenie dodawane = new Zgloszenie(dane[1], dane[0], liczbaZgloszen() + 1, dane[2], dataWyslania);
        int s1 = liczbaZgloszen();
        SetUp.aplikacja.utworzZgloszenie(dane[0],dane[1],dane[2]);
        int s2 = liczbaZgloszen();
        return (dodawane.getId() == SetUp.aplikacja.getZgloszenieById(liczbaZgloszen()).getId() && s1 != s2);
    }
    public int liczbaZgloszen(){
        return SetUp.aplikacja.getListaZgloszen().size();
    }
}
