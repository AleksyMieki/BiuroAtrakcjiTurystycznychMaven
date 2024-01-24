package testyfitnessefixture;

import Aplikacja.Zgloszenie;
import fit.ColumnFixture;

public class TestGetZgloszenie extends ColumnFixture {
    int id;
    public boolean getZgloszenie() throws IndexOutOfBoundsException{
        try {
            Zgloszenie zgloszenie = SetUp.aplikacja.getZgloszenieById(id);
            return true;
        }catch (IndexOutOfBoundsException ignored){

        }
        return false;
    }
}
