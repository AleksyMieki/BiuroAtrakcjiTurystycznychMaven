package Prezentacja;

import Aplikacja.*;
import java.util.Scanner;

public class InterfejsUzytkownika {
    public InterfejsUzytkownika(){

    }

    Scanner scanner = new Scanner(System.in);
    public InterfejsUzytkownika(Scanner scanner){
        this.scanner = scanner;
    }
    private boolean czyZalogowany = false;
    protected Aplikacja aplikacja = Aplikacja.getInstance();
    private void zaloguj() {


        String haslo = "";

        haslo = scanner.nextLine();

        if (haslo.equals("1234")) czyZalogowany = true;

    }
    protected Atrakcja wyszukajAtrakcje(boolean czyZalogowany) {

        System.out.println("Podaj nazwę szukanej atrakcji:");

        String nazwa = podajNazwe();
        boolean wynik = false;
        Atrakcja znalezionaAtrakcja = aplikacja.wyszukajAtrakcje(nazwa);

        if(znalezionaAtrakcja == null){
            return null;
        }

        if(czyZalogowany)
        {
            System.out.println("atrakcja znaleziona dla pracownika");
            return znalezionaAtrakcja;
        }

        wynik = czyKupicBilet();

        if(wynik == true)
        {
            aplikacja.kasaBiletowa.kupBilet(znalezionaAtrakcja);
        }

        return znalezionaAtrakcja;
    }

    public void wyswietlDaneAtrakcji(Atrakcja atrakcja)
    {
        if(atrakcja != null) {
            System.out.println("OTO DANE ATRAKCJI");
            System.out.println(atrakcja.getNazwa());
            System.out.println(atrakcja.getCena());
            System.out.println(atrakcja.getDataAtrakcji());
            System.out.println(atrakcja.getLokalizacja());
        }
        else{
            throw new NullPointerException();
        }
    }

    public void wyslijZapytanieDoPracownika() {

        Bilet znalezionyBilet;
        String wiadomosc;
        String email;
        String temat;
        int id;
        boolean poprawny;
        boolean czyZwrot;
        boolean mniejNizDoba;

        do {
            System.out.println("Podaj swojego maila");
            email = podajMaila();
            System.out.println(email);
            poprawny = aplikacja.menedzerWiadomosci.sprawdzenieMaila(email);
        }while(poprawny == false);

        System.out.println("Podaj temat wiadomosci(jesli chcesz zwrocic bilet wpisz zwrot biletu)");

        temat = podajTemat();
        System.out.println(temat);
        czyZwrot = aplikacja.menedzerWiadomosci.sprawdzenieTematu(temat);

        if(czyZwrot)
        {
            System.out.println("Podaj numer biletu");

            id = podajNumerBiletu();

            znalezionyBilet = aplikacja.kasaBiletowa.wyszukajBilet(id);

            if(znalezionyBilet == null)
            {
                throw new NullPointerException();
            }

            mniejNizDoba = aplikacja.kasaBiletowa.sprawdzDateWydarzenia(znalezionyBilet);

            if(mniejNizDoba == true)
            {
                aplikacja.kasaBiletowa.zwrocBilet(znalezionyBilet);
                return;
            }
        }

        System.out.println("Podaj tresc twojej wiadomosci");
        wiadomosc = podajTrescWiadomosci();

        aplikacja.utworzZgloszenie(temat,email,wiadomosc);
    }

    private String podajMaila() {

        return scanner.nextLine();
    }

    private String podajTemat()
    {

        return scanner.nextLine();
    }

    private int podajNumerBiletu() {

        return scanner.nextInt();
    }

    private String podajTrescWiadomosci() {

        return scanner.nextLine();
    }
    private String podajNazwe() {

        return scanner.nextLine();
    }

    private boolean czyKupicBilet() {



        System.out.println("""
						Czy kupić bilet?
						1. tak
						aby nie kupować biletu, wprowadź dowolny znak
						""");

        String wybor = scanner.nextLine();

        switch (wybor) {
            case "1": {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner1 = new Scanner(System.in);

        InterfejsUzytkownika ui = new InterfejsUzytkownika();
        InterfejsPracownika pracownikUI = new InterfejsPracownika();

        int wybor = 0;

        while (true)
        {
            if(!ui.czyZalogowany) {

                System.out.println("""
						Dokonaj wyboru akcji
						1. Zaloguj 1234
						2. Wyszukaj Atrakcje
						3. Wyslij Zapytanie
						""");

                wybor = scanner1.nextInt();

                switch (wybor) {
                    case 1:
                        System.out.println("podaj haslo");
                        ui.zaloguj();
                        break;

                    case 2:
                        Atrakcja atrakcja = ui.wyszukajAtrakcje(ui.czyZalogowany);
                        ui.wyswietlDaneAtrakcji(atrakcja);

                        break;
                    case 3:
                        ui.wyslijZapytanieDoPracownika();
                        break;
                    case 4:
                        break;
                }
            }
            else
            {
                System.out.println("""
						Dokonaj wyboru akcji
						1. Zarzadzaj Atrakcja
						2. Przegladaj zapytania uzytkownikow
						3. Zarzadzaj zapytaniami klientow
						4. wyloguj
						""");

                wybor = scanner1.nextInt();

                switch (wybor) {
                    case 1:
                        pracownikUI.zarzadzajAtrakcja(ui.czyZalogowany);
                        break;
                    case 2:
                        pracownikUI.przegladajZgloszenia();
                        break;
                    case 3:
                        pracownikUI.zarzadzajZapytaniamiKlientow();
                        break;
                    case 4:
                        ui.czyZalogowany = false;
                        break;
                }
            }
        }
    }
}