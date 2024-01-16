package Aplikacja;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AplikacjaTest implements TestExecutionExceptionHandler {
    static Aplikacja instance;
    static DaneTestowe daneTestowe;


    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
        if (throwable instanceof NullPointerException) {
            System.out.println("Wprowadzone dane są nieprawidłowe, unikaj wartości NULL");
        } else if (throwable instanceof IndexOutOfBoundsException) {
            System.out.println("Wprowadzone dane są nieprawidłowe, IndexOutOfBoundsException");
        } else {
            throw throwable;
        }
    }

    @BeforeAll
    static void setUp() {
        instance = Aplikacja.getInstance();
        daneTestowe = new DaneTestowe();
    }


    // TestMethodOrder(OrderAnnotation.class) i  Order,

    @Order(1)
    @Tag("tworzenie")
    @ParameterizedTest
    @CsvSource({"niemail", ",", "cos@gmail.pl", "sadada@asdasd.pl"})
    @ExtendWith(AplikacjaTest.class)
    void testyUtworzZgloszenieBounds(String email) {
        // tworzymy wszystkie możliwe kombinacje emaili, tematow i tresci z klasy DaneTestowe
        for (String temat : daneTestowe.tytulyMaili) {
            for (String tresc : daneTestowe.tresciMaili) {
                // sprawdzamy, czy wielkosc listyZgloszen zwiekszyl sie o 1
                int staraWielkoscListyZgloszen = instance.getListaZgloszen().size();
                instance.utworzZgloszenie(email, temat, tresc);
                int nowaWielkoscListyZgloszen = instance.getListaZgloszen().size();
                assertEquals(nowaWielkoscListyZgloszen, staraWielkoscListyZgloszen + 1);

                // sprawdzamy, czy ostatni element to ten nasz wlasnie dodany
                int lastIndex = instance.getListaZgloszen().size() - 1;
                assertEquals(instance.getListaZgloszen().get(lastIndex).getTemat(), temat);
                assertEquals(instance.getListaZgloszen().get(lastIndex).getEmail(), email);
                assertEquals(instance.getListaZgloszen().get(lastIndex).getTrescWiadomosci(), tresc);
            }
        }
    }


    @Order(2)
    @Tag("tworzenie")
    @Test
    void testyUtworzZgloszenieCzasiID() {
        // sprawdza spójnosc danych (zgloszenie o wiekszym id jest wyslane pozniej)
        for (int i = 1; i < instance.getListaZgloszen().size(); i++) {
            Zgloszenie element1 = instance.getListaZgloszen().get(i - 1);
            Zgloszenie element2 = instance.getListaZgloszen().get(i);
            assertTrue(element1.getId() < element2.getId()
                    && element1.getDataWyslania().compareTo(element2.getDataWyslania()) > -1);
        }
    }

    static IntStream intProvider() {
        return IntStream.range(-2, 10);
    }

    @Order(3)
    @ParameterizedTest
    @MethodSource("intProvider")
    @ExtendWith(AplikacjaTest.class)
    void testGetZgloszenieById(int id) {
        // test sprawdza, czy obsługę wyjątku (out of bounds)
        instance.getZgloszenieById(id);
    }


}