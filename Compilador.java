public class Compilador {
    class Token {
        private final String type;
        private final String value;

        public Token(String type, String value) {
            this.type = type;
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "type='" + type + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    class Parser {
        public Tokenizer tokenizer;

        public Parser() {

        }

        public Integer parseExpression() throws Exception {
            int res = 0;

            if (tokenizer.checkIfTokenIsNumber(tokenizer.getNext())) {
                res += Integer.parseInt(tokenizer.getNext().getValue());

                tokenizer.selectNext();
                Token t = tokenizer.getNext();
                while (t.getValue().equals(tokenizer.getAlphabet().TT_PLUS) ||
                        t.getValue().equals(tokenizer.getAlphabet().TT_MINUS)) {
                    if (t.getValue().equals(tokenizer.getAlphabet().TT_PLUS)) {
                        tokenizer.selectNext();
                        t = tokenizer.getNext();
                        if (t.getType().equals(tokenizer.getAlphabet().TT_INT)) {
                            res += Integer.parseInt(t.getValue());
                        } else {
                            throw new Exception();
                        }
                    }

                    if (t.getValue().equals(tokenizer.getAlphabet().TT_MINUS)) {
                        tokenizer.selectNext();
                        t = tokenizer.getNext();
                        if (t.getType().equals(tokenizer.getAlphabet().TT_INT)) {
                            res -= Integer.parseInt(t.getValue());
                        } else {
                            throw new Exception();
                        }
                    }

                    tokenizer.selectNext();
                    t = tokenizer.getNext();
                }

                return res;
            }

            throw new Exception();
        }

        public void run(String sourceCode) throws Exception {
            this.tokenizer = new Tokenizer(sourceCode);
            int total = 0;

            while (tokenizer.getCurrentChar() != '"') {
                tokenizer.selectNext();
                total = parseExpression();
            }

            System.out.println(total);
        }
    }

    class Tokenizer {
        private final Alphabet alphabet;
        private final String source;
        private char currentChar;
        private Integer position;
        private Token next;

        public Tokenizer(String source) {
            this.alphabet = new Alphabet();
            this.source = source;
            this.position = 0;
            this.currentChar = source.charAt(position);
        }

        public void selectNext() throws Exception {
            while (currentChar != '"') {
                if (currentChar == ' ') {
                    Token lastToken = getNext();
                    advance();
                    if (lastToken.getType().equals(alphabet.TT_INT) && next.getType().equals(alphabet.TT_INT)) {
                        throw new Exception();
                    }
                } else if (Character.isDigit(currentChar)) {
                    makeNumber();
                    return;
                } else if (currentChar == alphabet.TT_PLUS.charAt(0)) {
                    next = new Token("TT_PLUS", alphabet.TT_PLUS);
                    advance();
                    return;
                } else if (currentChar == alphabet.TT_MINUS.charAt(0)) {
                    next = new Token("TT_MINUS", alphabet.TT_MINUS);
                    advance();
                    return;
                } else {
                    throw new Exception();
                }
            }
        }

        public String getSource() {
            return source;
        }

        public Integer getPosition() {
            return position;
        }

        public Token getNext() {
            return next;
        }

        public Alphabet getAlphabet() {
            return alphabet;
        }

        public char getCurrentChar() {
            return currentChar;
        }

        public void advance() {
            position++;
            if (position < source.length()) {
                currentChar = source.charAt(position);
            } else {
                currentChar = '"';
            }
        }

        public void makeNumber() {
            StringBuilder num = new StringBuilder();

            while (currentChar != '"' && Character.isDigit(currentChar)) {
                num.append(currentChar);
                advance();
            }

            next = new Token(alphabet.TT_INT, num.toString());
        }

        public boolean checkIfTokenIsNumber(Token token) {
            return token.getType().equals(alphabet.TT_INT);
        }
    }

    class Alphabet {
        public String TT_PLUS;
        public String TT_MINUS;
        public String TT_INT;

        public Alphabet() {
            initAlphabet();
        }

        public void initAlphabet() {
            TT_PLUS = "+";
            TT_MINUS = "-";
            TT_INT = "TT_INT";
        }
    }

    public static void main(String[] args) throws Exception {
        Compilador c = new Compilador();
        Compilador.Parser p = c.new Parser();
        p.run(args[0]);
    }
}
