package compiler.classes;

import java.util.ArrayList;

public class Identifier extends Node {

    public Identifier(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate() throws Exception {
        return SymbolTable.get(value);
    }

    @Override
    public String EvaluateString() throws Exception {
        return null;
    }
}
