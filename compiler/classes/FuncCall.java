package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class FuncCall extends Node {

    public FuncCall(String value, Node[] children) {
        super(value, children);
    }

    @Override
    public ArrayList<String> Evaluate(SymbolTable symbolTable) throws Exception {
        Node func = FuncTable.get(value);

        if (func.children.length - 2 != children.length) {
            throw new Exception("Number of arguments don't match");
        }

        SymbolTable funcSymbolTable = new SymbolTable();

        if (func.children.length > 2) {
            for (int i = 1; i < func.children.length-1; i++) {
                funcSymbolTable.create(
                        func.children[i].children[0].getValue(),
                        new ArrayList<>(Arrays.asList(func.children[i].getValue(), func.children[i].children[1].Evaluate(funcSymbolTable).get(1)))
                );
            }
        }

        if (func.children.length > 2) {
            for (int i = 0; i < children.length; i++) {
                Node arg = children[i];
                Node varDec = func.children[i+1];
                String argTypeGlobal = arg.Evaluate(symbolTable).get(0);
                String argTypeInFunc = varDec.children[0].Evaluate(funcSymbolTable).get(0);
                if (!argTypeInFunc.equals(argTypeGlobal)) {
                    throw new Exception("Type mismatch");
                } else {
                    funcSymbolTable.set(
                            varDec.children[0].getValue(),
                            arg.Evaluate(symbolTable)
                    );
                }
            }
        }

        ArrayList<String> funcDecReturn = func.children[func.children.length-1].Evaluate(funcSymbolTable);
        if (!funcDecReturn.get(0).equals(func.getValue())) {
            throw new Exception("Type mismatch");
        }
        return new ArrayList<>(Arrays.asList(funcDecReturn.get(0), funcDecReturn.get(1)));
    }
}
