package catcafe;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CatCafeTest {

    private CatCafe cafe;

    @BeforeEach
    public void setup() {
        cafe = new CatCafe();
    }

    @Test
    public void testAddCatIncreasesCount() {
        assertEquals(0, cafe.getCatCount());

        cafe.addCat(new FelineOverLord("Felix", 4));
        assertEquals(1, cafe.getCatCount());

        cafe.addCat(new FelineOverLord("Miezi", 3));
        assertEquals(2, cafe.getCatCount());
    }

    @Test
    public void testGetCatByNameFound() {
        FelineOverLord cat = new FelineOverLord("Tom", 5);
        cafe.addCat(cat);

        Optional<FelineOverLord> result = cafe.getCatByName("Tom");
        assertTrue(result.isPresent());
        assertEquals(cat, result.get());
    }

    @Test
    public void testGetCatByNameNotFound() {
        cafe.addCat(new FelineOverLord("Jerry", 2));

        Optional<FelineOverLord> result = cafe.getCatByName("Spike");
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetCatByNameNullArgument() {
        Optional<FelineOverLord> result = cafe.getCatByName(null);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetCatByWeightFound() {
        FelineOverLord cat1 = new FelineOverLord("Whiskers", 3);
        FelineOverLord cat2 = new FelineOverLord("Snowball", 5);
        cafe.addCat(cat1);
        cafe.addCat(cat2);

        Optional<FelineOverLord> result = cafe.getCatByWeight(2, 4);
        assertTrue(result.isPresent());
        assertEquals(cat1, result.get());
    }

    @Test
    public void testGetCatByWeightNotFound() {
        cafe.addCat(new FelineOverLord("Shadow", 6));

        Optional<FelineOverLord> result = cafe.getCatByWeight(1, 3);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetCatByWeightInvalidRange() {
        Optional<FelineOverLord> result1 = cafe.getCatByWeight(-1, 5);
        Optional<FelineOverLord> result2 = cafe.getCatByWeight(5, 3);

        assertFalse(result1.isPresent());
        assertFalse(result2.isPresent());
    }

    @Test
    public void testAddNullCatThrowsException() {
        assertThrows(NullPointerException.class, () -> cafe.addCat(null));
    }

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

    @Test
    public void testCatCountAfterMultipleAdds() {
        for (int i = 0; i < 10; i++) {
            cafe.addCat(new FelineOverLord("Cat" + i, i));
        }
        assertEquals(10, cafe.getCatCount());
    }
}
