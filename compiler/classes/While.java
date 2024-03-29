package compiler.classes;

import java.util.ArrayList;

public class While extends Node {

    public While(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate() throws Exception {
        while (children[0].Evaluate().get(1).equals("1")) {
            children[1].Evaluate();
        }

        return new ArrayList<>();
    }

    @Override
    public String EvaluateString() throws Exception {
        return null;
    }
}
