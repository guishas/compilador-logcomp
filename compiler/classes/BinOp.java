package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class BinOp extends Node {

    public BinOp(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate() throws Exception {
        String a = children[0].Evaluate().get(1);
        String b = children[1].Evaluate().get(1);
        if (children[0].Evaluate().get(0).equals("string") || children[1].Evaluate().get(0).equals("string")) {
            if (value.equals(".")) {
                return new ArrayList<>(Arrays.asList("String", a + b));
            } else if (value.equals("&&")) {
                if (Integer.parseInt(a) == 1 && Integer.parseInt(b) == 1) {
                    return new ArrayList<>(Arrays.asList("Int", "1"));
                } else {
                    return new ArrayList<>(Arrays.asList("Int", "0"));
                }
            } else if (value.equals("||")) {
                if (Integer.parseInt(a) == 1 || Integer.parseInt(b) == 1) {
                    return new ArrayList<>(Arrays.asList("Int", "1"));
                } else {
                    return new ArrayList<>(Arrays.asList("Int", "0"));
                }
            } else if (value.equals("==")) {
                if (Integer.parseInt(a) == Integer.parseInt(b)) {
                    return new ArrayList<>(Arrays.asList("Int", "1"));
                } else {
                    return new ArrayList<>(Arrays.asList("Int", "0"));
                }
            } else if (value.equals("<")) {
                if (Integer.parseInt(a) < Integer.parseInt(b)) {
                    return new ArrayList<>(Arrays.asList("Int", "1"));
                } else {
                    return new ArrayList<>(Arrays.asList("Int", "0"));
                }
            } else if (value.equals(">")) {
                if (Integer.parseInt(a) > Integer.parseInt(b)) {
                    return new ArrayList<>(Arrays.asList("Int", "1"));
                } else {
                    return new ArrayList<>(Arrays.asList("Int", "0"));
                }
            } else {
                throw new Exception();
            }
        } else {
            if (value.equals("+")) {
                return new ArrayList<>(Arrays.asList("Int", Integer.toString(Integer.parseInt(a) + Integer.parseInt(b))));
            } else if (value.equals("-")) {
                return new ArrayList<>(Arrays.asList("Int", Integer.toString(Integer.parseInt(a) - Integer.parseInt(b))));
            } else if (value.equals("*")) {
                return new ArrayList<>(Arrays.asList("Int", Integer.toString(Integer.parseInt(a) * Integer.parseInt(b))));
            } else if (value.equals("/")) {
                int div = (int) Math.floor((double) Integer.parseInt(a) / Integer.parseInt(b));
                return new ArrayList<>(Arrays.asList("Int", Integer.toString(div)));
            } else if (value.equals("&&")) {
                if (Integer.parseInt(a) == 1 && Integer.parseInt(b) == 1) {
                    return new ArrayList<>(Arrays.asList("Int", "1"));
                } else {
                    return new ArrayList<>(Arrays.asList("Int", "0"));
                }
            } else if (value.equals("||")) {
                if (Integer.parseInt(a) == 1 || Integer.parseInt(b) == 1) {
                    return new ArrayList<>(Arrays.asList("Int", "1"));
                } else {
                    return new ArrayList<>(Arrays.asList("Int", "0"));
                }
            } else if (value.equals("==")) {
                if (Integer.parseInt(a) == Integer.parseInt(b)) {
                    return new ArrayList<>(Arrays.asList("Int", "1"));
                } else {
                    return new ArrayList<>(Arrays.asList("Int", "0"));
                }
            } else if (value.equals("<")) {
                if (Integer.parseInt(a) < Integer.parseInt(b)) {
                    return new ArrayList<>(Arrays.asList("Int", "1"));
                } else {
                    return new ArrayList<>(Arrays.asList("Int", "0"));
                }
            } else if (value.equals(">")) {
                if (Integer.parseInt(a) > Integer.parseInt(b)) {
                    return new ArrayList<>(Arrays.asList("Int", "1"));
                } else {
                    return new ArrayList<>(Arrays.asList("Int", "0"));
                }
            } else {
                throw new Exception();
            }
        }
    }

    @Override
    public String EvaluateString() throws Exception {
        return null;
    }
}
