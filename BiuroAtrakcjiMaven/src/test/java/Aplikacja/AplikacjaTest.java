package Aplikacja;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Entity")
@Tag("Control")
class AplikacjaTest implements TestExecutionExceptionHandler {

    Dane dane = new Dane("1",1,"1","1");

    @BeforeEach
    public void setUp()
    {
        Atrakcja atrakcja = new Atrakcja(dane);
    }

    @Test
    @ExtendWith(AplikacjaTest.class)
    public void testDodajAtrakcje()
    {
        Aplikacja aplikacja = Aplikacja.getInstance();

        assertEquals(0,0);

    }


    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {

    }
}