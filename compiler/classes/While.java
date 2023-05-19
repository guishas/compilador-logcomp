package compiler.classes;

import java.util.ArrayList;

public class While extends Node {

    public While(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate(SymbolTable symbolTable) throws Exception {
        while (children[0].Evaluate(symbolTable).get(1).equals("1")) {
            children[1].Evaluate(symbolTable);
        }

        return new ArrayList<>();
    }
}
