import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part1 implements Runnable{
    String input;
    String path = "Day9\\src\\rope.txt";
    String[] lines;
    public Part1() {
        getInputFromFile();
    }
    public int grid[][];
int row;
int column;
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

        int gridSize = 0;
        for (int i = 0; i < lines.length; i++) {
            gridSize += Integer.parseInt(lines[i].split(" ")[1]);
        }
        grid = new int[gridSize][gridSize];
        int[][] visited = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = 0;
            }
        }

        grid[0][0] = 1; // head
        grid[0][0] += 2; // tail

        for (String line :
                lines) {
            String[] instruction = line.split(" ");
            String direction = instruction[0];
            int distance = Integer.parseInt(instruction[1]);



        }

        
        
    }

    public void move(String dir, int dis, int row1, int column1){
        int positionHead = grid[row1][column1];
        if(positionHead >= 3){ // head is on the tail
            grid[row][column] -= 1;
            getDirection(dir, dis);
            // Now the new Position is updated;
            grid[row][column] += 1;
            moveTail(dir);
            grid[row1][column1] -= 2;
            grid[row][column] += 2;

        }
    }

    public void getDirection(String dir, int dis){
        if(dir.equals("U")){
            row += dis;
        } else if (dir.equals("D")) {
            row -= dis;
        } else if (dir.equals("L")) {
            column -= dis;
        } else if (dir.equals("R")) {
            column += dis;
        }
        }


    public void moveTail(String dir) {
        if(dir.equals("U")){
            row = row + 1;
        } else if (dir.equals("D")) {
            row = row - 1;
        } else if (dir.equals("L")) {
            column = column + 1;
        } else if (dir.equals("R")) {
            column = column - 1;
        }
    }


}