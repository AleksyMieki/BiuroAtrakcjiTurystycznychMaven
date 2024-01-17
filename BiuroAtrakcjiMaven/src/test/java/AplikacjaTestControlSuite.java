import Prezentacja.InterfejsPracownikaTest;
import org.junit.platform.suite.api.*;

@Suite
@SelectPackages("Aplikacja")
@IncludeTags("Control")
@ExcludeTags("Entity")
public class AplikacjaTestControlSuite {
}
