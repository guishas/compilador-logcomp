package compiler.classes;

public class UnOp extends Node {

    public UnOp(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public int Evaluate() throws Exception {
        if (value.equals("-")) {
            return -children[0].Evaluate();
        }

        if (value.equals("!")) {
            int bool = children[0].Evaluate();
            if (bool == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        return children[0].Evaluate();
    }
}
