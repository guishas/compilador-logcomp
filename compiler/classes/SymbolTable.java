package compiler.classes;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    public static HashMap<String, ArrayList<String>> symbolTable = new HashMap<>();

    public SymbolTable() {

    }

    public static void create(String key, ArrayList<String> arrayList) throws Exception {
        if (!symbolTable.containsKey(key)) {
            symbolTable.put(key, arrayList);
        } else {
            throw new Exception();
        }
    }

    public static ArrayList<String> get(String key) throws Exception {
        if (symbolTable.containsKey(key)) {
            return symbolTable.get(key);
        } else {
            throw new Exception();
        }
    }

    public static void set(String key, ArrayList<String> arrayList) throws Exception {
        if (symbolTable.containsKey(key)) {
            if (symbolTable.get(key).get(0).equals(arrayList.get(0))) {
                symbolTable.put(key, arrayList);
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }
}
