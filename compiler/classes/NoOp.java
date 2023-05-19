package compiler.classes;

import java.util.ArrayList;

public class NoOp extends Node {

    public NoOp(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate(SymbolTable symbolTable) {
        return new ArrayList<>();
    }
}
