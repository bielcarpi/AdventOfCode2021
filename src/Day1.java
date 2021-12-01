import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1 {

    public static void runOne() throws IOException {
        int[] measurements = getMeasurements();
        int biggerThanLastOne = 0;
        for(int i = 0; i < measurements.length - 1; i++)
            if(measurements[i+1] > measurements[i]) biggerThanLastOne++;

        System.out.println("There are " + biggerThanLastOne + " measures bigger than last one.");
    }

    public static void runTwo() throws IOException {
        int[] measurements = getMeasurements();
        int biggerThanLastOne = 0;

        for(int i = 0; i < measurements.length - 3; i++){
            int first = measurements[i] + measurements[i + 1] + measurements[i + 2];
            int second = measurements[i + 1] + measurements[i + 2] + measurements[i + 3];

            if(second > first) biggerThanLastOne++;
        }

        System.out.println("There are " + biggerThanLastOne + " measures bigger than last one.");
    }


    private static int[] getMeasurements(){
        try{
            ArrayList<Integer> measurements = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("resources/Day1Input.txt"));

            String currentLine;
            while((currentLine = br.readLine()) != null)
                measurements.add(Integer.parseInt(currentLine));

            return measurements.stream().mapToInt(i -> i).toArray();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(0);
            return null;
        }


    }
}
