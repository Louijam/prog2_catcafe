package tree;

public class PostOrderVisitor<T extends Comparable<T>> implements TreeVisitor<T> {
    @Override
    public String visit(Empty<T> node) {
        return "";
    }

    @Override
    public String visit(Node<T> node) {
        String left = node.leftChild().accept(this);
        String right = node.rightChild().accept(this);
        String current = node.data().toString();

        return String.join(" ",
                left.trim(),
                right.trim(),
                current.trim()
        ).trim();
    }
}
