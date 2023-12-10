import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Part1 implements Runnable{
    String input;
    String path = "Day11-Monkey_in_the_Middle\\src\\monkeys.txt";
    String pathTest = "Day11-Monkey_in_the_Middle\\src\\monkeysTest.txt";
    String[] lines;
    ArrayList<Monkey> monkeys = new ArrayList<>();
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
        ArrayList<Monkey> monkeys = populateMonkeys();

        System.out.println("Part 1: " + rounds( 20 , monkeys));
    }

    public long rounds(int rounds, ArrayList<Monkey> monkeys) {
        // For each Round
        for (int i = 0; i < rounds; i++) {

            // For each monkey
            for (int m = 0; m < monkeys.size(); m++) {
                Monkey monkey = monkeys.get(m);
                String test = monkey.getTest();
                String trueResult = monkey.getTrueStatement();
                String falseResult = monkey.getFalseStatement();
                String operation = monkey.getOperation();

                long divisibleBy = Long.parseLong(test.replaceAll("[^0-9]", ""));
                int trueThrow = Integer.parseInt(trueResult.replaceAll("[^0-9]", ""));
                int falseThrow = Integer.parseInt(falseResult.replaceAll("[^0-9]", ""));

                String[] equation = operation.split("  Operation: ");
                // 0 is empty 1 is the equation
                String[] instruction = equation[0].substring("new = ".length()).split(" ");

                boolean add = !instruction[1].equals("*");

                // For each Item of the monkey
                for (int n = 0 ; n < monkey.getItems().size(); ) {
                    long item = monkey.getItems().get(n);
                    long numberToPass = 0;

                    // Remove the item from monkey, and increase his inspect count
                    monkey.getItems().remove(0);
                    monkey.increaseInspect();

                    // What numbers to give to Operation
                    if (instruction[2].equals("old")) {
                        numberToPass = item;
                    }else{
                        numberToPass = Long.parseLong(instruction[2]);
                    }


                    // Handles the Operation
                    if(add){
                        item = item + (numberToPass);
                    }else {
                        item = item * (numberToPass);
                    }

                    item = item / 3;
                    // Handles the pass
                    if(item % (divisibleBy) == 0){
                        monkeys.get(trueThrow).getItems().add(item);
                    }else{
                        monkeys.get(falseThrow).getItems().add(item);
                    }

                }
            }

        }

        long[] largest = new long[2];
        for (Monkey m : monkeys) {
            if(m.getInspect() > largest[0]) {
                if(m.getInspect() > largest[1]){
                    largest[0] = largest[1];
                    largest[1] = m.getInspect();
                }else{
                    largest[0] = m.getInspect();
                }
            }
        }
        return (largest[0]) * (largest[1]);
    }

    public ArrayList<Monkey> populateMonkeys(){
        String[] monkeyDesc = input.split("\n\n");

        for (String m : monkeyDesc) {
            String[] descriptions = m.split("\n");
            // Handles Name
            int number = Integer.parseInt(m.split("\n")[0].substring("Monkey ".length(), "Monkey ".length()+1));
            Monkey monkey = new Monkey(number);

            // Handles Instruction
                String[] startingItems = descriptions[1].substring("  Starting items: ".length()).split(", ");

                // Handles items
                for (int j = 0; j < startingItems.length; j++) {
                    monkey.getItems().add(Long.parseLong(startingItems[j]));
                }

                // Handle Operation
                monkey.setOperation(descriptions[2].substring("  Operation: ".length()));

                // Test
                monkey.setTest(descriptions[3].split("Test: ")[1]);

                // True
                monkey.setTrueStatement(descriptions[4].split("true: ")[1]);

                // False
                monkey.setFalseStatement(descriptions[5].split("false: ")[1]);


            monkeys.add(monkey);
        }

        return monkeys;
    }


}