package compiler.classes;

import java.util.Scanner;

public class Read extends Node {

    private final Scanner sc;

    public Read(String value, Node[] children) {
        super(value, children);
        sc = new Scanner(System.in);
    }

    @Override
    public int Evaluate() throws Exception {
        int i = sc.nextInt();
        sc.nextLine();
        return i;
    }
}
