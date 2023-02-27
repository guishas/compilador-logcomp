//public class Parser {
//    public Tokenizer tokenizer;
//
//    public Parser() {
//
//    }
//
//    public Integer parseExpression() throws Exception {
//        int res = 0;
//
//        if (tokenizer.checkIfTokenIsNumber(tokenizer.getNext())) {
//            res += Integer.parseInt(tokenizer.getNext().getValue());
//
//            tokenizer.selectNext();
//            Token t = tokenizer.getNext();
//            while (t.getValue().equals(tokenizer.getAlphabet().TT_PLUS) ||
//                    t.getValue().equals(tokenizer.getAlphabet().TT_MINUS)) {
//                if (t.getValue().equals(tokenizer.getAlphabet().TT_PLUS)) {
//                    tokenizer.selectNext();
//                    t = tokenizer.getNext();
//                    if (t.getType().equals(tokenizer.getAlphabet().TT_INT)) {
//                        res += Integer.parseInt(t.getValue());
//                    } else {
//                        throw new Exception();
//                    }
//                }
//
//                if (t.getValue().equals(tokenizer.getAlphabet().TT_MINUS)) {
//                    tokenizer.selectNext();
//                    t = tokenizer.getNext();
//                    if (t.getType().equals(tokenizer.getAlphabet().TT_INT)) {
//                        res -= Integer.parseInt(t.getValue());
//                    } else {
//                        throw new Exception();
//                    }
//                }
//
//                tokenizer.selectNext();
//                t = tokenizer.getNext();
//            }
//
//            return res;
//        }
//
//        throw new Exception();
//    }
//
//    public void run(String sourceCode) throws Exception {
//        this.tokenizer = new Tokenizer(sourceCode);
//        int total = 0;
//
//        while (tokenizer.getCurrentChar() != '"') {
//            tokenizer.selectNext();
//            total = parseExpression();
//        }
//
//        System.out.println(total);
//    }
//}
