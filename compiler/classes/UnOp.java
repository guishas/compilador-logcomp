package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class UnOp extends Node {

    public UnOp(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate() throws Exception {
        if (value.equals("-")) {
            return new ArrayList<>(Arrays.asList("Int", "-" + children[0].Evaluate().get(1)));
        } else if (value.equals("!")) {
            String bool = children[0].Evaluate().get(1);
            if (bool.equals("0")) {
                return new ArrayList<>(Arrays.asList("Int", "1"));
            } else {
                return new ArrayList<>(Arrays.asList("Int", "0"));
            }
        } else {
            return new ArrayList<>(Arrays.asList("Int", children[0].Evaluate().get(1)));
        }
    }

    @Override
    public String EvaluateString() throws Exception {
        return null;
    }
}
