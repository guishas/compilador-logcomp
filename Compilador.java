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
            int res = parseTerm();
            while (tokenizer.getNext().getValue().equals(tokenizer.getAlphabet().TT_PLUS) ||
                    tokenizer.getNext().getValue().equals(tokenizer.getAlphabet().TT_MINUS)) {
                if (tokenizer.getNext().getValue().equals(tokenizer.getAlphabet().TT_PLUS)) {
                    tokenizer.selectNext();
                    res += parseTerm();
                } else {
                    tokenizer.selectNext();
                    res -= parseTerm();
                }
            }

            return res;
        }

        public Integer parseTerm() throws Exception {
            int res = parseFactor();
            while (tokenizer.getNext().getValue().equals(tokenizer.getAlphabet().TT_MULT) ||
                    tokenizer.getNext().getValue().equals(tokenizer.getAlphabet().TT_DIV)) {
                if (tokenizer.getNext().getValue().equals(tokenizer.getAlphabet().TT_MULT)) {
                    tokenizer.selectNext();

                    if (tokenizer.getNext().getValue().equals(tokenizer.getAlphabet().TT_LEFT_PAR)) {
                        res *= parseFactor();
                    } else if (tokenizer.getNext().getType().equals(tokenizer.getAlphabet().TT_INT)) {
                        res *= Integer.parseInt(tokenizer.getNext().getValue());
                    } else {
                        throw new Exception();
                    }

                } else {
                    tokenizer.selectNext();

                    if (tokenizer.getNext().getValue().equals(tokenizer.getAlphabet().TT_LEFT_PAR)) {
                        res = (int) Math.ceil((double) res / parseFactor());
                    } else if (tokenizer.getNext().getType().equals(tokenizer.getAlphabet().TT_INT)) {
                        res = (int) Math.ceil((double) res / Integer.parseInt(tokenizer.getNext().getValue()));
                    } else {
                        throw new Exception();
                    }
                }
            }

            return res;
        }

        public Integer parseFactor() throws Exception {
            int res = 0;
            if (tokenizer.checkIfTokenIsNumber(tokenizer.getNext())) {
                res = Integer.parseInt(tokenizer.getNext().getValue());
                tokenizer.selectNext();
                return res;
            } if (tokenizer.getNext().getValue().equals(tokenizer.getAlphabet().TT_PLUS)) {
                tokenizer.selectNext();
                return parseTerm();
            } else if (tokenizer.getNext().getValue().equals(tokenizer.getAlphabet().TT_MINUS)) {
                tokenizer.selectNext();
                return parseTerm();
            } else if (tokenizer.getNext().getValue().equals(tokenizer.getAlphabet().TT_LEFT_PAR)) {
                tokenizer.selectNext();
                res = parseExpression();

                if (tokenizer.getNext().getValue().equals(tokenizer.getAlphabet().TT_RIGHT_PAR)) {
                    tokenizer.selectNext();
                    return res;
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
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

    class PrePro {
        public String filter(String sourceCode) {
            int pos = -1;
            for (int i = 0; i < sourceCode.length(); i++) {
                if (sourceCode.charAt(i) == '#') {
                    if (pos == -1) {
                        pos = i;
                    }
                }
            }

            if (pos == -1) {
                return sourceCode;
            } else {
                return sourceCode.substring(0, pos);
            }
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
                    if (lastToken.getType().equals(alphabet.TT_INT) && Character.isDigit(currentChar)) {
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
                } else if (currentChar == alphabet.TT_MULT.charAt(0)) {
                    next = new Token("TT_MULT", alphabet.TT_MULT);
                    advance();
                    return;
                } else if (currentChar == alphabet.TT_DIV.charAt(0)) {
                    next = new Token("TT_DIV", alphabet.TT_DIV);
                    advance();
                    return;
                } else if (currentChar == alphabet.TT_LEFT_PAR.charAt(0)) {
                    next = new Token("TT_LEFT_PAR", alphabet.TT_LEFT_PAR);
                    advance();
                    return;
                } else if (currentChar == alphabet.TT_RIGHT_PAR.charAt(0)) {
                    next = new Token("TT_RIGHT_PAR", alphabet.TT_RIGHT_PAR);
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
        public String TT_MULT;
        public String TT_DIV;
        public String TT_LEFT_PAR;
        public String TT_RIGHT_PAR;

        public Alphabet() {
            initAlphabet();
        }

        public void initAlphabet() {
            TT_PLUS = "+";
            TT_MINUS = "-";
            TT_INT = "TT_INT";
            TT_MULT = "*";
            TT_DIV = "/";
            TT_LEFT_PAR = "(";
            TT_RIGHT_PAR = ")";
        }
    }

    public static void main(String[] args) throws Exception {
        Compilador c = new Compilador();
        Compilador.Parser p = c.new Parser();
        Compilador.PrePro preP = c.new PrePro();
        p.run(preP.filter(args[0]));
    }
}
