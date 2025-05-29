package tree;

public class InOrderVisitor<T extends Comparable<T>> implements TreeVisitor<T> {
    @Override
    public String visit(Empty<T> node) {
        return "";
    }

    @Override
    public String visit(Node<T> node) {
        String left = node.leftChild().accept(this);
        String current = node.data().toString();
        String right = node.rightChild().accept(this);

        // Strings zusammensetzen, mit Leerzeichen trennen und überflüssige vermeiden
        return String.join(" ",
                left.trim(),
                current.trim(),
                right.trim()
        ).trim();
    }
}
