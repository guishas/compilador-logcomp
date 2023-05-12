package compiler.classes;

import java.util.ArrayList;

public class While extends Node {

    private int index;

    public While(String value, Node[] children) {
        super(value, children);
        this.index = i;
        System.out.println(index);
    }

    @Override
    public void Evaluate() throws Exception {
        Writer.writeToFileAppend("  WHILE_" + index + ":\n");
        children[0].Evaluate();
        Writer.writeToFileAppend("  CMP EBX, False\n");
        Writer.writeToFileAppend("  JE ENDWHILE_" + index + "\n");
        children[1].Evaluate();
        Writer.writeToFileAppend("  JMP WHILE_" + index + "\n");
        Writer.writeToFileAppend("  ENDWHILE_" + index + ":\n");
    }
}
