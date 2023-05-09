package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class IntVal extends Node {

    public IntVal(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate() {
        return new ArrayList<>(Arrays.asList("int", value));
    }

    @Override
    public String EvaluateString() throws Exception {
        return null;
    }
}
