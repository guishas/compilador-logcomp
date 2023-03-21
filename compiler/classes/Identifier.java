package compiler.classes;

public class Identifier extends Node {

    public Identifier(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public int Evaluate() throws Exception {
        return Integer.parseInt(SymbolTable.get(value));
    }
}
