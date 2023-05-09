package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class VarDec extends Node {

    public VarDec(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate() throws Exception {
        SymbolTable.create(children[0].getValue(), new ArrayList<>(Arrays.asList(value, children[1].Evaluate().get(1))));
        return new ArrayList<>();
    }

    @Override
    public String EvaluateString() throws Exception {
        return null;
    }
}
