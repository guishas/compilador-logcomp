package compiler.classes;

import java.util.ArrayList;

public class While extends Node {

    public While(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public void Evaluate() throws Exception {
        Writer.writeToFileAppend("  WHILE_" + i + ":\n");
        children[0].Evaluate();
        Writer.writeToFileAppend("  CMP EBX, False\n");
        Writer.writeToFileAppend("  JE ENDWHILE_" + i + "\n");
        children[1].Evaluate();
        Writer.writeToFileAppend("  JMP WHILE_" + i + "\n");
        Writer.writeToFileAppend("  ENDWHILE_" + i + ":\n");
    }
}
