import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Part1 implements Runnable{
    String input;
    String path = "Day10\\src\\assembly.txt";
    String pathTest = "Day10\\src\\assemblyTest.txt";
    String[] lines;
    public Part1(boolean t) {
        if (t){
            getInputFromFile(path);
        }else{
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
        lines  = input.split("\n");
    }


    @Override
    public void run() {
        System.out.println(solution());
    }

    public int solution(){
        String[] lines = input.split("\n");

        int total = 0;
        int xRegister = 1;

        ArrayList<String> linesUpdated = new ArrayList<>(Arrays.stream(lines).toList());
        int element = 0;
        boolean running = true;

        while (running) {
            if(!linesUpdated.get(element).equals("noop")){
                linesUpdated.add(element, "noop");
                element++;
            }

            element++;

            if(element == linesUpdated.size()){
                running = false;
            }

        }

        int number;

        for (int i = 0; i < linesUpdated.size(); i ++) {
            String[] instruction = linesUpdated.get(i).split(" ");

            if(instruction.length == 1){ // noop
                number = 0;
            }else {
                number = Integer.parseInt(instruction[1]);
            }


            if(i % 40 == 19 && i < 220){
                total += xRegister * (i + 1);
            }
            xRegister += number;


        }
        return total;
    }
}