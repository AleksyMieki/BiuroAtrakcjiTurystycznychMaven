package testyfitnessefixture;

import fit.Fixture;
import Aplikacja.Aplikacja;
public class SetUp extends Fixture{
    static Aplikacja aplikacja;
    public SetUp() {
        aplikacja = Aplikacja.getInstance();
    }
}
