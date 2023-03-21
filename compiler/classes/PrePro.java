package compiler.classes;

public class PrePro {
    public String filter(String sourceCode) {
        StringBuilder output = new StringBuilder();
        boolean ignoreMode = false;

        for (int i = 0; i < sourceCode.length(); i++) {
            char c = sourceCode.charAt(i);

            if (c == '#') {
                ignoreMode = true;
            } else if (c == '\n') {
                ignoreMode = false;
                output.append(c);
            } else if (!ignoreMode) {
                output.append(c);
            }
        }

        return output.toString().trim().replaceAll("\\R", "Ë");
    }
}
