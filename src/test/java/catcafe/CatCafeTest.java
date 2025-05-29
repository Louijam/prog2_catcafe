package catcafe;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CatCafeTest {

    private CatCafe cafe;

    // Wird vor jedem Test ausgeführt, um eine neue Instanz von CatCafe zu erzeugen
    @BeforeEach
    public void setup() {
        cafe = new CatCafe();
    }

    // Testet, ob die Anzahl der Katzen korrekt erhöht wird, wenn neue Katzen hinzugefügt werden
    @Test
    public void testAddCatIncreasesCount() {
        assertEquals(0, cafe.getCatCount()); // Anfangs sollte der Count 0 sein

        cafe.addCat(new FelineOverLord("Felix", 4));
        assertEquals(1, cafe.getCatCount()); // Nach dem Hinzufügen einer Katze soll der Count 1 sein

        cafe.addCat(new FelineOverLord("Garfield", 3));
        assertEquals(2, cafe.getCatCount()); // Nach Hinzufügen einer zweiten Katze soll der Count 2 sein
    }

    // Testet, ob eine Katze mit bestimmtem Namen gefunden wird
    @Test
    public void testGetCatByNameFound() {
        FelineOverLord cat = new FelineOverLord("Tom", 5);
        cafe.addCat(cat); // Katze "Tom" hinzufügen

        Optional<FelineOverLord> result = cafe.getCatByName("Tom"); // Suche nach Katze "Tom"
        assertTrue(result.isPresent()); // Es soll ein Ergebnis vorhanden sein
        assertEquals(cat, result.get()); // Gefundene Katze soll genau die hinzugefügte sein
    }

    // Testet, ob bei Suche nach einem Namen, der nicht existiert, kein Ergebnis zurückkommt
    @Test
    public void testGetCatByNameNotFound() {
        cafe.addCat(new FelineOverLord("Jerry", 2)); // Nur Katze "Jerry" im Cafe

        Optional<FelineOverLord> result = cafe.getCatByName("Flecki"); // Suche nach "Flecki" (nicht vorhanden)
        assertFalse(result.isPresent()); // Ergebnis soll leer sein
    }

    // Testet, ob die Methode getCatByName bei null-Argument eine Exception wirft
    @Test
    public void testGetCatByNameNullArgument() {
        // Erwartet wird eine NullPointerException, wenn null übergeben wird
        assertThrows(NullPointerException.class, () -> cafe.getCatByName(null));
    }

    // Testet Suche nach Katze anhand ihres Gewichts im angegebenen Bereich
    @Test
    public void testGetCatByWeightFound() {
        FelineOverLord cat1 = new FelineOverLord("Whisky", 3);
        FelineOverLord cat2 = new FelineOverLord("Snowflake", 5);
        cafe.addCat(cat1);
        cafe.addCat(cat2);

        Optional<FelineOverLord> result = cafe.getCatByWeight(2, 4); // Suche Gewicht zwischen 2 und 4
        assertTrue(result.isPresent()); // Katze soll gefunden werden
        assertEquals(cat1, result.get()); // Gefundene Katze ist die mit Gewicht 3 ("Whisky")
    }

    // Testet, ob bei Gewichtssuche ohne passende Katze kein Ergebnis zurückkommt
    @Test
    public void testGetCatByWeightNotFound() {
        cafe.addCat(new FelineOverLord("Shadow", 6)); // Katze mit Gewicht 6

        Optional<FelineOverLord> result = cafe.getCatByWeight(1, 3); // Suche Bereich 1-3 (keine Katze)
        assertFalse(result.isPresent()); // Ergebnis soll leer sein
    }

    // Testet, ob ungültige Gewichtsspannen (negative oder falschrum) korrekt behandelt werden
    @Test
    public void testGetCatByWeightInvalidRange() {
        // Erwartet wird eine IllegalArgumentException bei ungültigen Grenzen
        assertThrows(IllegalArgumentException.class, () -> cafe.getCatByWeight(-1, 3));
        assertThrows(IllegalArgumentException.class, () -> cafe.getCatByWeight(3, 2));
    }

    // Testet, ob das Hinzufügen von null als Katze eine NullPointerException wirft
    @Test
    public void testAddNullCatThrowsException() {
        assertThrows(NullPointerException.class, () -> cafe.addCat(null));
    }

    // Testet Verhalten bei mehreren Katzen mit demselben Namen
    @Test
    public void testMultipleCatsSameName() {
        FelineOverLord cat1 = new FelineOverLord("Garfield", 4);
        FelineOverLord cat2 = new FelineOverLord("Garfield", 5);
        cafe.addCat(cat1);
        cafe.addCat(cat2);

        Optional<FelineOverLord> result = cafe.getCatByName("Garfield");
        assertTrue(result.isPresent());
        // Erwartet wird, dass die erste hinzugefügte Katze zurückgegeben wird
        assertEquals(cat1, result.get());
    }

    // Testet, ob die Anzahl der Katzen nach mehreren Hinzufügungen korrekt ist
    @Test
    public void testCatCountAfterMultipleAdds() {
        for (int i = 0; i < 10; i++) {
            cafe.addCat(new FelineOverLord("Katze_" + i, i));
        }
        assertEquals(10, cafe.getCatCount()); // Nach 10 Hinzufügungen soll Count 10 sein
    }
}
