package Prezentacja;

import Aplikacja.Atrakcja;
import Aplikacja.DaneTestowe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InterfejsPracownikaTest implements TestExecutionExceptionHandler {
    static InterfejsUzytkownika instance;
    static DaneTestowe daneTestowe;
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
        if (throwable instanceof NullPointerException) {
            System.out.println("Wprowadzone dane są nieprawidłowe, unikaj wartości NULL");
        } else {
            throw throwable;
        }
    }

    @BeforeAll
    static void setUp() {
        instance = new InterfejsPracownika();
        daneTestowe = new DaneTestowe();
    }

    //Assertions biblioteki JUnit 5 oraz
    //adnotacje: Test, dobraną adnotację
    //TestMethodOrder(OrderAnnotation.class) i Order,
    //ExtendWith razem z obsługą wyjtąków np. TestExecutionExceptionHandler.

    static Stream<Atrakcja> atrakcjaProvider() {
        return Stream.of(new Atrakcja("A", 0.12F, "", "Wrocław"),
                new Atrakcja("B", 0.18F, "2013-12-22 12:12:12", "Wrocław"),
                null);
    }

    @ParameterizedTest
    @ExtendWith(InterfejsPracownikaTest.class)
    @MethodSource("atrakcjaProvider")
    void testWyswietlDaneAtrakcji(Atrakcja atrakcja) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        instance.wyswietlDaneAtrakcji(atrakcja);
        System.setOut(System.out);
        assertEquals("OTO DANE ATRAKCJI\r\n" + atrakcja.getNazwa() + "\r\n" + atrakcja.getCena() + "\r\n"
                + atrakcja.getDataAtrakcji() + "\r\n" + atrakcja.getLokalizacja() + "\r\n", outputStream.toString());
    }

    @ParameterizedTest
    @CsvSource({"piotr.@gmail.com\rpiotr@gmail.com\rLubie wasze atrakcje\rSa super, Sa super"
    , })
    void testWyslijZapytanieDoPracownika(String data, String end) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        Scanner scanner = new Scanner(System.in);
        instance = new InterfejsUzytkownika(scanner);
        instance.wyslijZapytanieDoPracownika();
        System.setOut(System.out);
        System.setIn(System.in);

        assertTrue((outputStream.toString().contains("wyslano wiadomosc o podanej tresci ") && outputStream.toString().endsWith(end + "\r\n")));
    }
}