package compiler.classes;

public class PrePro {
    public String filter(String sourceCode) {
        int pos = sourceCode.indexOf("#");

        if (pos == -1) {
            return sourceCode.trim();
        }

        return sourceCode.substring(0, pos).trim();
    }
}
