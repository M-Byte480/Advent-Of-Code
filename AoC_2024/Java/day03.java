import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day03 extends AbstractAoc{
    private String input;

    public day03() {
        super();
        super.loadInput("day03");
        processInput();
    }

    @Override
    public void processInput() {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(this.fileReader);
        try {
            while (bf.ready()) {
                sb.append(bf.readLine());
            }

            this.input = sb.toString();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String solution01() {
        Pattern regex = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Matcher matcher = regex.matcher(this.input);

        int sum = 0;
        while (matcher.find()) {
            sum += multiply(matcher.group());
        }
        return sum + "";
    }

    public String solution02() {
        String input = this.input;
        int sum = 0;
        Pattern regex = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        int pointerToDont = input.indexOf("don't");
        int pointerToDo = 0;
        while(true){
            String stringToFilter = input.substring(pointerToDo, pointerToDont);
            Matcher matcher = regex.matcher(stringToFilter);
            while (matcher.find()) {
                sum += multiply(matcher.group());
            }
            pointerToDo = input.indexOf("do(", pointerToDont);
            pointerToDont = input.indexOf("don't", pointerToDo + 1);
            if(pointerToDont == -1 ){
                stringToFilter = input.substring(pointerToDo);
                matcher = regex.matcher(stringToFilter);
                while (matcher.find()) {
                    sum += multiply(matcher.group());
                }
                break;
            } else if(pointerToDo == -1){
                break;
            }

        }

        return sum + "";
    }

    private int multiply(String expression){
        int indexOfBracket = expression.indexOf('(');
        int indexOfComma = expression.indexOf(',');
        String firstNumber = expression.substring(indexOfBracket + 1, indexOfComma);
        String secondNumber = expression.substring(indexOfComma + 1, expression.length() - 1);

        return Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber);
    }


}
