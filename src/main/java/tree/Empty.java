package tree;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * Repräsentiert einen leeren Knoten in einem binären Suchbaum.
 *
 * @param <T> Typ der gespeicherten Daten (muss Comparable sein)
 */
public record Empty<T extends Comparable<T>>() implements Tree<T> {
    /**
     * Gibt zurück, ob der Knoten leer ist.
     *
     * @return true, da dies ein leerer Knoten ist
     */
    @Override
    public boolean isEmpty() {
        return true;
    }

    /**
     * Fügt Daten in den Baum ein. Da der Knoten leer ist, wird ein neuer Knoten erstellt.
     *
     * @param data einzufügende Daten, dürfen nicht null sein
     * @return neuer Baumknoten mit den Daten, linker und rechter Kindknoten sind leer
     */
    @Override
    public Tree<T> addData(T data) {
        requireNonNull(data);

        return new Node<>(new Empty<>(), data, new Empty<>());
    }

    /**
     * Akzeptiert einen Besucher, der auf diesem Knoten arbeitet.
     *
     * @param visitor Visitor, darf nicht null sein
     * @return Ergebnis der Besuchermethode
     */
    @Override
    public String accept(TreeVisitor<T> visitor) {
        requireNonNull(visitor);

        return visitor.visit(this);
    }

    /**
     * Gibt einen Iterator zurück, der diesen Knoten durchläuft.
     *
     * @return Iterator über die Elemente des Baums
     */
    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<>(this);
    }

    /**
     * Führt eine Aktion für jedes Element dieses Baums aus.
     *
     * @param action Aktion, die für jedes Element ausgeführt wird
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        requireNonNull(action);

        // Da leer, wird keine Aktion ausgeführt
        for (T t : this) {
            action.accept(t);
        }
    }

    /**
     * Gibt einen Spliterator zurück, der über diesen Baum iteriert.
     *
     * @return Spliterator über die Elemente des Baums
     */
    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED);
    }
}
