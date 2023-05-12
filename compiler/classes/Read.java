package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Read extends Node {
    private static final Scanner sc = new Scanner(System.in);

    public Read(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public void Evaluate() throws Exception {
        sc.reset();
    }
}
