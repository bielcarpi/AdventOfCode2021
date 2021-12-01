import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1 {

    private static ArrayList<Integer> measurements;

    public static void runOne() throws IOException {
        measurements = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("resources/Day1Input.txt"));

        String currentLine;
        while((currentLine = br.readLine()) != null)
            measurements.add(Integer.parseInt(currentLine));

        int biggerThanLastOne = 0;
        for(int i = 0; i < measurements.size() - 1; i++)
            if(measurements.get(i+1) > measurements.get(i)) biggerThanLastOne++;

        System.out.println("There are " + biggerThanLastOne + " measures bigger than last one.");
    }
}
