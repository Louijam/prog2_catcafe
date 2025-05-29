package tree;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Interface eines sortierten binären Suchbaums.
 *
 * @param <T> Typ der Knotendaten (muss Comparable sein)
 */
public interface Tree<T extends Comparable<T>> extends Iterable<T> {
    /**
     * Fügt neue Daten in den sortierten Baum ein.
     *
     * <p>Das neue Objekt wird als neues Blatt an der richtigen Position eingefügt. Wenn das Objekt
     * bereits im Baum enthalten ist, ändert sich der Baum nicht.
     *
     * @param data einzufügendes Objekt (darf nicht null sein)
     * @return neue Wurzel des Baums
     * @throws NullPointerException wenn data null ist
     */
    Tree<T> addData(T data);

    /**
     * Liefert die Daten des aktuellen Knotens.
     *
     * @return gespeicherte Daten
     */
    default T data() {
        throw new UnsupportedOperationException();
    }

    /**
     * Liefert den linken Kindknoten.
     *
     * @return linker Kindknoten
     */
    default Tree<T> leftChild() {
        throw new UnsupportedOperationException();
    }

    /**
     * Liefert den rechten Kindknoten.
     *
     * @return rechter Kindknoten
     */
    default Tree<T> rightChild() {
        throw new UnsupportedOperationException();
    }

    /**
     * Prüft, ob dieser Knoten leer ist.
     *
     * @return true wenn leer, sonst false
     */
    boolean isEmpty();

    /**
     * Anzahl der Nicht-Leer-Knoten im Baum.
     *
     * @return Anzahl der Node-Elemente
     */
    default long size() {
        return stream().count();
    }

    /**
     * Akzeptiert einen Visitor für diesen Baum.
     *
     * <p>Der Visitor implementiert nicht nur die Verarbeitung der Daten, sondern auch die Traversierung.
     *
     * @param visitor Visitor (darf nicht null sein)
     * @return String-Ergebnis der Traversierung
     * @throws NullPointerException wenn visitor null ist
     */
    String accept(TreeVisitor<T> visitor);

    /**
     * Erstellt einen Stream zur Traversierung des Baums in Tiefensuche.
     *
     * @return Stream der Knotendaten
     */
    default Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
