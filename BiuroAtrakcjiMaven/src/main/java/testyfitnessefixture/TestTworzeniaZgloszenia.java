package testyfitnessefixture;

import Aplikacja.Zgloszenie;
import fit.ColumnFixture;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTworzeniaZgloszenia extends ColumnFixture {
    String[] dane;

    public String checkIfNull(String s){
        if(s.equals("\"\"")){
            return null;
        }else{
            return s;
        }
    }

    public boolean dodajZgloszenieDoListy() throws NullPointerException{
        for(int i = 0; i < dane.length; i++){
            dane[i] = checkIfNull(dane[i]);
        }
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataWyslania = data.format(new Date());
        Zgloszenie dodawane = new Zgloszenie(dane[1], dane[0], liczbaZgloszen() + 1, dane[2], dataWyslania);
        try{
            int s1 = liczbaZgloszen();
            SetUp.aplikacja.utworzZgloszenie(dane[0],dane[1],dane[2]);
            int s2 = liczbaZgloszen();
            return (dodawane.getId() == SetUp.aplikacja.getZgloszenieById(liczbaZgloszen()).getId() && s1 != s2);
        }catch (NullPointerException ignored){}
        return false;
    }
    public int liczbaZgloszen(){
        return SetUp.aplikacja.getListaZgloszen().size();
    }


}
