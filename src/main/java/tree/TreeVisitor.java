package tree;

/**
 * Interface für das Visitor-Pattern bei binären Suchbäumen.
 *
 * @param <T> Typ der Baum-Elemente (muss Comparable sein)
 */
public interface TreeVisitor<T extends Comparable<T>> {

    /**
     * Besuch eines leeren Knotens.
     *
     * @param node zu besuchender leerer Knoten
     * @return Ergebnis des Besuchs (rekursiv)
     */
    String visit(Empty<T> node);

    /**
     * Besuch eines normalen Knotens.
     *
     * @param node zu besuchender Knoten
     * @return Ergebnis des Besuchs (rekursiv)
     */
    String visit(Node<T> node);
}
