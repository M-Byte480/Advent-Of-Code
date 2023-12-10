import javax.print.CancelablePrintJob;

public class Main {
    public static void main(String[] args) {
        Part1 p1 = new Part1();
//        Thread part1Solution = new Thread(p1);
//        part1Solution.start();
        p1.run();
        Part2 p2 = new Part2();
//        Thread part2Solution = new Thread(p2);
//        part2Solution.start();
    }
}
