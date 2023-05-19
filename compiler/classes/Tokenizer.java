package compiler.classes;

import java.util.Arrays;

public class Tokenizer {
    private final String source;
    private Integer position;
    private Token next;
    private final String[] reservedWords;

    public Tokenizer(String source) {
        this.source = source;
        this.position = 0;
        this.reservedWords = new String[]{"println", "readline", "function", "return", "if", "else", "end", "while", "Int", "String"};
    }

    public void selectNext() throws Exception {
        if (position == source.length()) {
            next = new Token("EOF", "EOF");
            return;
        }

        while (source.charAt(position) == ' ') {
            position++;

            if (source.charAt(position) != ' ') {
                Token lastToken = getNext();
                if (lastToken.getType().equals("TT_INT") && Character.isDigit(source.charAt(position))) {
                    throw new Exception("Whitespace between digits!");
                }
            }
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
        } else if (source.charAt(position) == '.') {
            next = new Token("TT_CONCAT", ".");
            position++;
        } else if (source.charAt(position) == ',') {
            next = new Token("TT_COMMA", ",");
            position++;
        } else if (source.charAt(position) == '(') {
            next = new Token("TT_LEFT_PAR", "(");
            position++;
        } else if (source.charAt(position) == ')') {
            next = new Token("TT_RIGHT_PAR", ")");
            position++;
        } else if (source.charAt(position) == '=') {
            if (source.charAt(position+1) == '=') {
                next = new Token("TT_IS_EQUAL_TO", "==");
                position+=2;
            } else {
                next = new Token("TT_EQUALS", "=");
                position++;
            }
        } else if (source.charAt(position) == '!') {
            next = new Token("TT_NOT", "!");
            position++;
        } else if (source.charAt(position) == '>') {
            next = new Token("TT_GREATER", ">");
            position++;
        } else if (source.charAt(position) == '<') {
            next = new Token("TT_LESS", "<");
            position++;
        } else if (source.charAt(position) == '&') {
            if (source.charAt(position+1) == '&') {
                next = new Token("TT_AND", "&&");
                position+=2;
            } else {
                throw new Exception("& not a valid character!");
            }
        } else if (source.charAt(position) == '|') {
            if (source.charAt(position+1) == '|') {
                next = new Token("TT_OR", "||");
                position+=2;
            } else {
                throw new Exception("| not a valid character!");
            }
        } else if (source.charAt(position) == ':') {
            if (source.charAt(position+1) == ':') {
                next = new Token("TT_TYPE_ASSIGN", "::");
                position+=2;
            } else {
                throw new Exception(": not a valid character");
            }
        } else if (source.charAt(position) == '"') {
            position++;
            makeString();
        } else if (source.charAt(position) == 'Ë') {
            next = new Token("TT_ENDLINE", "\\n");
            position++;
        } else if (Character.isLetter(source.charAt(position))) {
            makeWord();
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

    public void makeString() {
        StringBuilder str = new StringBuilder();

        while (position < source.length() && source.charAt(position) != '"') {
            str.append(source.charAt(position));
            position++;
        }

        position++;
        next = new Token("TT_STRING", str.toString());
    }

    public void makeWord() throws Exception {
        StringBuilder word = new StringBuilder();

        while (position < source.length() && String.valueOf(source.charAt(position)).matches("[a-zA-Z0-9_]+")) {
            word.append(source.charAt(position));
            position++;
        }

        if (Arrays.asList(reservedWords).contains(word.toString())) {
            String reserved = "";
            for (String reservedWord : reservedWords) {
                if (reservedWord.equals(word.toString())) {
                    reserved = reservedWord;
                }
            }

            if (reserved.equals("Int") || reserved.equals("String")) {
                next = new Token("TT_TYPE", reserved.toLowerCase());
            } else if (reserved.equals("end")) {
                if (!next.getType().equals("TT_ENDLINE")) {
                    throw new Exception();
                } else {
                    next = new Token("TT_" + reserved.toUpperCase(), reserved);
                }
            } else {
                next = new Token("TT_" + reserved.toUpperCase(), reserved);
            }
        } else {
            if (Character.isDigit(word.toString().charAt(0))) {
                throw new Exception("Invalid character!");
            } else if (!word.toString().matches("[a-zA-Z0-9_]+")) {
                throw new Exception("Invalid character!");
            } else {
                next = new Token("TT_IDENTIFIER", word.toString());
            }
        }
    }
}
