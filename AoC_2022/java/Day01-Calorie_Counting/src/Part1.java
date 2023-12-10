import java.util.*;
import java.io.*;

public class Part1 {
    String input;
    String path = "C:\\Programming\\Java\\AdventOfCode\\AdventOfCode\\Day1\\src\\Calories.txt";
    ArrayList<Integer> elves;
    public Part1(){
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
    }

    public String getInput() {
        return input;
    }

    public int run() {
        Part1 aoc = new Part1();
        String inputs = aoc.getInput();
        String[] lines = inputs.split("\n");
//        System.out.println(Arrays.toString(lines));
        ArrayList<Integer> elves = new ArrayList<>();
        int total = 0;
//*
        for (String line :  lines) {
            if(line.equals("")){
                elves.add(total);
                total = 0;
                continue;
            }
            total += Integer.parseInt(line);
        }

        this.elves = elves;

        int max = 0;
        for (Integer elf :
                elves) {
            if(elf > max){
                max = elf;
            }
        }

        return max;
        // */
    }
}