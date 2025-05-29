package tree;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Iterator für binäre Suchbäume {@link Tree}.
 *
 * @param <T> Typ der Knotendaten, muss Comparable sein
 */
public class TreeIterator<T extends Comparable<T>> implements Iterator<T> {
    // Stack zur Nachverfolgung der Knoten, die noch besucht werden müssen
    private final Stack<Tree<T>> stack;

    /**
     * Erzeugt einen neuen Iterator für einen gegebenen Baum.
     *
     * @param root Wurzelknoten des Baums (darf nicht null sein)
     */
    public TreeIterator(Tree<T> root) {
        requireNonNull(root);

        stack = new Stack<>();
        // Alle linken Kinder des Startknotens auf den Stack legen (für In-Order Traversierung)
        pushAllLeftNodes(root);
    }

    /**
     * Prüft, ob noch ein nächstes Element existiert.
     *
     * @return true, falls weitere Elemente vorhanden sind
     */
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Liefert das nächste Element im Baum (In-Order).
     *
     * @return nächstes Element
     * @throws NoSuchElementException falls kein weiteres Element existiert
     */
    @Override
    public T next() {
        if (hasNext()) {
            // Knoten vom Stack nehmen
            Tree<T> node = stack.pop();
            // Alle linken Kinder des rechten Teilbaums auf den Stack legen
            pushAllLeftNodes(node.rightChild());
            // Daten des aktuellen Knotens zurückgeben
            return node.data();
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Hilfsmethode: Legt alle linken Knoten eines Teilbaums auf den Stack.
     *
     * @param node Startknoten
     */
    private void pushAllLeftNodes(Tree<T> node) {
        requireNonNull(node);

        while (!node.isEmpty()) {
            stack.push(node);
            node = node.leftChild();
        }
    }
}
