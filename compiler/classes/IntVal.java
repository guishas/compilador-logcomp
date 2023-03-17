package compiler.classes;

public class IntVal extends Node {

    public IntVal(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public int Evaluate() {
        return Integer.parseInt(value);
    }
}
