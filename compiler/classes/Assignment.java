package compiler.classes;

import java.util.ArrayList;

public class Assignment extends Node {

    public Assignment(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public void Evaluate() throws Exception {
        children[1].Evaluate();
        ArrayList<String> ret = SymbolTable.get(children[0].getValue());
        Writer.writeToFileAppend("  MOV " + ret.get(1) + ", EBX\n");
    }
}
