package compiler.classes;

import java.util.ArrayList;

public class If extends Node {

    public If(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate(SymbolTable symbolTable) throws Exception {
        if (children[0].Evaluate(symbolTable).get(1).equals("1")) {
            children[1].Evaluate(symbolTable);
        } else if (children.length == 3) {
            children[2].Evaluate(symbolTable);
        }

        return new ArrayList<>();
    }
}
