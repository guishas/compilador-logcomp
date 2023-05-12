package compiler.classes;

import java.util.ArrayList;

public class Block extends Node {

    public Block(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public void Evaluate() throws Exception {
        for (Node node : children) {
            node.Evaluate();
        }
    }
}
