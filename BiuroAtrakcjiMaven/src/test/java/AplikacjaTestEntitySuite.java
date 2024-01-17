import Prezentacja.InterfejsPracownikaTest;
import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.*;

@Suite
@SelectPackages("Aplikacja")

@IncludeTags("Entity")
@ExcludeTags("Control")
public class AplikacjaTestEntitySuite {
}
