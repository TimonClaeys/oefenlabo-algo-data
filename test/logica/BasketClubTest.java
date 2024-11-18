package logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class BasketClubTest {

    private BasketClub club;

    @BeforeEach
    void setUp() {
        club = new BasketClub();
    }

    @Test
    void testSpelerCorrectIngeschreven() {
        Speler speler = new Speler("Tom", "Van Dyck", 1998);

        club.schrijfIn(speler);
        TreeSet<Speler> spelers = club.getSpelers(club.getCategorie(speler.getGeboortejaar()));

        assertNotNull(spelers, "Categorie moet aangemaakt worden bij inschrijving.");
        assertTrue(spelers.contains(speler), "Speler zou in de categorie moeten staan.");
    }

    @Test
    void testSpelerNietTweeKeerIngeschreven() {
        Speler speler = new Speler("Tom", "Van Dyck", 1998);

        club.schrijfIn(speler);
        club.schrijfIn(speler);

        TreeSet<Speler> spelers = club.getSpelers(club.getCategorie(speler.getGeboortejaar()));
        assertEquals(1, spelers.size(), "De speler mag niet twee keer ingeschreven worden.");
    }

    @Test
    void testSpelerCorrectUitgeschreven() {
        Speler speler = new Speler("Tom", "Van Dyck", 1998);

        club.schrijfIn(speler);
        club.schrijfUit(speler);

        TreeSet<Speler> spelers = club.getSpelers(club.getCategorie(speler.getGeboortejaar()));
        assertFalse(spelers != null && spelers.contains(speler), "Speler zou uit de categorie verwijderd moeten zijn.");
    }

    @Test
    void testCategorieVerwijderdAlsLeeg() {
        Speler speler = new Speler("Tom", "Van Dyck", 1998);

        club.schrijfIn(speler);
        club.schrijfUit(speler);

        TreeSet<Speler> spelers = club.getSpelers(club.getCategorie(speler.getGeboortejaar()));
        assertNull(spelers, "Lege categorieën moeten verwijderd worden.");
    }

    @Test
    void testGetSpelersPerCategorie() {
        Speler speler1 = new Speler("Tom", "Van Dyck", 1998);
        Speler speler2 = new Speler("Karel", "Peeters", 1998);

        club.schrijfIn(speler1);
        club.schrijfIn(speler2);

        TreeSet<Speler> spelers = club.getSpelers(club.getCategorie(speler1.getGeboortejaar()));
        assertNotNull(spelers, "Categorie moet aangemaakt worden.");
        assertEquals(2, spelers.size(), "Aantal spelers in de categorie klopt niet.");
    }

    @Test
    void testSpelersGesorteerdInCategorie() {
        Speler speler1 = new Speler("Tom", "Van Dyck", 1998);
        Speler speler2 = new Speler("An", "Van Dyck", 1998);
        Speler speler3 = new Speler("Karel", "Peeters", 1998);

        club.schrijfIn(speler1);
        club.schrijfIn(speler2);
        club.schrijfIn(speler3);

        TreeSet<Speler> spelers = club.getSpelers(club.getCategorie(speler1.getGeboortejaar()));
        assertEquals(speler3, spelers.first(), "Spelers moeten gesorteerd zijn op familienaam en voornaam.");
        assertEquals(speler1, spelers.last(), "Spelers moeten gesorteerd zijn op familienaam en voornaam.");
    }

    @Test
    void testToString() {
        Speler speler1 = new Speler("Tom", "Van Dyck", 1998);
        Speler speler2 = new Speler("Karel", "Peeters", 1998);

        club.schrijfIn(speler1);
        club.schrijfIn(speler2);

        String output = club.toString();
        assertTrue(output.contains("Van Dyck"), "ToString zou de familienaam moeten bevatten.");
        assertTrue(output.contains("Peeters"), "ToString zou de familienaam moeten bevatten.");
        assertTrue(output.contains(club.getCategorie(speler1.getGeboortejaar()).toString()), "ToString zou de categorie moeten bevatten.");
    }
}
