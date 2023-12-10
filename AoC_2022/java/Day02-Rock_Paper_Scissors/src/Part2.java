import java.util.Collections;

public class Part2 extends Part1 {

    public int run() {
        Part1 aoc = new Part1();
        String inputs = aoc.getInput();

        String[] lines = inputs.split("\n");

        String[][] rps = new String[lines.length][2];
        int i = 0;
        for (String line : lines) {
            rps[i] = line.split(" ");
            i++;
        }


        int myScore = 0;
        for (String[] match : rps) {
            myScore += result(match[0], match[1]);
        }

        return myScore;
    }

    String[] myValues = {"x", "y", "z"};
    String[] myObjects = {"rock", "paper", "scissors"};

    /*
        A = Rock
        B = Paper
        C = Scissors

        X = lose
        Y = draw
        Z = win

        Win = +6
        Loss = +0
        Draw = +3
        // */

    public int result(String elf, String me){
        elf = elf.toLowerCase();
        me = me.toLowerCase();

        String object = null; // -1 loss, 0 draw, 1 win
        int score = 0;

        // Handles the score of your choice
        for (String val : myValues) {
            if (val.equals(me)) break;
            score += 3;
        }



        if(elf.equals("a")){
            elf = "rock";
        } else if (elf.equals("b")) {
            elf = "paper";
        }else {
            elf = "scissors";
        }

        if(me.equals("x")){
            me = "lose";
        } else if (me.equals("y")) {
            me = "draw";
        }else {
            me = "win";
        }

        // Not Draw
        if(!me.equals("draw")){

            if(elf.equals("rock")){
                if(me.equals("win")){
                    object = "paper"; // Win
                }else{
                    object = "scissors";
                }
            } else if (elf.equals("paper")) {
                if (me.equals("win")){
                    object = "scissors"; // win
                }else{
                    object = "rock"; // loss
                }
            }else{
                if(me.equals("win")){
                    object = "rock";
                }else{
                    object = "paper";
                }
            }
        }else{
            object = elf.toLowerCase();
        }

        for (String obj : myObjects) {
            score++;
            if (obj.equals(object)) break;
        }

        return score;
    }
}
