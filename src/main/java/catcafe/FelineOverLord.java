package catcafe;

/**
 * Repräsentiert eine Katze (FelineOverLord) mit Name und Gewicht.
 *
 * Diese Klasse ist ein Record, der automatisch Konstruktor, Getter, equals, hashCode und toString generiert.
 *
 * @param name Name der Katze
 * @param weight Gewicht der Katze
 */
public record FelineOverLord(String name, int weight) implements Comparable<FelineOverLord> {
    /**
     * Vergleich zweier Katzen anhand ihres Gewichts.
     *
     * @param o andere Katze zum Vergleich
     * @return negativer Wert, wenn diese Katze leichter ist,
     *         positiver Wert, wenn schwerer,
     *         0 bei gleichem Gewicht
     */
    @Override
    public int compareTo(FelineOverLord o) {
        return weight - o.weight;
    }
}
