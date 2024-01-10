package Prezentacja;

import Aplikacja.Atrakcja;
import Aplikacja.Bilet;
import Aplikacja.Dane;
import Aplikacja.Zgloszenie;

import java.util.Collection;
import java.util.Scanner;

public class InterfejsPracownika extends InterfejsUzytkownika {

    public void zarzadzajAtrakcja(boolean czyZalogowany) {

        Atrakcja atrakcja;
        Dane atrybutyAtrakcji;

        atrakcja = wyszukajAtrakcje(czyZalogowany);

        if(atrakcja == null)
        {
            boolean wynik;

            System.out.println("nie znaleziono atrakcji o podanej nazwie w bazie atrakcji, dodawanie nowej");

            do {

                atrybutyAtrakcji = podajDaneAtrakcji();
                wynik = aplikacja.sprawdzPoprawnosc(atrybutyAtrakcji);

            }while(wynik == false);

            System.out.println("wprowadzono poprawne dane, tworze atrakcje");

            aplikacja.utworzAtrakcje(atrybutyAtrakcji);

            System.out.println("Atrakcje utwozono pomyslnie");

        }
        else
        {
            System.out.println("w bazie znaleziono taka atrakcje, czy chcesz ja teraz : \n1.usunac\n2.edytowac");

            int edycjaCzyUsuniecie = wybierzEdycjeLubUsuniecie();

            switch (edycjaCzyUsuniecie)
            {
                case 1:
                    boolean potwierdzenie = potwierdzUsuniecie();

                    if(potwierdzenie)
                    {
                        aplikacja.usunAtrakcje(atrakcja);
                    }

                    break;

                case 2:

                    boolean wynik;

                    do {

                        atrybutyAtrakcji = podajDaneAtrakcji();
                        wynik = aplikacja.sprawdzPoprawnosc(atrybutyAtrakcji);

                    }while(wynik == false);

                    aplikacja.edytujAtrakcje(atrakcja, atrybutyAtrakcji);

                    System.out.println("Pomyslnie zmodyfikowano dane atrakcji");

                    break;
            }
        }
    }

    public void zarzadzajZapytaniamiKlientow() {

        Zgloszenie zgloszenie;

        int idZgloszenia;
        boolean czyOdpowiedziec;
        boolean tematIsZwrot;

        idZgloszenia = podajIdZgloszenia();

        zgloszenie = aplikacja.getZgloszenieById(idZgloszenia);

        if (zgloszenie == null) return;

        czyOdpowiedziec = czyOdpowiedziecNaZgloszenie(zgloszenie);

        if(czyOdpowiedziec == false) return;

        tematIsZwrot = aplikacja.menedzerWiadomosci.sprawdzenieTematu(zgloszenie.getTemat());

        if(tematIsZwrot)
        {
            boolean czyZwrot = czyZwrocicBilet(zgloszenie);

            if(czyZwrot == true)
            {
                int id = podajIdBiletu();

                Bilet biletDoZwrotu = aplikacja.kasaBiletowa.wyszukajBilet(id);

                aplikacja.kasaBiletowa.zwrocBilet(biletDoZwrotu);
            }

        }
        else
        {
            System.out.println("Podaj wiadomosc do wyslania");
            String wiadomosc = podajWiadomosc();
            aplikacja.menedzerWiadomosci.wyslijWiadomoscPracownik(zgloszenie,wiadomosc);
        }
    }

    private int wybierzEdycjeLubUsuniecie() {

        Scanner scanner = new Scanner(System.in);

        int wybor = scanner.nextInt();
        scanner.nextLine();

        return wybor;
    }

    private Dane podajDaneAtrakcji() {

        String nazwa;
        float cena;
        String godzina;
        String lokalizacja;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj nazwe atrakcji:");
        nazwa = scanner.nextLine();

        System.out.println("Podaj cene atrakcji:");
        cena = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Podaj godzine atrakcji:");
        godzina = scanner.nextLine();

        System.out.println("Podaj lokalizacje atrakcji:");
        lokalizacja = scanner.nextLine();

        return new Dane(nazwa,cena,godzina,lokalizacja);

    }
    private boolean czyOdpowiedziecNaZgloszenie(Zgloszenie zgloszenie)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(zgloszenie.getTemat());
        System.out.println(zgloszenie.getEmail());
        System.out.println(zgloszenie.getTrescWiadomosci());
        System.out.println(zgloszenie.getDataWyslania());
        System.out.println("Czy chcesz odpowiedziec na zgloszenie(tak/nie)");

        String potwierdzenie;
        potwierdzenie = scanner.nextLine();

        if(potwierdzenie.equals("tak")) return true;
        else if (potwierdzenie.equals("nie")) return false;

        return false;

    }

    private boolean czyZwrocicBilet(Zgloszenie zgloszenie)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Czy zwrocic bilet?(tak/nie)");
        String potwierdzenie;

        System.out.println(zgloszenie.getTemat());
        System.out.println(zgloszenie.getEmail());
        System.out.println(zgloszenie.getTrescWiadomosci());
        System.out.println(zgloszenie.getDataWyslania());

        potwierdzenie = scanner.nextLine();

        if(potwierdzenie.equals("tak")) return true;
        if(potwierdzenie.equals("nie")) return false;

        return false;

    }

    private int podajIdZgloszenia()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("podaj id szukanego zapytania : ");

        return scanner.nextInt();

    }

    private String podajWiadomosc()
    {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private boolean potwierdzUsuniecie()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("czy na pewno chcesz usunac ta atrakcje z bazy dostepnych atrakcji(tak/nie)");
        String potwierdzenie;
        potwierdzenie = scanner.nextLine();

        if(potwierdzenie.equals("tak")) return true;
        else if (potwierdzenie.equals("nie")) return false;

        return false;
    }

    public void przegladajZgloszenia() {

        Collection<Zgloszenie> lista = aplikacja.getListaZgloszen();
        wyswietlZapytania(lista);

    }


    private void wyswietlZapytania(Collection<Zgloszenie> lista)
    {
        for (var v : lista) {
            System.out.println(v.getTrescWiadomosci());
            System.out.println(v.getId());
            System.out.println(v.getTemat());
            System.out.println();
        }
    }

    private int podajIdBiletu() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("podaj id szukanego biletu ze zgloszenia : ");
        return scanner.nextInt();

    }












}