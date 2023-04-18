package compiler.classes;

public class While extends Node {

    public While(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public int Evaluate() throws Exception {
        while (children[0].Evaluate() == 1) {
            children[1].Evaluate();
        }

        return 0;
    }
}
