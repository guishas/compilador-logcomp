package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class UnOp extends Node {

    public UnOp(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public void Evaluate() throws Exception {
        children[0].Evaluate();
        if (value.equals("-")) {
            Writer.writeToFileAppend("  NEG EBX\n");
        } else if (value.equals("!")) {
            Writer.writeToFileAppend("  NOT EBX\n");
        }
    }
}
