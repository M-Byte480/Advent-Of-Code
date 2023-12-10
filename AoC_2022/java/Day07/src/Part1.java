import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
public class Part1 implements Runnable {
    String input;
    String path = "Day7\\src\\directories.txt";
    String[] lines;
    private final int SIZELIMIT = 100_000;
    public Part1() {
        getInputFromFile();
    }

    static String[] dirs;

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
        lines = input.split("\n");
    }

    public String getInput() {
        return input;
    }

    @Override
    public void run() {
        Part1 p1 = new Part1();
        String[] lines = p1.getInput().split("\n");

        ArrayList<Directory> directories = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        Directory current = null;
        boolean contains = false;
        int countLess = 0;
        for (String line : lines) {
            stack.push(line);
            if(line.contains("$")){
                if(line.contains("cd") && !line.contains("..")){
//                    contains = false;
//                    for (Directory dir : directories) {
//                        if (dir.getName().equals(line.substring("$ cd ".length()))) {
//                            current = dir;
//                            contains = true;
//                        }
//                    }
//                    if(!contains){
                        current = new Directory(line.substring("$ cd ".length()), current);
                        directories.add(current);
//                    }



                }else if(line.contains("cd ..")){
                    // We must start popping until we hit the parent directory call
                    while(true){
                        String top = stack.pop();

                        if(top.contains(current.getName())){
                            current.setSize(current.getDirectorySize());
                            current = current.getParent();
                            break;
                        }
                    }

                }
            }else{
                if(line.contains("dir")){
                    Directory child = (new Directory(line.split(" ")[1], current));
                    current.addChild(child);
                    directories.add(child);
                }else{
                    String[] args = line.split(" ");
                    File f = new File(args[1], Integer.parseInt(args[0]));
                    current.addFile(f);
                }
            }
        }

        for (int i = directories.size() - 1; i >= 0; i --) {
            Directory directory = directories.get(i);
            if(directory.getSize() < SIZELIMIT){
                countLess += directory.getSize();
            }
        }

        System.out.println(countLess);
    }

}