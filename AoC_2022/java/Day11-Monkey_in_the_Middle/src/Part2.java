import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class Part2 extends Part1 implements Runnable {

    public Part2(boolean t) {
        super(t);
    }


    @Override
    public void run() {
        ArrayList<Monkey> monkeys = populateMonkeys();
        int roundNum = 10_000 ;
        System.out.println("Part 2: " + rounds(roundNum , monkeys));
    }

    @Override
    public long rounds(int rounds, ArrayList<Monkey> monkeys) {
        // Modulus Arithmetic Theorem!!!
        long mod = 1;
        for (Monkey m :
                monkeys) {
            mod *= Long.parseLong(m.getTest().replaceAll("[^0-9]", ""));
        }
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

                    item = item % mod;
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


}