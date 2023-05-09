package compiler.classes;

import java.util.ArrayList;

public class Print extends Node {

    public Print(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate() throws Exception {
        System.out.println(children[0].Evaluate().get(1));
        return new ArrayList<>();
    }

    @Override
    public String EvaluateString() throws Exception {
        return null;
    }
}
