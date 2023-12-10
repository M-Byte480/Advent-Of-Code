public class Part2 extends Part1 implements Runnable {

    public Part2() {
        super();
    }


    @Override
    public void run() {
        Part2 aoc = new Part2();

        lines = aoc.getInput().split("\n");

    }
}