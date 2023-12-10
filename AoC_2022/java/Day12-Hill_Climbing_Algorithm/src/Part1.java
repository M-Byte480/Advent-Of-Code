import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Part1 implements Runnable {
    String input;
    String path = "Day12-Hill_Climbing_Algorithm\\src\\hill.txt";
    String pathTest = "Day12-Hill_Climbing_Algorithm\\src\\hillTest.txt";
    String[] lines;

    public Part1(boolean t) {
        if (t) {
            getInputFromFile(path);
        } else {
            getInputFromFile(pathTest);
        }
    }

    public void getInputFromFile(String path) {
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
        lines = input.split("\n");
    }

    @Override
    public void run() {
        breathFirstSearch();
    }

    public void breathFirstSearch() {
        String[] input = this.input.split("\n");
        String[][] map = new String[input.length][input[0].length()];

        int sr = 0;
        int sc = 0;
        int er = 0;
        int ec = 0;

        for (int r = 0; r < input.length; r++) {
            for (int c = 0; c < input[0].length(); c++) {
                String v = String.valueOf(input[r].charAt(c));
                if (v.equals("S")) {
                    v = "a";
                    sr = r;
                    sc = c;
                }
                if (v.equals("E")) {
                    v = "z";
                    er = r;
                    ec = c;
                }
                map[r][c] = v;
            }
        }

        LinkedList<Integer[]> queue = new LinkedList<>();
        ArrayList<Integer[]> visited = new ArrayList<>();

        queue.add(new Integer[]{0, sr, sc});
        visited.add(new Integer[]{sr, sc});


        // RemoveFirst removes First Element
        // RemoveLast removes Last Element
        // PollFirst from the First Element and returns Null if it doesnt exist
        // PollLast from the Last element and Returns null if it doesnt exist

        int nr = 0;
        int nc = 0;
        while (true) {
            Integer[] vals = queue.pollFirst();
            int d = vals[0];
            int r = vals[1];
            int c = vals[2];

            label:
            for (int[] next : new int[][]{{r + 1, c}, {r - 1, c}, {r, c - 1}, {r, c + 1}}) {
                nr = next[0];
                nc = next[1];

                if(nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length){
                    continue;
                }

                for (int i = 0; i < visited.size(); i++) {
                    if(visited.get(i)[0].equals(nr) && visited.get(i)[1].equals(nc)){
                        continue label;
                    }
                }

                if((map[nr][nc].charAt(0)) - (map[r][c].charAt(0)) > 1){
                    continue;
                }

                if(nr == er && nc == ec){
                    System.out.println("Part 1 : " + (d+1));
                    return;
                }

                visited.add(new Integer[]{nr, nc});
                queue.add(new Integer[]{d + 1, nr, nc});
            }
        }
    }
}
