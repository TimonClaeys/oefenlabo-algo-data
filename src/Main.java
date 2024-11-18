import logica.BasketClub;
import logica.Categorie;
import logica.Speler;

import java.util.List;
import java.util.SortedSet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BasketClub basketClub = new BasketClub();
        Speler speler = new Speler("Timon", "Claeys", 2004);
        Speler speler2 = new Speler("Milan", "Claeys", 2004);
        basketClub.schrijfIn(speler);
        basketClub.schrijfIn(speler2);
        List<Speler> ledenVolgensLidnummer = basketClub.getSpelersInCategorieVolgensLidnummer(Categorie.pupillen);
        System.out.println(basketClub.toString());
    }
}