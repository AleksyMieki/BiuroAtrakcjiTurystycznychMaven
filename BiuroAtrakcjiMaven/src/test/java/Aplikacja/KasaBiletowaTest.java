package Aplikacja;

import mockit.Injectable;
import mockit.Mocked;
import mockit.Expectations;
import mockit.Tested;
import org.hibernate.jdbc.Expectation;
import org.junit.jupiter.api.Test;
import mockit.VerificationsInOrder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;

public class KasaBiletowaTest {

    @Tested
    KasaBiletowa kasaBiletowa;

    @Mocked
    Atrakcja atrakcja;

    @Injectable
    Bilet bilet,bilet1,bilet2,bilet3;

    @Test
    void kupBiletTest() {

        // Action
        kasaBiletowa.kupBilet(atrakcja);
        // Assertion
        assertFalse(kasaBiletowa.getListaBiletow().isEmpty());
    }

    @Test
    void zwrocBiletTest() {

        kasaBiletowa.getListaBiletow().add(bilet);
        // Action
        kasaBiletowa.zwrocBilet(bilet);
        // Assertion
        assertTrue(kasaBiletowa.getListaBiletow().isEmpty());
    }
    @Test
    void testWyszukajBilet()
    {
        kasaBiletowa.getListaBiletow().add(bilet);
        kasaBiletowa.getListaBiletow().add(bilet1);
        kasaBiletowa.getListaBiletow().add(bilet2);
        kasaBiletowa.getListaBiletow().add(bilet3);

        new Expectations()
        {{
            bilet.getNumerBiletu();
            result = 1;
            bilet1.getNumerBiletu();
            result = 2;
            bilet2.getNumerBiletu();
            result = 3;
            bilet3.getNumerBiletu();
            result = 4;
        }};
        Bilet bilety[] = {bilet,bilet1,bilet2,bilet3};

        for(int i = 0 ; i < 4 ; i++)
        {
            assertEquals(kasaBiletowa.wyszukajBilet(bilety[i].getNumerBiletu()),bilety[i]);
        }

    }
    @Test
    void sprawdzDateWydarzeniaTest() {
        // Setup
        new Expectations() {{
            bilet.getDataAtrakcji();
            result = "2024-01-01 10:00:00";
        }};

        // Action
        boolean result = kasaBiletowa.sprawdzDateWydarzenia(bilet);

        // Assertion
        assertFalse(result);
    }
}