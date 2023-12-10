public class Part2 extends Part1 {

    public int run() {
        Part1 p1 = new Part1();
        lines  = input.split("\n");


        String[][] sections = new String[lines.length][4];

        for (int i = 0; i < lines.length; i++) {
            String[] temp = lines[i].split(",");
            sections[i][0] = temp[0].split("-")[0];
            sections[i][1] = temp[0].split("-")[1];
            sections[i][2] = temp[1].split("-")[0];
            sections[i][3] = temp[1].split("-")[1];
        }
        int total = 0;
        for (String[] section:
                sections ) {
            if(overLaps(section))total++;
        }

        return lines.length - total;
    }

    public boolean overLaps(String[] selection){
        int firstElfStart = Integer.parseInt(selection[0]);
        int firstElfFinish = Integer.parseInt(selection[1]);

        int secondElfStart = Integer.parseInt(selection[2]);
        int secondElfFinish = Integer.parseInt(selection[3]);


        return firstElfFinish < secondElfStart || secondElfFinish < firstElfStart;
    }

}
