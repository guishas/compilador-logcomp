package compiler.classes;

import java.util.ArrayList;

public abstract class Node {
    protected final String value;
    protected final Node[] children;

    public Node(String value, Node[] children) {
        this.value = value;
        this.children = children;
    }

    public String getValue() {
        return value;
    }

    public abstract ArrayList<String> Evaluate() throws Exception;

    public abstract String EvaluateString() throws Exception;
}
