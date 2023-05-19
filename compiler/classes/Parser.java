package compiler.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public Tokenizer tokenizer;

    public Parser(String sourceCode) {
        this.tokenizer = new Tokenizer(sourceCode);
    }

    public Node parseBlock() throws Exception {
        List<Node> children = new ArrayList<>();
        while (!tokenizer.getNext().getType().equals("EOF")) {
            children.add(parseStatement());
        }

        Node[] nodes = new Node[children.size()];
        for (int i = 0; i < children.size(); i++) {
            nodes[i] = children.get(i);
        }

        return new Block("BLOCK", nodes);
    }

    public Node parseStatement() throws Exception {
        if (tokenizer.getNext().getType().equals("TT_ENDLINE")) {
            tokenizer.selectNext();
            return new NoOp("NoOp", new Node[]{});
        } else if (tokenizer.getNext().getType().equals("TT_IDENTIFIER")) {
            Token iden = tokenizer.getNext();
            tokenizer.selectNext();
            if (tokenizer.getNext().getType().equals("TT_EQUALS")) {
                tokenizer.selectNext();
                return new Assignment("ASSIGNMENT", new Node[]{new Identifier(iden.getValue(), new Node[]{}), parseRelExpression()});
            } else if (tokenizer.getNext().getType().equals("TT_TYPE_ASSIGN")) {
                tokenizer.selectNext();
                if (tokenizer.getNext().getType().equals("TT_TYPE")) {
                    String type = tokenizer.getNext().getValue();
                    tokenizer.selectNext();
                    if (tokenizer.getNext().getType().equals("TT_EQUALS")) {
                        tokenizer.selectNext();
                        return new VarDec(type, new Node[]{new Identifier(iden.getValue(), new Node[]{}), parseRelExpression()});
                    } else {
                        if (type.equals("int")) {
                            return new VarDec(type, new Node[]{new Identifier(iden.getValue(), new Node[]{}), new IntVal("0", new Node[]{})});
                        } else if (type.equals("string")) {
                            return new VarDec(type, new Node[]{new Identifier(iden.getValue(), new Node[]{}), new StrVal("", new Node[]{})});
                        }
                    }
                } else {
                    throw new Exception();
                }
            } else if (tokenizer.getNext().getType().equals("TT_LEFT_PAR")) {
                tokenizer.selectNext();
                ArrayList<Node> params = new ArrayList<>();
                if (tokenizer.getNext().getType().equals("TT_RIGHT_PAR")) {
                    return new FuncCall(iden.getValue(), new Node[]{});
                } else {
                    params.add(parseRelExpression());
                    while (tokenizer.getNext().getType().equals("TT_COMMA")) {
                        tokenizer.selectNext();
                        params.add(parseRelExpression());
                    }

                    if (tokenizer.getNext().getType().equals("TT_RIGHT_PAR")) {
                        tokenizer.selectNext();

                        Node[] args = new Node[params.size()];
                        for (int i = 0; i < params.size(); i++) {
                            args[i] = params.get(i);
                        }

                        return new FuncCall(iden.getValue(), args);
                    } else {
                        throw new Exception("Unexpected token: " + tokenizer.getNext().getType());
                    }
                }
            } else {
                throw new Exception();
            }
        } else if (tokenizer.getNext().getType().equals("TT_RETURN")) {
            tokenizer.selectNext();
            return new Return("TT_RETURN", new Node[]{parseRelExpression()});
        } else if (tokenizer.getNext().getType().equals("TT_PRINTLN")) {
            tokenizer.selectNext();
            if (tokenizer.getNext().getType().equals("TT_LEFT_PAR")) {
                tokenizer.selectNext();
                Node n = new Print("PRINTLN", new Node[]{parseRelExpression()});

                if (tokenizer.getNext().getType().equals("TT_RIGHT_PAR")) {
                    tokenizer.selectNext();
                    return n;
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } else if (tokenizer.getNext().getType().equals("TT_WHILE")) {
            tokenizer.selectNext();

            Node c = parseRelExpression();

            if (tokenizer.getNext().getType().equals("TT_ENDLINE")) {
                tokenizer.selectNext();
                List<Node> children = new ArrayList<>();
                while (!tokenizer.getNext().getType().equals("TT_END")) {
                    children.add(parseStatement());
                }

                Node[] nodes = new Node[children.size()];
                for (int i = 0; i < children.size(); i++) {
                    nodes[i] = children.get(i);
                }

                Block b = new Block("BLOCK", nodes);

                if (tokenizer.getNext().getType().equals("TT_END")) {
                    tokenizer.selectNext();
                    return new While("WHILE", new Node[]{c, b});
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } else if (tokenizer.getNext().getType().equals("TT_IF")) {
            tokenizer.selectNext();

            Node c = parseRelExpression();

            if (tokenizer.getNext().getType().equals("TT_ENDLINE")) {
                tokenizer.selectNext();
                List<Node> children = new ArrayList<>();
                while (!(tokenizer.getNext().getType().equals("TT_ELSE") || tokenizer.getNext().getType().equals("TT_END"))) {
                    children.add(parseStatement());
                }

                Node[] nodes = new Node[children.size()];
                for (int i = 0; i < children.size(); i++) {
                    nodes[i] = children.get(i);
                }

                Block ifBlock = new Block("BLOCK", nodes);

                if (tokenizer.getNext().getType().equals("TT_ELSE")) {
                    tokenizer.selectNext();

                    if (tokenizer.getNext().getType().equals("TT_ENDLINE")) {
                        tokenizer.selectNext();
                        List<Node> elseChildren = new ArrayList<>();
                        while (!tokenizer.getNext().getType().equals("TT_END")) {
                            elseChildren.add(parseStatement());
                        }

                        Node[] elseNodes = new Node[elseChildren.size()];
                        for (int i = 0; i < elseChildren.size(); i++) {
                            elseNodes[i] = elseChildren.get(i);
                        }

                        Block elseBlock = new Block("BLOCK", elseNodes);

                        if (tokenizer.getNext().getType().equals("TT_END")) {
                            tokenizer.selectNext();
                            return new If("IF", new Node[]{c, ifBlock, elseBlock});
                        } else {
                            throw new Exception();
                        }
                    } else {
                        throw new Exception();
                    }
                } else if (tokenizer.getNext().getType().equals("TT_END")) {
                    tokenizer.selectNext();
                    return new If("IF", new Node[]{c, ifBlock});
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } else if (tokenizer.getNext().getType().equals("TT_FUNCTION")) {
            tokenizer.selectNext();
            if (tokenizer.getNext().getType().equals("TT_IDENTIFIER")) {
                Node identifier = new Identifier(tokenizer.getNext().getValue(), new Node[]{});
                tokenizer.selectNext();
                if (tokenizer.getNext().getType().equals("TT_LEFT_PAR")) {
                    tokenizer.selectNext();
                    if (tokenizer.getNext().getType().equals("TT_RIGHT_PAR")) {
                        tokenizer.selectNext();
                        if (tokenizer.getNext().getType().equals("TT_TYPE_ASSIGN")) {
                            tokenizer.selectNext();
                            if (tokenizer.getNext().getType().equals("TT_TYPE")) {
                                String type = tokenizer.getNext().getValue();
                                tokenizer.selectNext();
                                if (tokenizer.getNext().getType().equals("TT_ENDLINE")) {
                                    tokenizer.selectNext();

                                    List<Node> funcContent = new ArrayList<>();
                                    while (!tokenizer.getNext().getType().equals("TT_END")) {
                                        funcContent.add(parseStatement());
                                    }

                                    Node[] nodes = new Node[funcContent.size()];

                                    for (int i = 0; i < funcContent.size(); i++) {
                                        nodes[i] = funcContent.get(i);
                                    }

                                    Node block = new Block("BLOCK", nodes);

                                    tokenizer.selectNext();
                                    return new FuncDec(type, new Node[]{identifier, block});
                                } else {
                                    throw new Exception("Unexpected token: " + tokenizer.getNext().getType());
                                }
                            } else {
                                throw new Exception("Unexpected token: " + tokenizer.getNext().getType());
                            }
                        } else {
                            throw new Exception("Unexpected token: " + tokenizer.getNext().getType());
                        }
                    } else if (tokenizer.getNext().getType().equals("TT_IDENTIFIER")) {
                        ArrayList<Node> params = new ArrayList<>();

                        params.add(parseStatement());
                        while (tokenizer.getNext().getType().equals("TT_COMMA")) {
                            tokenizer.selectNext();
                            params.add(parseStatement());
                        }

                        Node[] args = new Node[params.size()];
                        for (int i = 0; i < params.size(); i++) {
                            args[i] = params.get(i);
                        }

                        if (tokenizer.getNext().getType().equals("TT_RIGHT_PAR")) {
                            tokenizer.selectNext();
                            if (tokenizer.getNext().getType().equals("TT_TYPE_ASSIGN")) {
                                tokenizer.selectNext();
                                if (tokenizer.getNext().getType().equals("TT_TYPE")) {
                                    String type = tokenizer.getNext().getValue();
                                    tokenizer.selectNext();
                                    if (tokenizer.getNext().getType().equals("TT_ENDLINE")) {
                                        tokenizer.selectNext();

                                        List<Node> funcContent = new ArrayList<>();
                                        while (!tokenizer.getNext().getType().equals("TT_END")) {
                                            funcContent.add(parseStatement());
                                        }

                                        Node[] nodes = new Node[funcContent.size()];
                                        for (int i = 0; i < funcContent.size(); i++) {
                                            nodes[i] = funcContent.get(i);
                                        }

                                        Node block = new Block("BLOCK", nodes);

                                        tokenizer.selectNext();

                                        Node[] funcDec = new Node[args.length + 2];
                                        funcDec[0] = identifier;
                                        for (int i = 1; i <= args.length; i++) {
                                            funcDec[i] = args[i-1];
                                        }
                                        funcDec[args.length+1] = block;

                                        return new FuncDec(type, funcDec);
                                    } else {
                                        throw new Exception("Unexpected token: " + tokenizer.getNext().getType());
                                    }
                                } else {
                                    throw new Exception("Unexpected token: " + tokenizer.getNext().getType());
                                }
                            } else {
                                throw new Exception("Unexpected token: " + tokenizer.getNext().getType());
                            }
                        } else {
                            throw new Exception("Unexpected token: " + tokenizer.getNext().getType());
                        }
                    } else {
                        throw new Exception("Unexpected token: " + tokenizer.getNext().getType());
                    }
                } else {
                    throw new Exception("Unexpected token: " + tokenizer.getNext().getType());
                }
            } else {
                throw new Exception("Unexpected token: " + tokenizer.getNext().getType());
            }
        } else {
            throw new Exception();
        }

        return null;
    }

    public Node parseRelExpression() throws Exception {
        Node result = parseExpression();
        while (Arrays.asList(new String[]{"TT_IS_EQUAL_TO", "TT_GREATER", "TT_LESS"}).contains(tokenizer.getNext().getType())) {
            if (tokenizer.getNext().getType().equals("TT_IS_EQUAL_TO")) {
                tokenizer.selectNext();
                result = new BinOp("==", new Node[]{result, parseExpression()});
            } else if (tokenizer.getNext().getType().equals("TT_GREATER")) {
                tokenizer.selectNext();
                result = new BinOp(">", new Node[]{result, parseExpression()});
            } else {
                tokenizer.selectNext();
                result = new BinOp("<", new Node[]{result, parseExpression()});
            }
        }

        return result;
    }

    public Node parseExpression() throws Exception {
        Node result = parseTerm();
        while (Arrays.asList(new String[]{"TT_PLUS", "TT_MINUS", "TT_OR", "TT_CONCAT"}).contains(tokenizer.getNext().getType())) {
            if (tokenizer.getNext().getType().equals("TT_PLUS")) {
                tokenizer.selectNext();
                result = new BinOp("+", new Node[]{result, parseTerm()});
            } else if (tokenizer.getNext().getType().equals("TT_MINUS")) {
                tokenizer.selectNext();
                result = new BinOp("-", new Node[]{result, parseTerm()});
            } else if (tokenizer.getNext().getType().equals("TT_OR")) {
                tokenizer.selectNext();
                result = new BinOp("||", new Node[]{result, parseTerm()});
            } else if (tokenizer.getNext().getType().equals("TT_CONCAT")) {
                tokenizer.selectNext();
                result = new BinOp(".", new Node[]{result, parseTerm()});
            } else {
                throw new Exception();
            }
        }

        return result;
    }

    public Node parseTerm() throws Exception {
        Node result = parseFactor();
        while (Arrays.asList(new String[]{"TT_MULT", "TT_DIV", "TT_AND"}).contains(tokenizer.getNext().getType())) {
            if (tokenizer.getNext().getType().equals("TT_MULT")) {
                tokenizer.selectNext();
                result = new BinOp("*", new Node[]{result, parseFactor()});
            } else if (tokenizer.getNext().getType().equals("TT_DIV")) {
                tokenizer.selectNext();
                result = new BinOp("/", new Node[]{result, parseFactor()});
            } else {
                tokenizer.selectNext();
                result = new BinOp("&&", new Node[]{result, parseFactor()});
            }
        }

        return result;
    }

    public Node parseFactor() throws Exception {
        Node ret;
        if (tokenizer.getNext().getType().equals("TT_INT")) {
            ret = new IntVal(tokenizer.getNext().getValue(), new Node[]{});
            tokenizer.selectNext();
            return ret;
        } else if (tokenizer.getNext().getType().equals("TT_STRING")) {
            ret = new StrVal(tokenizer.getNext().getValue(), new Node[]{});
            tokenizer.selectNext();
            return ret;
        } else if (tokenizer.getNext().getType().equals("TT_IDENTIFIER")) {
            String identifier = tokenizer.getNext().getValue();
            tokenizer.selectNext();
            if (tokenizer.getNext().getType().equals("TT_LEFT_PAR")) {
                tokenizer.selectNext();
                ArrayList<Node> params = new ArrayList<>();
                if (tokenizer.getNext().getType().equals("TT_RIGHT_PAR")) {
                    ret = new FuncCall(identifier, new Node[]{});
                    return ret;
                } else {
                    params.add(parseRelExpression());
                    while (tokenizer.getNext().getType().equals("TT_COMMA")) {
                        tokenizer.selectNext();
                        params.add(parseRelExpression());
                    }

                    if (tokenizer.getNext().getType().equals("TT_RIGHT_PAR")) {
                        tokenizer.selectNext();

                        Node[] args = new Node[params.size()];
                        for (int i = 0; i < params.size(); i++) {
                            args[i] = params.get(i);
                        }

                        ret = new FuncCall(identifier, args);
                        return ret;
                    } else {
                        throw new Exception("Unexpected token: " + tokenizer.getNext().getType());
                    }
                }
            } else {
                ret = new Identifier(identifier, new Node[]{});
                return ret;
            }
        } else if (tokenizer.getNext().getType().equals("TT_PLUS")) {
            tokenizer.selectNext();
            return new UnOp("+", new Node[]{parseFactor()});
        } else if (tokenizer.getNext().getType().equals("TT_MINUS")) {
            tokenizer.selectNext();
            return new UnOp("-", new Node[]{parseFactor()});
        } else if (tokenizer.getNext().getType().equals("TT_NOT")) {
            tokenizer.selectNext();
            return new UnOp("!", new Node[]{parseFactor()});
        } else if (tokenizer.getNext().getType().equals("TT_LEFT_PAR")) {
            tokenizer.selectNext();
            ret = parseRelExpression();

            if (tokenizer.getNext().getType().equals("TT_RIGHT_PAR")) {
                tokenizer.selectNext();
                return ret;
            } else {
                throw new Exception();
            }
        } else if (tokenizer.getNext().getType().equals("TT_READLINE")) {
            tokenizer.selectNext();
            if (tokenizer.getNext().getType().equals("TT_LEFT_PAR")) {
                tokenizer.selectNext();

                ret = new Read("TT_READLINE", new Node[]{});

                if (tokenizer.getNext().getType().equals("TT_RIGHT_PAR")) {
                    tokenizer.selectNext();
                    return ret;
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    public void run(SymbolTable symbolTable) throws Exception {
        Node tree = null;

        tokenizer.selectNext();
        while (!tokenizer.getNext().getType().equals("EOF")) {
            tree = parseBlock();
            if (tokenizer.getNext().getType().equals("TT_RIGHT_PAR")) {
                throw new Exception();
            }
        }

        tree.Evaluate(symbolTable);
    }
}
