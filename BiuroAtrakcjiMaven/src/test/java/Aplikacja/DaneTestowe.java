package Aplikacja;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaneTestowe {
    Map<String,Boolean> tematy = Map.of(
            "Zwrot biletu", true,
            "zWrOt bIlEtu", true,
            "zwrot biletu", true,
            "ZWROT BILETU", true,
            "inny temat", false,
            "jeszcze inny temat", false,
            "inniejszy temat", false,
            "prośba o zwrot biletu na atrakcję", true,
            "proszę bardzo o ZWROT BILETU", true
    );

    String[] maile ={"niemail", null, "cos@gmail.pl", "sadada@asdasd.pl"};
    String[] tytulyMaili = {null, "cos", "zwrot biletu"};
    String[] tresciMaili ={null, "dzien dobry"};

    public String[] userInputCzyKupicBilet = {"tak","jeszcze jak!","nie","...jakie było pytanie?", "1", "0", "-1"};
    public Boolean[] userInputCzyKupicBiletWynik = {false,false,false,false,true,false,false};

}
