package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class IntVal extends Node {

    public IntVal(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public void Evaluate() {
        Writer.writeToFileAppend("  MOV EBX, " + value + "\n");
    }
}
