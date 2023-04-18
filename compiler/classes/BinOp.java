package compiler.classes;

import java.util.ArrayList;

public class BinOp extends Node {

    public BinOp(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public int Evaluate() throws Exception {
        if (value.equals("*")) {
            return children[0].Evaluate() * children[1].Evaluate();
        } else if (value.equals("/")) {
            return (int) Math.floor((double) children[0].Evaluate() / children[1].Evaluate());
        } else if (value.equals("+")) {
            return children[0].Evaluate() + children[1].Evaluate();
        } else if (value.equals("&&")) {
            return children[0].Evaluate() & children[1].Evaluate();
        } else if (value.equals("||")) {
            return children[0].Evaluate() | children[1].Evaluate();
        } else if (value.equals("==")) {
            if (children[0].Evaluate() == children[1].Evaluate()) {
                return 1;
            } else {
                return 0;
            }
        } else if (value.equals(">")) {
            if (children[0].Evaluate() > children[1].Evaluate()) {
                return 1;
            } else {
                return 0;
            }
        } else if (value.equals("<")) {
            if (children[0].Evaluate() < children[1].Evaluate()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return children[0].Evaluate() - children[1].Evaluate();
        }
    }
}
