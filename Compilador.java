import compiler.classes.*;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Compilador {
    public static void main(String[] args) throws Exception {
        String content = Files.readString(Paths.get(args[0]).toAbsolutePath());
        String fileName = Paths.get(args[0]).toAbsolutePath().toString().split("\\.")[0];
        Writer.setFilePath(fileName + ".asm");
        Writer.clearFile();
        String headerPath = Paths.get("header.txt").toAbsolutePath().toString();
        Writer.writeHeader(headerPath);
        String footerPath = Paths.get("footer.txt").toAbsolutePath().toString();
        PrePro preP = new PrePro();
        Parser p = new Parser(preP.filter(content));
        p.run();
        Writer.writeFooter(footerPath);
    }
}
