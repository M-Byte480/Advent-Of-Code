import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part1 {
    String input;
    String path = "Day6\\src\\stream.txt";
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

        for (int i = 0; i < inputs.length(); i++) {
            String setOf4 = inputs.substring(i, i + 4);
            char[] chars = new char[4];

            for (int j = 0; j < 4; j++) {
                chars[j] = setOf4.charAt(j);
            }
            boolean founds = false;
            for (char character : chars) {
                if (4 - setOf4.replaceAll(String.valueOf(character), "").length() > 1) {
                    founds = true;
                }
            }

            if(!founds){
               return i + 4;
            }
        }



        return 0;
    }

}