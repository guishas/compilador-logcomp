package compiler.classes;

import java.util.HashMap;

public class SymbolTable {
    public static HashMap<String, String> symbolTable = new HashMap<>();

    public SymbolTable() {

    }

    public static String get(String key) throws Exception {
        if (symbolTable.containsKey(key)) {
            return symbolTable.get(key);
        } else {
            throw new Exception();
        }
    }

    public static void set(String key, String value) {
        symbolTable.put(key, value);
    }
}
