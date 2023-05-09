package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class StrVal extends Node {

    public StrVal(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate() throws Exception {
        return new ArrayList<>(Arrays.asList("String", value));
    }

    public String EvaluateString() {
        return value;
    }
}
