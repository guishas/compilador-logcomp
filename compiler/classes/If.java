package compiler.classes;

import java.util.ArrayList;

public class If extends Node {

    public If(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public void Evaluate() throws Exception {
        children[0].Evaluate();
        Writer.writeToFileAppend("  CMP EBX, True\n");
        Writer.writeToFileAppend("  JE IF_" + i + "\n");
        if (children.length == 3) {
            children[2].Evaluate();
            Writer.writeToFileAppend("  JMP ENDIF_" + i + "\n");
        } else {
            Writer.writeToFileAppend("  JMP ENDIF_" + i + "\n");
        }
        Writer.writeToFileAppend("  IF_" + i + ":\n");
        children[1].Evaluate();
        Writer.writeToFileAppend("  ENDIF_" + i + ":\n");
    }
}
