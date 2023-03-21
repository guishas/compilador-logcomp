package compiler.classes;

public class Block extends Node {

    public Block(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public int Evaluate() throws Exception {
        for (Node node : children) {
            node.Evaluate();
        }

        return 0;
    }
}
