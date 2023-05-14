package compiler.classes;

public class If extends Node {

    private int index;

    public If(String value, Node[] children) {
        super(value, children);
        this.index = i;
    }

    @Override
    public void Evaluate() throws Exception {
        children[0].Evaluate();
        Writer.writeToFileAppend("  CMP EBX, True\n");
        Writer.writeToFileAppend("  JE IF_" + index + "\n");
        if (children.length == 3) {
            children[2].Evaluate();
            Writer.writeToFileAppend("  JMP ENDIF_" + index + "\n");
        } else {
            Writer.writeToFileAppend("  JMP ENDIF_" + index + "\n");
        }
        Writer.writeToFileAppend("  IF_" + index + ":\n");
        children[1].Evaluate();
        Writer.writeToFileAppend("  ENDIF_" + index + ":\n");
    }
}
