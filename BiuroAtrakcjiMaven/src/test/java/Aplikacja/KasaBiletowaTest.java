package Aplikacja;

import mockit.Mocked;
import mockit.Expectations;
import mockit.Tested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KasaBiletowaTest {

    @Tested
    KasaBiletowa kasaBiletowa;

    @Mocked
    Atrakcja atrakcja;

    @Mocked
    Bilet bilet;

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