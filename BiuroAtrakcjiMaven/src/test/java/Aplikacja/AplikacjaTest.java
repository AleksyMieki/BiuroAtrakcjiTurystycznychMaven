package Aplikacja;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import static org.junit.jupiter.api.Assertions.*;

class AplikacjaTest implements TestExecutionExceptionHandler {
    static Aplikacja instance;

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
        if(throwable instanceof NullPointerException) {
            System.out.println("Wprowadzone dane są nieprawidłowe, unikaj wartości NULL");
        }
        else{ throw throwable; }}

    @BeforeAll
    static void setUp() {
        instance = Aplikacja.getInstance();
    }

    @Test
    void utworzZgloszenie() {
    }
}