package compiler.classes;

import java.util.ArrayList;

public class Print extends Node {

    public Print(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public void Evaluate() throws Exception {
        children[0].Evaluate();
        Writer.writeToFileAppend("  PUSH EBX\n");
        Writer.writeToFileAppend("  CALL print\n");
        Writer.writeToFileAppend("  POP EBX\n");
    }
}
