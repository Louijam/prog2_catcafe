package tree;

public class InOrderVisitor<T extends Comparable<T>> implements TreeVisitor<T> {

    @Override
    public String visit(Node<T> node) {
        String leftStr = node.leftChild().accept(this);
        String dataStr = node.data().toString();
        String rightStr = node.rightChild().accept(this);
        return leftStr + dataStr + rightStr;
    }

    @Override
    public String visit(Empty<T> empty) {
        return "";
    }
}
