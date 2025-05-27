package tree;

public class PostOrderVisitor<T extends Comparable<T>> implements TreeVisitor<T> {

    @Override
    public String visit(Node<T> node) {
        // links, rechts, dann data
        String leftStr = node.leftChild().accept(this);
        String rightStr = node.rightChild().accept(this);
        String dataStr = node.data().toString();

        return leftStr + rightStr + dataStr;
    }

    @Override
    public String visit(Empty<T> empty) {
        return ""; // leerer String für leere Knoten
    }
}
