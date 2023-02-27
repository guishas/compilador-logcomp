//public class Tokenizer {
//    private final Alphabet alphabet;
//    private final String source;
//    private char currentChar;
//    private Integer position;
//    private Token next;
//
//    public Tokenizer(String source) {
//        this.alphabet = new Alphabet();
//        this.source = source;
//        this.position = 0;
//        this.currentChar = source.charAt(position);
//    }
//
//    public void selectNext() {
//        while (currentChar != '"') {
//            if (currentChar == ' ') {
//                advance();
//            } else if (Character.isDigit(currentChar)) {
//                makeNumber();
//                return;
//            } else if (currentChar == alphabet.TT_PLUS.charAt(0)) {
//                next = new Token("TT_PLUS", alphabet.TT_PLUS);
//                advance();
//                return;
//            } else if (currentChar == alphabet.TT_MINUS.charAt(0)) {
//                next = new Token("TT_MINUS", alphabet.TT_MINUS);
//                advance();
//                return;
//            }
//        }
//    }
//
//    public String getSource() {
//        return source;
//    }
//
//    public Integer getPosition() {
//        return position;
//    }
//
//    public Token getNext() {
//        return next;
//    }
//
//    public Alphabet getAlphabet() {
//        return alphabet;
//    }
//
//    public char getCurrentChar() {
//        return currentChar;
//    }
//
//    public void advance() {
//        position++;
//        if (position < source.length()) {
//            currentChar = source.charAt(position);
//        } else {
//            currentChar = '"';
//        }
//    }
//
//    public void makeNumber() {
//        StringBuilder num = new StringBuilder();
//
//        while (currentChar != '"' && Character.isDigit(currentChar)) {
//            num.append(currentChar);
//            advance();
//        }
//
//        next = new Token(alphabet.TT_INT, num.toString());
//    }
//
//    public boolean checkIfTokenIsNumber(Token token) {
//        return token.getType().equals(alphabet.TT_INT);
//    }
//}
