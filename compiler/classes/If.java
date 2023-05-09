package compiler.classes;

import java.util.ArrayList;

public class If extends Node {

    public If(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate() throws Exception {
        if (children[0].Evaluate().get(1).equals("1")) {
            children[1].Evaluate();
        } else if (children.length == 3) {
            children[2].Evaluate();
        }

        return new ArrayList<>();
    }

    @Override
    public String EvaluateString() throws Exception {
        return null;
    }
}
