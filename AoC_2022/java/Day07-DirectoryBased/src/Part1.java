import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Part1 implements Runnable {
    String input;
    String path = "Day7-DirectoryBased\\src\\directories.txt";
    String[] lines;
    int sum = 0;
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
        String[] dirs = p1.getInput().split("\n");

        Stack<String> stack = new Stack<>();
        HashMap<String, Integer> directorySizes = new HashMap<String, Integer>();

        int globalIdentifier = 0;
        for (String line : lines) {
            if (line.equals("$ cd ..")) {
                int totalByteSize = 0;
                // Calculates the bytes stored in the directory.
                String popped = null;
                while (!stack.peek().contains("$ cd ")) {
                    popped = stack.pop();
                    if (popped.contains("dir ")) {
                        totalByteSize += directorySizes.get(popped.substring("dir ".length()));
                    } else {
                        totalByteSize += Integer.parseInt(popped.split(" ")[0]);
                    }
                }
                popped = stack.pop();

                if(totalByteSize <= 100_000) {
                    sum += totalByteSize;
                    directorySizes.replace(popped.substring("$ cd ".length()), totalByteSize);
                }
                continue;

            } else if (line.equals("$ ls")) {
                // We ignore
                continue;
            }else if (line.contains("$ cd ") && !line.contains("..")) {
                if(directorySizes.containsKey(line.substring("$ cd ".length()))){
                    directorySizes.put(line.substring("$ cd ".length()) + globalIdentifier, 0);
                    globalIdentifier++;
                }
                directorySizes.put(line.substring("$ cd ".length()), 0);
            }
            stack.push(line);
        }
        for (String key : (directorySizes.keySet())){
            System.out.println(key +" = "+directorySizes.get(key));
        }
        System.out.println(sum);
    }

//    private void setDirs(String[] dirs) {
//        Part1.dirs = dirs;
//    }

//    public static ArrayList<Directory> getDirectory(String name, int position) {
//
//        ArrayList<String> contents = new ArrayList<>();
//        for (int i = position; i < dirs.length; i++) {
//            if (dirs[i].equals("$ cd " + name)) {
//                i += 2;
//                position = i;
//                while (!dirs[i].contains("$ cd")) {
//                    contents.add(dirs[i]);
//                    i++;
//                }
//                break;
//            }
//        }
//        ArrayList<Directory> directoryList = new ArrayList<>();
//        ArrayList<File> fileContents = new ArrayList<>();
//
//        for (String content : contents) {
//            if (content.contains("dir")) {
//                directoryList.add(new Directory(content, getDirectory(content.substring("dir ".length()), position), getFiles(content.substring("dir ".length()), position)));
//            } else {
//                fileContents.add(new File(content));
//            }
//        }
//        return directoryList;
//    }

//    public static ArrayList<File> getFiles(String name, int position) {
//        ArrayList<String> contents = new ArrayList<>();
//
//        for (int i = position; i < dirs.length; i++) {
//            if (dirs[i].equals("$ cd " + name)) {
//                i += 2;
//                while (!dirs[i].contains("$ cd")) {
//                    contents.add(dirs[i]);
//                    i++;
//                }
//                break;
//            }
//        }
//
//        ArrayList<File> fileContents = new ArrayList<>();
//
//        for (String content : contents) {
//            if (!(content.contains("dir"))) {
//                fileContents.add(new File(content));
//            }
//        }
//        return fileContents;
//    }


//    public static int numberOfDirectories(Directory root, int number) {
//        int sum = 0;
//        if (root.getDirectories().size() == 0) {
//            return (root.getDirectorySize() <= number ? sum + root.getDirectorySize() : sum);
//        } else {
//            for (Directory dir : root.getDirectories()) {
//                sum += numberOfDirectories(dir, number);
//            }
//            sum += (root.getDirectorySize() <= number ? root.getDirectorySize() : 0);
//        }
//
//        return sum;
//    }
}