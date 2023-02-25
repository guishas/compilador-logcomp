public class Compilador {
    public static void main(String[] args) throws Exception {
        Parser p = new Parser();
        p.run(args[0]);
    }
}
