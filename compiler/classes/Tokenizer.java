package compiler.classes;

public class Tokenizer {
    private final String source;
    private Integer position;
    private Token next;

    public Tokenizer(String source) {
        this.source = source;
        this.position = 0;
    }

    public void selectNext() throws Exception {
        if (position == source.length()) {
            next = new Token("EOF", "EOF");
            return;
        }

        while (source.charAt(position) == ' ') {
            position++;
        }

        if (Character.isDigit(source.charAt(position))) {
            makeNumber();
        } else if (source.charAt(position) == '+') {
            next = new Token("TT_PLUS", "+");
            position++;
        } else if (source.charAt(position) == '-') {
            next = new Token("TT_MINUS", "-");
            position++;
        } else if (source.charAt(position) == '*') {
            next = new Token("TT_MULT", "*");
            position++;
        } else if (source.charAt(position) == '/') {
            next = new Token("TT_DIV", "/");
            position++;
        } else if (source.charAt(position) == '(') {
            next = new Token("TT_LEFT_PAR", "(");
            position++;
        } else if (source.charAt(position) == ')') {
            next = new Token("TT_RIGHT_PAR", ")");
            position++;
        } else {
            throw new Exception("Invalid character!");
        }
    }

    public Token getNext() {
        return next;
    }

    public void makeNumber() {
        StringBuilder num = new StringBuilder();

        while (position < source.length() && Character.isDigit(source.charAt(position))) {
            num.append(source.charAt(position));
            position++;
        }

        next = new Token("TT_INT", num.toString());
    }
}
