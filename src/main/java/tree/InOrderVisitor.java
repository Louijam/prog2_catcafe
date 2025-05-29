package tree;

/**
 * Visitor für InOrder-Traversierung eines Baums.
 *
 * @param <T> Typ der Knotendaten, muss Comparable sein
 */
public class InOrderVisitor<T extends Comparable<T>> implements TreeVisitor<T> {
    /**
     * Besuch eines leeren Knotens.
     *
     * @param node leerer Knoten
     * @return leerer String, da kein Inhalt
     */
    @Override
    public String visit(Empty<T> node) {
        return "";
    }

    /**
     * Besuch eines Nicht-Leer-Knotens.
     * Traversiert links, dann aktuellen Knoten, dann rechts.
     *
     * @param node Knoten mit Daten und Kindern
     * @return zusammengesetzte String-Repräsentation der Traversierung
     */
    @Override
    public String visit(Node<T> node) {
        // Links rekursiv traversieren
        String left = node.leftChild().accept(this);
        // Daten des aktuellen Knotens als String
        String current = node.data().toString();
        // Rechts rekursiv traversieren
        String right = node.rightChild().accept(this);

        // Strings mit Leerzeichen trennen, überflüssige Leerzeichen entfernen
        return String.join(" ",
                left.trim(),
                current.trim(),
                right.trim()
        ).trim();
    }
}
