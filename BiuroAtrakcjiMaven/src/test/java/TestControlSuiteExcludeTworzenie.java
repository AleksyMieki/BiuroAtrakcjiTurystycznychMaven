import Prezentacja.InterfejsPracownikaTest;
import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.*;


@Suite
@SelectPackages("Aplikacja")
@ExcludeTags("tworzenie")
public class TestControlSuiteExcludeTworzenie {
}
