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
        assertEquals(1, cafe.getCatCount()); // Nach dem Hinzufügen 1 Katze

        cafe.addCat(new FelineOverLord("Miezi", 3));
        assertEquals(2, cafe.getCatCount()); // Nach dem zweiten Hinzufügen 2 Katzen
    }

    // Testet, ob eine Katze mit bestimmtem Namen gefunden wird
    @Test
    public void testGetCatByNameFound() {
        FelineOverLord cat = new FelineOverLord("Tom", 5);
        cafe.addCat(cat); // Katze "Tom" hinzufügen

        Optional<FelineOverLord> result = cafe.getCatByName("Tom"); // Katze suchen
        assertTrue(result.isPresent()); // Ergebnis soll vorhanden sein
        assertEquals(cat, result.get()); // Gefundene Katze soll genau die hinzugefügte sein
    }

    // Testet, ob bei Suche nach einem Namen, der nicht existiert, kein Ergebnis zurückkommt
    @Test
    public void testGetCatByNameNotFound() {
        cafe.addCat(new FelineOverLord("Jerry", 2)); // Nur "Jerry" hinzugefügt

        Optional<FelineOverLord> result = cafe.getCatByName("Spike"); // Suche nach "Spike"
        assertFalse(result.isPresent()); // Sollte nicht gefunden werden
    }

    // Testet, ob die Methode getCatByName bei null-Argument korrekt reagiert (kein Ergebnis)
    @Test
    public void testGetCatByNameNullArgument() {
        assertThrows(NullPointerException.class, () -> cafe.getCatByName(null)); // Soll kein Ergebnis zurückgeben
    }

    // Testet Suche nach Katze anhand ihres Gewichts im angegebenen Bereich
    @Test
    public void testGetCatByWeightFound() {
        FelineOverLord cat1 = new FelineOverLord("Whiskers", 3);
        FelineOverLord cat2 = new FelineOverLord("Snowball", 5);
        cafe.addCat(cat1);
        cafe.addCat(cat2);

        // Suche nach Katze mit Gewicht zwischen 2 und 4
        Optional<FelineOverLord> result = cafe.getCatByWeight(2, 4);
        assertTrue(result.isPresent()); // Katze soll gefunden werden
        assertEquals(cat1, result.get()); // Gefundene Katze ist cat1 ("Whiskers")
    }

    // Testet, ob bei Gewichtssuche ohne passende Katze kein Ergebnis zurückkommt
    @Test
    public void testGetCatByWeightNotFound() {
        cafe.addCat(new FelineOverLord("Shadow", 6)); // Nur Katze mit Gewicht 6

        Optional<FelineOverLord> result = cafe.getCatByWeight(1, 3); // Suche Bereich 1-3
        assertFalse(result.isPresent()); // Keine passende Katze -> kein Ergebnis
    }

    // Testet, ob ungültige Gewichtsspannen korrekt behandelt werden (kein Ergebnis)
    @Test
    public void testGetCatByWeightInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> cafe.getCatByWeight(-1, 3));
        assertThrows(IllegalArgumentException.class, () -> cafe.getCatByWeight(3, 2));
    }

    // Testet, ob das Hinzufügen von null als Katze eine NullPointerException wirft
    @Test
    public void testAddNullCatThrowsException() {
        assertThrows(NullPointerException.class, () -> cafe.addCat(null));
    }

    // Testet Verhalten, wenn mehrere Katzen mit demselben Namen vorhanden sind
    @Test
    public void testMultipleCatsSameName() {
        FelineOverLord cat1 = new FelineOverLord("Felix", 4);
        FelineOverLord cat2 = new FelineOverLord("Felix", 5);
        cafe.addCat(cat1);
        cafe.addCat(cat2);

        Optional<FelineOverLord> result = cafe.getCatByName("Felix");
        assertTrue(result.isPresent());
        // Erwartet wird, dass die erste hinzugefügte Katze gefunden wird
        assertEquals(cat1, result.get());
    }

    // Testet, ob die Anzahl der Katzen nach mehreren Hinzufügungen korrekt ist
    @Test
    public void testCatCountAfterMultipleAdds() {
        for (int i = 0; i < 10; i++) {
            cafe.addCat(new FelineOverLord("Cat" + i, i));
        }
        assertEquals(10, cafe.getCatCount()); // 10 Katzen sollten im Cafe sein
    }
}
