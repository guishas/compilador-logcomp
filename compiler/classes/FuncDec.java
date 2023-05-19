package compiler.classes;

import java.util.ArrayList;

public class FuncDec extends Node {

    public FuncDec(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate(SymbolTable symbolTable) throws Exception {
        FuncTable.create(children[0].getValue(), this);
        return new ArrayList<>();
    }
}
