package compiler.classes;

public class Assignment extends Node {

    public Assignment(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public int Evaluate() throws Exception {
        SymbolTable.set(children[0].getValue(), String.valueOf(children[1].Evaluate()));
        return 0;
    }
}
