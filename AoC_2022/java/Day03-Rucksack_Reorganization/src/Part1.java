import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part1 {
    String input;
    String path = "Day3\\src\\SackInput.txt";
    ArrayList<Integer> elves;
    String[] lines;
    public Part1() {
        getInputFromFile();
    }


    public void getInputFromFile() {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String sCurrentLine;
            while ((sCurrentLine = reader.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.input = contentBuilder.toString();
        lines  = input.split("\n");
    }

    public String getInput() {
        return input;
    }

    public int run() {
        Part1 aoc = new Part1();
        String inputs = aoc.getInput();

        lines  = inputs.split("\n");

        int total = 0;
        char c = 'a';

        for (String line : lines) {
           c = getOverlap(line);
           if (97 <= c && c <= 122) c -= 96;
           else if(65 <= c && c <= 90) c -= 38;
           total += c;
        }
        return total;
    }

    public char getOverlap(String backpack){
        int half = backpack.length()/2;

        char[] firstHalf = backpack.substring(0, half).toCharArray();
        String secondHalf = backpack.substring(half);

        char temp = 96;
        for (char c : firstHalf) {
            if (secondHalf.contains(String.valueOf(c)))temp = c;
        }
        return temp;
    }
}