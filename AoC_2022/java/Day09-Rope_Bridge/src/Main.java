public class Main {
    public static void main(String[] args) {
        Part1 p1 = new Part1();
        Thread part1 = new Thread(p1);

        part1.start();

        Part2 p2 = new Part2();
        Thread part2 = new Thread(p2);
        part2.start();
    }
}
