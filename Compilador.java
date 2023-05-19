import compiler.classes.*;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Compilador {
    public static void main(String[] args) throws Exception {
        String content = Files.readString(Paths.get(args[0]).toAbsolutePath());
        PrePro preP = new PrePro();
        Parser p = new Parser(preP.filter(content));
        SymbolTable symbolTable = new SymbolTable();
        p.run(symbolTable);
    }
}
