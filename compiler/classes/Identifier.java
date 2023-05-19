package compiler.classes;

import java.util.ArrayList;

public class Identifier extends Node {

    public Identifier(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate(SymbolTable symbolTable) throws Exception {
        return symbolTable.get(value);
    }
}
