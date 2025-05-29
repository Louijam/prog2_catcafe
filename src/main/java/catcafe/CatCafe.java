package catcafe;

import static java.util.Objects.requireNonNull;

import tree.Empty;
import tree.Tree;
import tree.TreeVisitor;

import java.util.Optional;

/**
 * Ein CatCafe verwaltet eine Sammlung von Katzen (FelineOverLord).
 * Die Datenstruktur dahinter ist ein Baum (Tree).
 */
public class CatCafe {
    // Die Menge aller Katzen wird in einem Baum gespeichert. Initial ist dieser leer.
    private Tree<FelineOverLord> clowder = new Empty<>();

    /**
     * Fügt eine neue Katze zum CatCafe hinzu.
     *
     * @param cat die hinzuzufügende Katze
     */
    public void addCat(FelineOverLord cat) {
        // Null-Prüfung und Einfügen der Katze in den Baum
        clowder = clowder.addData(requireNonNull(cat));
    }

    /**
     * Gibt die Anzahl der Katzen im CatCafe zurück.
     *
     * @return Anzahl der Katzen
     */
    public long getCatCount() {
        // Die Größe des Baums entspricht der Anzahl der Katzen
        return clowder.size();
    }

    /**
     * Sucht die erste Katze mit einem bestimmten Namen.
     *
     * @param name Name der gesuchten Katze
     * @return Optional mit Katze, falls gefunden
     */
    public Optional<FelineOverLord> getCatByName(String name) {
        // Null-Prüfung für den Namen
        if (name == null) throw new NullPointerException("Keine Name eingegeben");

        // Durchsuche den Baum nach der ersten Katze mit passendem Namen
        return clowder.stream()
            .filter(c -> c.name().equals(name))
            .findFirst();
    }

    /**
     * Sucht die erste Katze mit einem Gewicht im angegebenen Bereich.
     *
     * @param minWeight untere Gewichtsgrenze (inklusive)
     * @param maxWeight obere Gewichtsgrenze (exklusiv)
     * @return Optional mit Katze, falls gefunden
     */
    public Optional<FelineOverLord> getCatByWeight(int minWeight, int maxWeight) {
        // Plausibilitätsprüfung für die Gewichtsgrenzen
        if (minWeight < 0) throw new IllegalArgumentException("minWeight muss größer als 0 sein");
        if (maxWeight < minWeight) throw new IllegalArgumentException("maxWeight darf nicht kleiner als minWeight sein");

        // Durchsuche den Baum nach der ersten Katze im gegebenen Gewichtsbereich
        return clowder.stream()
            .filter(c -> c.weight() >= minWeight && c.weight() < maxWeight)
            .findFirst();
    }

    /**
     * Wendet einen Visitor auf das CatCafe an.
     *
     * Der Visitor ist für die gesamte Traversierung des Baums verantwortlich.
     *
     * @param visitor Visitor, der auf die Struktur angewendet wird (darf nicht null sein)
     * @return Ergebnis der Traversierung als String
     * @throws NullPointerException falls visitor null ist
     */
    String accept(TreeVisitor<FelineOverLord> visitor) {
        // Delegiert die Traversierung an die Tree-Struktur
        return clowder.accept(visitor);
    }
}
