package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class StrVal extends Node {

    public StrVal(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public void Evaluate() throws Exception {

    }
}
