package compiler.classes;

import java.util.ArrayList;

public class Identifier extends Node {

    public Identifier(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public void Evaluate() throws Exception {
        ArrayList<String> ret = SymbolTable.get(value);
        Writer.writeToFileAppend("  MOV EBX, " + ret.get(1) + "\n");
    }
}
