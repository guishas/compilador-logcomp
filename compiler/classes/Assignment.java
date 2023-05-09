package compiler.classes;

import java.util.ArrayList;

public class Assignment extends Node {

    public Assignment(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate() throws Exception {
        SymbolTable.set(children[0].getValue(), children[1].Evaluate());
        return new ArrayList<>();
    }

    @Override
    public String EvaluateString() throws Exception {
        return null;
    }
}
