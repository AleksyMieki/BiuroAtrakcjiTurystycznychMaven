import Aplikacja.AplikacjaTest;
import Prezentacja.InterfejsPracownika;
import Prezentacja.InterfejsPracownikaTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        AplikacjaTest.class,
        InterfejsPracownikaTest.class,
        // Add more test classes here
})
class AllTestsSuite {
    // This class remains empty, used only as a holder for the above annotations
}
