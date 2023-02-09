import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compilador {
    public static void main(String[] args) throws Exception {

        StringBuilder numberString = new StringBuilder();
        boolean isSum = false;
        boolean isFirst = true;

        checkErrors(args);

        int total = getFirstNumber(args);

        for (String word : args[0].replace("'", "").strip().split("")) {

            if (word.equals("+")) {
                if (isFirst) {
                    isSum = true;
                    isFirst = false;
                } else {
                    if (isSum) {
                        total += Integer.parseInt(numberString.toString());
                    } else {
                        total -= Integer.parseInt(numberString.toString());
                    }

                    numberString = new StringBuilder();
                    isSum = true;
                }
            } else if (word.equals("-")) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    if (isSum) {
                        total += Integer.parseInt(numberString.toString());
                    } else {
                        total -= Integer.parseInt(numberString.toString());
                    }

                    numberString = new StringBuilder();
                    isSum = false;
                }
            } else {
                if (!isFirst) {
                    numberString.append(word);
                }
            }
        }

        if (isSum) {
            total += Integer.parseInt(numberString.toString());
        } else {
            total -= Integer.parseInt(numberString.toString());
        }

        System.out.println(total);
    }

    private static void checkErrors(String[] args) throws Exception {
        String[] math = args[0].replace("'", "").split("");
        Pattern pNumber = Pattern.compile("^[0-9]$");
        Pattern pSymbol = Pattern.compile("[+-]");

        if (args.length != 1) {
            throw new Exception();
        }

        if (math[0].equals("+") || math[0].equals("-") || math[math.length-1].equals("+") || math[math.length-1].equals("-")) {
            throw new Exception();
        }

        for (int i = 1; i < math.length - 1; i++) {
            Matcher mLeft = pNumber.matcher(math[i-1]);
            Matcher mRight = pNumber.matcher(math[i+1]);
            if (mLeft.matches() && mRight.matches() && math[i].equals(" ")) {
                throw new Exception();
            }
        }

        Matcher mSymbol = pSymbol.matcher(args[0]);
        if (!mSymbol.find()) {
            throw new Exception();
        }
    }

    private static int getFirstNumber(String[] string) {
        StringBuilder number = new StringBuilder();

        for (String word : string[0].replace("'", "").strip().split("")) {
            if (word.equals("+") || word.equals("-")) {
                return Integer.parseInt(number.toString());
            }

            number.append(word);
        }

        return 0;
    }
}
