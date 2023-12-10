import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;

public class Part1 {
    String input;
    String path = "Day4\\src\\Day2.txt";
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

        String[][] sections = new String[lines.length][4];

        for (int i = 0; i < lines.length; i++) {
            String[] temp = lines[i].split(",");
            sections[i][0] = temp[0].split("-")[0];
            sections[i][1] = temp[0].split("-")[1];
            sections[i][2] = temp[1].split("-")[0];
            sections[i][3] = temp[1].split("-")[1];
        }
        int total = 0;
        for (String[] section:
            sections ) {
           if(overLaps(section))total++;
        }

        return total;
    }

    public boolean overLaps(String[] selection){
        int firstElfStart = Integer.parseInt(selection[0]);
        int firstElfFinish = Integer.parseInt(selection[1]);

        int secondElfStart = Integer.parseInt(selection[2]);
        int secondElfFinish = Integer.parseInt(selection[3]);


        if (firstElfStart <= secondElfStart && secondElfFinish <= firstElfFinish){
            return true;
        }

        return secondElfStart <= firstElfStart && firstElfFinish <=  secondElfFinish;
    }
}