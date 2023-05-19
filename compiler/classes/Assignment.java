package compiler.classes;

import java.util.ArrayList;

public class Assignment extends Node {

    public Assignment(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate(SymbolTable symbolTable) throws Exception {
        symbolTable.set(children[0].getValue(), children[1].Evaluate(symbolTable));
        return new ArrayList<>();
    }
}
