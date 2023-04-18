package compiler.classes;

public class If extends Node {

    public If(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public int Evaluate() throws Exception {
        if (children[0].Evaluate() == 1) {
            children[1].Evaluate();
        } else {
            if (children.length == 3) {
                children[2].Evaluate();
            }
        }

        return 0;
    }
}
