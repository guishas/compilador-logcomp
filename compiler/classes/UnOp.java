package compiler.classes;

public class UnOp extends Node {

    public UnOp(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public int Evaluate() {
        if (value.equals("-")) {
            return -children[0].Evaluate();
        }

        return children[0].Evaluate();
    }
}
