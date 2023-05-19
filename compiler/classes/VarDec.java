package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class VarDec extends Node {

    public VarDec(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate(SymbolTable symbolTable) throws Exception {
        symbolTable.create(children[0].getValue(), new ArrayList<>(Arrays.asList(value, children[1].Evaluate(symbolTable).get(1))));
        return new ArrayList<>();
    }
}
