package tree;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * Knoten in einem binären Suchbaum.
 *
 * @param data Daten, die im Knoten gespeichert werden (darf nicht null sein)
 * @param leftChild linker Teilbaum
 * @param rightChild rechter Teilbaum
 * @param <T> Typ der gespeicherten Daten (muss Comparable sein)
 */
public record Node<T extends Comparable<T>>(Tree<T> leftChild, T data, Tree<T> rightChild)
    implements Tree<T> {
    /**
     * Konstruktor mit Null-Prüfung aller Argumente.
     */
    public Node {
        requireNonNull(data);
        requireNonNull(leftChild);
        requireNonNull(rightChild);
    }

    /**
     * Knoten ist nicht leer.
     *
     * @return false
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Fügt Daten in den Baum ein.
     * Vergleich der neuen Daten mit aktuellen Knoten-Daten:
     * - kleiner: links einfügen
     * - größer: rechts einfügen
     * - gleich: nichts tun (keine Duplikate)
     *
     * @param data einzufügende Daten (nicht null)
     * @return neuer Baum mit eingefügtem Wert
     */
    @Override
    public Tree<T> addData(T data) {
        requireNonNull(data);

        int compareVal = this.data.compareTo(data);
        if (compareVal < 0) {
            // this.data < data: rechts einfügen
            return new Node<>(leftChild, this.data, rightChild.addData(data));
        } else if (compareVal > 0) {
            // this.data > data: links einfügen
            return new Node<>(leftChild.addData(data), this.data, rightChild);
        } else {
            // this.data == data: keine Änderung (keine Duplikate)
            return this;
        }
    }

    /**
     * Akzeptiert einen Visitor, der auf diesem Knoten arbeitet.
     *
     * @param visitor Visitor, nicht null
     * @return Ergebnis der Besuchermethode
     */
    @Override
    public String accept(TreeVisitor<T> visitor) {
        requireNonNull(visitor);

        return visitor.visit(this);
    }

    /**
     * Iterator für den Baum ab diesem Knoten.
     *
     * @return Iterator über die Elemente
     */
    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<>(this);
    }

    /**
     * Führt eine Aktion für jedes Element dieses Baums aus.
     *
     * @param action Aktion, die für jedes Element ausgeführt wird (nicht null)
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        requireNonNull(action);

        for (T t : this) {
            action.accept(t);
        }
    }

    /**
     * Gibt einen Spliterator zurück, der über diesen Baum iteriert.
     *
     * @return Spliterator über die Elemente
     */
    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED);
    }
}
