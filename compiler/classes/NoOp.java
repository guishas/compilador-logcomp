package compiler.classes;

public class NoOp extends Node {

    public NoOp(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public int Evaluate() {
        return -1;
    }
}
