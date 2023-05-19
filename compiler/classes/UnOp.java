package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class UnOp extends Node {

    public UnOp(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate(SymbolTable symbolTable) throws Exception {
        if (value.equals("-")) {
            return new ArrayList<>(Arrays.asList("int", "-" + children[0].Evaluate(symbolTable).get(1)));
        } else if (value.equals("!")) {
            String bool = children[0].Evaluate(symbolTable).get(1);
            if (bool.equals("0")) {
                return new ArrayList<>(Arrays.asList("int", "1"));
            } else {
                return new ArrayList<>(Arrays.asList("int", "0"));
            }
        } else {
            return new ArrayList<>(Arrays.asList("int", children[0].Evaluate(symbolTable).get(1)));
        }
    }
}
