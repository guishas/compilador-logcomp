package compiler.classes;

public class Print extends Node {

    public Print(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public int Evaluate() throws Exception {
        System.out.println(children[0].Evaluate());
        return 0;
    }
}
