package compiler.classes;

import java.util.ArrayList;

public class Block extends Node {

    public Block(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate(SymbolTable symbolTable) throws Exception {
        for (Node node : children) {
            if (node.getValue().equals("TT_RETURN")) {
                return node.Evaluate(symbolTable);
            } else {
                node.Evaluate(symbolTable);
            }
        }

        return new ArrayList<>();
    }
}
