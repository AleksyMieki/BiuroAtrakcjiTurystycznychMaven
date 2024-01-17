package Aplikacja;

import mockit.Mocked;
import org.hibernate.jdbc.Expectation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import mockit.Mocked;
import mockit.Expectations;
import mockit.Tested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Entity")
public class MenedzerWiadomosciTest implements TestExecutionExceptionHandler {

    static DaneTestowe daneTestowe;
    static MenedzerWiadomosci instance;

    @Mocked
    Zgloszenie zgloszenie;

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
        daneTestowe = new DaneTestowe();
        instance = new MenedzerWiadomosci();
    }

    @Test
    void mockTestSprawdzenieTematu()
    {
        new Expectations() {{
            zgloszenie.getTemat();
            result = "zwrot biletu";
        }};

        assertTrue(instance.sprawdzenieTematu(zgloszenie.getTemat()));
    }

    @Test
    void mockTestSprawdzenieTematuFalse()
    {
        new Expectations() {{
            zgloszenie.getTemat();
            result = "zwrot";
        }};

        assertFalse(instance.sprawdzenieTematu(zgloszenie.getTemat()));
    }

    @Test
    void mockTestSprawdzenieMaila()
    {
        new Expectations()
        {{
            zgloszenie.getEmail();
            result = "uzytkownik@domain.com";
        }};

        assertTrue(instance.sprawdzenieMaila(zgloszenie.getEmail()));
    }

    @Test
    void mockTestWyslijWiadomosc()
    {
        new Expectations()
        {{
            zgloszenie.getTrescWiadomosci();
            result = "tresc wiadomosci";
        }};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        instance.wyslijWiadomosc(zgloszenie);
        System.setOut(System.out);

        assertEquals("wyslano wiadomosc o podanej tresci " + zgloszenie.getTrescWiadomosci() + "\r\n", outputStream.toString());
    }

    @Test
    void mockTestWyslijWiadomoscPracownik()
    {
        new Expectations()
        {{
            zgloszenie.getTemat();
            result = "temat";
        }};
        String wiadomosc = "wiadomosc";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        instance.wyslijWiadomoscPracownik(zgloszenie, wiadomosc);
        System.setOut(System.out);

        assertEquals("wyslalem wiadomosc " + wiadomosc + " na zgloszenie o tym temacie: " + zgloszenie.getTemat() +"\r\n", outputStream.toString());
    }

    @Test
    void testSprawdzenieTematu() {
        boolean wynik;
        for (Map.Entry<String, Boolean> entry : daneTestowe.tematy.entrySet()) {
            wynik = instance.sprawdzenieTematu(entry.getKey());
            assertEquals(wynik, entry.getValue());
        }
    }

    static Stream<Zgloszenie> zgloszenieProvider() {
        return Stream.of(new Zgloszenie("A", "a@a.com", 1, "tresc", "2024-01-16 13:04:15"),
                new Zgloszenie("A", "a@a.com", 1, null, "2024-01-16 13:04:15"),
                null);
    }

    @ParameterizedTest
    @MethodSource("zgloszenieProvider")
    @ExtendWith(MenedzerWiadomosciTest.class)
    void testWyslijWiadomosc(Zgloszenie zgloszenie) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        instance.wyslijWiadomosc(zgloszenie);
        System.setOut(System.out);
//        assertEquals("wyslano wiadomosc o podanej tresci " + zgloszenie.getTrescWiadomosci() + "\r\n", outputStream.toString());
        assertEquals("wyslano wiadomosc o podanej tresci " + zgloszenie.getTrescWiadomosci() + "\r\n", outputStream.toString());
    }

    @ParameterizedTest
    @ExtendWith(MenedzerWiadomosciTest.class)
    @CsvSource({"username@domain.com, true", "user.name@domain.com, true", "user-name@domain.com, true",
            "username@domain.co.in, true", "user_name@domain.com, true", "username.@domain.com, false",
            ".user.name@domain.com, false", "user-name@domain.com., false", "username@.com, false",
            "username@@.com, false", ",false"})
    void testSprawdzenieMaila(String email, boolean poprawny) {
        boolean wynikFunkcji = instance.sprawdzenieMaila(email);
        assertEquals(poprawny, wynikFunkcji);
    }
}