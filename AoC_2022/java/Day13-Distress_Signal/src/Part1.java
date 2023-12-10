import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Part1 implements Runnable {
    String input;
    String path = "Day13-Distress_Signal\\src\\signal.txt";
    String pathTest = "Day13-Distress_Signal\\src\\signalTest.txt";
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
        distressSignal();
    }

    public void distressSignal(){
        String[] pairs = input.split("\n\n");
        String[][] set =  Arrays.stream(pairs)
                                        .map(pair -> pair.split("\n"))
                                        .toArray(String[][]::new);

        for (String[] pair : set) {
            Stack<String> left = new Stack<>();
            Stack<String> right = new Stack<>();

            for (int i = 0; i < pair[0].length(); i++) {
                left.push(String.valueOf(pair[0].charAt(i)));
            }
            left =  reverseStack(left);

            for (int i = 0; i < pair[1].length(); i++) {
                right.push(String.valueOf(pair[1].charAt(i)));
            }
            right =  reverseStack(right);


        }
    }

    public static <E> Stack<E> reverseStack(Stack <E> stack ){
        Stack<E> reversed = new Stack<>();
        while(!stack.isEmpty()){
            reversed.push(stack.pop());
        }
        return reversed;
    }
}
