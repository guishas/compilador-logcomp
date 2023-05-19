package compiler.classes;

import java.util.ArrayList;

public class Return extends Node {

    public Return(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate(SymbolTable symbolTable) throws Exception {
        return children[0].Evaluate(symbolTable);
    }
}
