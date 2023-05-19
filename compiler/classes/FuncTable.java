package compiler.classes;

import java.util.HashMap;

public class FuncTable {
    public static HashMap<String, Node> funcTable = new HashMap<>();

    public FuncTable() {

    }

    public static void create(String key, Node reference) throws Exception {
        if (!funcTable.containsKey(key)) {
            funcTable.put(key, reference);
        } else {
            throw new Exception("There is already a function declared with the name " + key);
        }
    }

    public static Node get(String key) throws Exception {
        if (funcTable.containsKey(key)) {
            return funcTable.get(key);
        } else {
            throw new Exception("There is no function declared with name " + key);
        }
    }
}
