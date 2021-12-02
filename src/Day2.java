import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2{

    public static void runOne(){
        int[] values = getValues();
        System.out.println(values[0]);
        System.out.println(values[1]);
        System.out.println(values[0] * values[1]);
    }


    private static int[] getValues(){
        try{
            ArrayList<String> measurements = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("resources/Day2Input.txt"));

            String currentLine;
            while((currentLine = br.readLine()) != null)
                measurements.add(currentLine);

            String[] measurementsString = new String[measurements.size()];
            measurements.toArray(measurementsString);

            int totalForward = 0;
            int totalDepth = 0;
            for(String m: measurementsString){
                String[] s = m.split(" ");
                switch (s[0]) {
                    case "up" -> totalDepth -= Integer.parseInt(s[1]);
                    case "down" -> totalDepth += Integer.parseInt(s[1]);
                    case "forward" -> totalForward += Integer.parseInt(s[1]);
                }
            }

            return new int[]{totalForward, totalDepth};
        }catch(IOException e){
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }
}