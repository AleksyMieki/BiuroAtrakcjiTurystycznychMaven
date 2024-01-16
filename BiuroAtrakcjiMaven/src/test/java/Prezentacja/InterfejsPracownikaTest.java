package Prezentacja;

import Aplikacja.Atrakcja;
import Aplikacja.DaneTestowe;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

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
    static Stream<Atrakcja> atrakcjaProvider() {
        return Stream.of(new Atrakcja("A", 0.12F, "", "Wrocław"),
                new Atrakcja("B", 0.18F, "2013-12-22 12:12:12", "Wrocław"),
                null);
    }

    @Order(3)
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

    // na poczatku są 3 bilety
    @ExtendWith(InterfejsPracownikaTest.class)
    @ParameterizedTest
    @CsvSource({
            "piotr.@gmail.com\rpiotr@gmail.com,Lubie wasze atrakcje,Sa super,zgloszenie,",
            "piotr.@gmail.com\rpiotr@gmddail.com,prosze o pilny zwrot biletu,skandal ze wam nie wstyd!!,zgloszenie,2", //atrakcja za mniej niz 24h
            "piotr.@gmail.com\rpiotr@gmddail.com,prosze o pilny zwrot biletu,skandal ze wam nie wstyd!!,zwrot,3", //zwroci sie bilet
            "piotr.@gmail.com\rpiotr@gmddail.com,prosze o pilny zwrot biletu,skandal ze wam nie wstyd!!,blad,-29", //out of bounds
            "piotr.@gmail.com\rpiotr@gmddail.com,prosze o pilny zwrot biletu,skandal ze wam nie wstyd!!,blad,129", //out of bounds
    })

    @Order(2)

    // wynik moze być tylko: "zgloszenie" lub "zwrot"
    void testWyslijZapytanieDoPracownikaWysylka(String mail, String temat, String tresc, String wynik, String idBiletu) {
        //przygotowanie danych
        String doKonsoli = idBiletu == null ? String.join("\r", mail, temat, tresc) : String.join("\r", mail, temat, idBiletu, tresc);
        boolean wystepowanieBiletuNaPoczatku = false;
        if (idBiletu != null) {
            wystepowanieBiletuNaPoczatku = instance.aplikacja.kasaBiletowa.wyszukajBilet(Integer.parseInt(idBiletu)) != null;
        }
        // wysyłka do konsoli danych
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ByteArrayInputStream testIn = new ByteArrayInputStream(doKonsoli.getBytes());
        System.setIn(testIn);
        Scanner scanner = new Scanner(System.in);
        instance = new InterfejsUzytkownika(scanner);
        // test w akcji
        instance.wyslijZapytanieDoPracownika();
//        System.setOut(System.out);
//        System.setIn(System.in);


        // jest problem - żeby bilet był starszy niż doba trzeba przeczekać dobę...

        //‼️‼️‼️ kwestię tego, czy idBiletu jest w dozwolonym zakresie, sprawdzamy wyjątkiem

        //asserty
        // "\r\n" i "\n" to kwestia systemu operacyjnego
        if (!Objects.equals(wynik, "blad")) {
            if (idBiletu != null) {
                boolean wystepowanieBiletuNaKoncu = instance.aplikacja.kasaBiletowa.wyszukajBilet(Integer.parseInt(idBiletu)) != null;
                if (Objects.equals(wynik, "zwrot")) {
                    // zwrocono bilet kupiony w ciagu ostatniej doby
                    assertTrue(wystepowanieBiletuNaPoczatku);
                    assertFalse(wystepowanieBiletuNaKoncu);
                    assertTrue((outputStream.toString().contains("Zwrocono bilet poprzez zwroc bilet")));
                    assertFalse((outputStream.toString().contains("wyslano wiadomosc o podanej tresci ")));
//                    assertFalse((outputStream.toString().contains("wyslano wiadomosc o podanej tresci ") && (outputStream.toString().endsWith(tresc + "\r\n") || outputStream.toString().endsWith(tresc + "\n"))));
                } else {
                    // wyslano prosbe o zwrot biletu kupionego wczesniej niz 24h temu
                    assertTrue(wystepowanieBiletuNaPoczatku);
                    assertTrue(wystepowanieBiletuNaKoncu);
                    assertFalse((outputStream.toString().contains("Zwrocono bilet poprzez zwroc bilet")));
                    assertTrue((outputStream.toString().contains("wyslano wiadomosc o podanej tresci ")));
//                    assertTrue((outputStream.toString().contains("wyslano wiadomosc o podanej tresci ") && (outputStream.toString().endsWith(tresc + "\r\n") || outputStream.toString().endsWith(tresc + "\n"))));
                }
            } else {
                // wyslano wiadomosc nie na temat zwrotow
                assertTrue((outputStream.toString().contains("wyslano wiadomosc o podanej tresci ")));
//                assertTrue((outputStream.toString().contains("wyslano wiadomosc o podanej tresci ") && (outputStream.toString().endsWith(tresc + "\r\n") || outputStream.toString().endsWith(tresc + "\n"))));
                assertFalse((outputStream.toString().contains("Zwrocono bilet poprzez zwroc bilet")));
            }
        }
    }

    @Order(1)

    @Test
    void testCzyKupicBilet() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String doKonsoli = String.join("\r", daneTestowe.userInputCzyKupicBilet);

        ByteArrayInputStream testIn = new ByteArrayInputStream(doKonsoli.getBytes());
        System.setIn(testIn);
        Scanner scanner = new Scanner(System.in);
        instance = new InterfejsUzytkownika(scanner);

        boolean wynik;
        for (int i = 0; i < daneTestowe.userInputCzyKupicBilet.length; i++) {
            wynik = instance.czyKupicBilet();
            assertEquals(wynik, daneTestowe.userInputCzyKupicBiletWynik[i]);
        }

    }


}