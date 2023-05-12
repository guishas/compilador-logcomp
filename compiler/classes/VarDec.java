package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class VarDec extends Node {

    public VarDec(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public void Evaluate() throws Exception {
        SymbolTable.create(children[0].getValue(), new ArrayList<>(Arrays.asList(value)));
        Writer.writeToFileAppend("  PUSH DWORD 0\n");
    }
}
