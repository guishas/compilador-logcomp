package compiler.classes;

public class Parser {
    public Tokenizer tokenizer;

    public Parser(String sourceCode) {
        this.tokenizer = new Tokenizer(sourceCode);
    }

    public Node parseExpression() throws Exception {
        Node result = parseTerm();
        while (tokenizer.getNext().getType().equals("TT_PLUS") ||
                tokenizer.getNext().getType().equals("TT_MINUS")) {
            if (tokenizer.getNext().getType().equals("TT_PLUS")) {
                tokenizer.selectNext();
                result = new BinOp("+", new Node[]{result, parseTerm()});
            } else {
                tokenizer.selectNext();
                result = new BinOp("-", new Node[]{result, parseTerm()});
            }
        }

        return result;
    }

    public Node parseTerm() throws Exception {
        Node result = parseFactor();
        while (tokenizer.getNext().getType().equals("TT_MULT") || tokenizer.getNext().getType().equals("TT_DIV")) {
            if (tokenizer.getNext().getType().equals("TT_MULT")) {
                tokenizer.selectNext();

                if (tokenizer.getNext().getType().equals("TT_LEFT_PAR")) {
                    result = new BinOp("*", new Node[]{result, parseFactor()});
                    continue;
                } else if (tokenizer.getNext().getType().equals("TT_INT")) {
                    result = new BinOp("*", new Node[]{result, new IntVal(tokenizer.getNext().getValue(), new Node[]{})});
                } else {
                    throw new Exception();
                }

            } else {
                tokenizer.selectNext();

                if (tokenizer.getNext().getType().equals("TT_LEFT_PAR")) {
                    result = new BinOp("/", new Node[]{result, parseFactor()});
                    continue;
                } else if (tokenizer.getNext().getType().equals("TT_INT")) {
                    result = new BinOp("/", new Node[]{result, new IntVal(tokenizer.getNext().getValue(), new Node[]{})});
                } else {
                    throw new Exception();
                }
            }

            tokenizer.selectNext();
        }

        return result;
    }

    public Node parseFactor() throws Exception {
        Node ret;
        if (tokenizer.getNext().getType().equals("TT_INT")) {
            ret = new IntVal(tokenizer.getNext().getValue(), new Node[]{});
            tokenizer.selectNext();
            return ret;
        } else if (tokenizer.getNext().getType().equals("TT_PLUS")) {
            tokenizer.selectNext();
            return new UnOp("+", new Node[]{parseFactor()});
        } else if (tokenizer.getNext().getType().equals("TT_MINUS")) {
            tokenizer.selectNext();
            return new UnOp("-", new Node[]{parseFactor()});
        } else if (tokenizer.getNext().getType().equals("TT_LEFT_PAR")) {
            tokenizer.selectNext();
            ret = parseExpression();

            if (tokenizer.getNext().getType().equals("TT_RIGHT_PAR")) {
                tokenizer.selectNext();
                return ret;
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    public void run() throws Exception {
        Node tree = null;

        tokenizer.selectNext();
        while (!tokenizer.getNext().getType().equals("EOF")) {
            tree = parseExpression();
            if (tokenizer.getNext().getType().equals("TT_RIGHT_PAR")) {
                throw new Exception();
            }
        }

        System.out.println(tree.Evaluate());
    }
}
