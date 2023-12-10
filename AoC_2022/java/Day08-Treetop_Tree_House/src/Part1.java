import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Part1 implements Runnable{
    String input;
    String path = "Day8\\src\\forest.txt";
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

    @Override
    public void run() {
        Part1 aoc = new Part1();
        String inputs = aoc.getInput();

        lines  = inputs.split("\n");
        int[][] forest = new int[lines.length][lines[0].length()];
        for (int row = 0 ; row < forest.length ; row ++) {
            for (int i = 0; i < forest[0].length; i++) {
                forest[row][i] = Integer.parseInt(String.valueOf(lines[row].charAt(i)));
            }
        }
        int sum = treesThatCantSee(forest);
        System.out.println("Solution to part 1: " + (sum + (forest.length * 2) + (forest[0].length - 2) * 2));

    }

    public int treesThatCantSee(int[][] forest){
        int sumOfTreesNotSeen = 0;
        for (int row = 1; row < forest.length - 1; row++) {
            for (int column = 1; column < forest[0].length - 1; column++) {
//                System.out.println(row + " : " + column);
                sumOfTreesNotSeen += (canBeSeen(row, column, forest) ? 1 : 0);
//                System.out.println(canBeSeen(row, column, forest));
            }
        }

        return sumOfTreesNotSeen;
    }

    // We know the tree itself is not higher than itself. We just need to know if the trees anywhere before can be seen.

    public boolean canBeSeen(int row, int column, int[][] forest){
        // Vertical Check
        int heightOfTree = forest[row][column];

        // Assume we can see all trees
        boolean upperBound= true;
        boolean lowerBound = true;
        boolean leftBound = true;
        boolean rightBound = true;

        // This checks if the tree in a vertical set, can be seen in the forest of said state
        for (int i = 0; i < forest.length; i++) {
            // Upper Bound
            if(forest[i][column] >= heightOfTree && i < row){
                i = row;
                upperBound = false;
            }else if(i == row && upperBound){
                // We went from the top down and we can see this tree
                return true;
            } else if (i > row) {
                // Lower half
                if (forest[i][column] >= heightOfTree){
                    lowerBound = false;
                }
            }
        }

        // If one of them can be seen, then we just return true to be seen
        if(upperBound || lowerBound){
            return true;
        }

        // If it cant be seen vertically

        // Horizontal Check
        for (int i = 0; i < forest[0].length; i++) {
            if(i < column && forest[row][i] >= heightOfTree){
                i = column;
                leftBound = false;

            }else if(i == column && leftBound){
                return true;
            } else if (i > column) {
                if(forest[row][i] >= heightOfTree){
                    rightBound = false;
                }
            }
        }

        if(leftBound || rightBound){
            return true;
        }

        return false;
    }

}