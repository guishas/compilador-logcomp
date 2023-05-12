package compiler.classes;

public abstract class Node {
    protected final String value;
    protected final Node[] children;
    protected static int i = 0;

    public Node(String value, Node[] children) {
        this.value = value;
        this.children = children;
        i = newId();
    }

    public String getValue() {
        return value;
    }

    public int newId() {
        i+=1;
        return i;
    }

    public abstract void Evaluate() throws Exception;
}
