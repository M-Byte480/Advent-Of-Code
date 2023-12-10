import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part1 {
    String input;
    String path = "Day2\\src\\rockPaperScissors.txt";
    ArrayList<Integer> elves;

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
    }

    public String getInput() {
        return input;
    }

    public int run() {
        Part1 aoc = new Part1();
        String inputs = aoc.getInput();

        String[] lines = inputs.split("\n");

//        int chunkSize = 1;
//        String[][] rockPaperScissors =
//                (String[][])
//                IntStream
//                .iterate(0, i -> i + chunkSize)
//                .limit((int) Math.ceil((double) lines.length / chunkSize))
//                .mapToObj(j -> (Arrays.copyOfRange(lines, j, Math.min(lines.length, j + chunkSize))))
//                .toArray(String[][]::new);
//
//        System.out.println(Arrays.deepToString(rockPaperScissors));


        String[][] rps = new String[lines.length][2];

        int i = 0;
        for (String line : lines) {
            rps[i] = line.split(" ");
            i++;
        }

        /*
        A = Rock
        B = Paper
        C = Scissors

        X = Rock = 1
        Y = Paper = 2
        Z = Scissors = 3

        Win = +6
        Loss = +0
        Draw = +3
        // */
        int myScore = 0;
        for (String[] match : rps) {
            myScore += result(match[0], match[1]);
        }

        return myScore;
    }

    String[] myValues = {"rock", "paper", "scissors"};

    public int result(String elf, String me){
        String elfValue = elf.toLowerCase();
        String myValue = me.toLowerCase();
        int status = 0; // -1 loss, 0 draw, 1 win
        int score = 0;

        if(elfValue.equals("a")){
            elf = "rock";
        } else if (elfValue.equals("b")) {
            elf = "paper";
        }else {
            elf = "scissors";
        }

        if(myValue.equals("x")){
            me = "rock";
        } else if (myValue.equals("y")) {
            me = "paper";
        }else {
            me = "scissors";
        }

        // Not Draw
        if(!elf.equals(me)){

            if(elf.equals("rock")){
                if(me.equals("paper")){
                    status = 1; // Win
                }else{
                    status = -1;
                }
            } else if (elf.equals("paper")) {
                if (me.equals("scissors")){
                    status = 1; // win
                }else{
                    status = -1; // loss
                }
            }else{
                if(me.equals("rock")){
                    status = 1;
                }else{
                    status = -1;
                }
            }
        }

        // Handles the score of your choice
        for (String val : myValues) {
            score++;
            if (val.equals(me)) break;
        }

        if(status == 0){
            score += 3;
        }
        else if (status == 1) {
            score += 6;
        }
        return score;
    }

}