package compiler.classes;

import java.util.ArrayList;

public class BinOp extends Node {

    public BinOp(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public void Evaluate() throws Exception {
        children[0].Evaluate();
        Writer.writeToFileAppend("  PUSH EBX\n");
        children[1].Evaluate();
        Writer.writeToFileAppend("  POP EAX\n");
        if (value.equals(".")) {

        } else if (value.equals("+")) {
            Writer.writeToFileAppend("  ADD EAX, EBX\n");
            Writer.writeToFileAppend("  MOV EBX, EAX\n");
        } else if (value.equals("-")) {
            Writer.writeToFileAppend("  SUB EAX, EBX\n");
            Writer.writeToFileAppend("  MOV EBX, EAX\n");
        } else if (value.equals("*")) {
            Writer.writeToFileAppend("  IMUL EAX, EBX\n");
            Writer.writeToFileAppend("  MOV EBX, EAX\n");
        } else if (value.equals("/")) {
            Writer.writeToFileAppend("  IDIV EAX, EBX\n");
            Writer.writeToFileAppend("  MOV EBX, EAX\n");
        } else if (value.equals("&&")) {
            Writer.writeToFileAppend("  AND EAX, EBX\n");
            Writer.writeToFileAppend("  MOV EBX, EAX\n");
        } else if (value.equals("||")) {
            Writer.writeToFileAppend("  OR EAX, EBX\n");
            Writer.writeToFileAppend("  MOV EBX, EAX\n");
        } else if (value.equals("==")) {
            Writer.writeToFileAppend("  CMP EAX, EBX\n");
            Writer.writeToFileAppend("  CALL binop_je\n");
        } else if (value.equals("<")) {
            Writer.writeToFileAppend("  CMP EAX, EBX\n");
            Writer.writeToFileAppend("  CALL binop_jl\n");
        } else if (value.equals(">")) {
            Writer.writeToFileAppend("  CMP EAX, EBX\n");
            Writer.writeToFileAppend("  CALL binop_jg\n");
        } else {
            throw new Exception();
        }
    }
}
