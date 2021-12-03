import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Day3 {

    public static void runOne(){
        ArrayList<String> values = getValues();
        if(values == null) return;

        int[] occurrences = new int[values.get(0).length()]; //Counts the occurrences of the number 1 in each row
        for(int i = 0; i < values.size(); i++){
            for(int j = 0; j < values.get(0).length(); j++){
                if(values.get(i).charAt(j) == '1') occurrences[j]++;
            }
        }

        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        for(int i = 0; i < occurrences.length; i++){
            if(occurrences[i] > (values.size() - occurrences[i])){ //If there are more 1s than 0s in a row
                gammaRate.append('1');
                epsilonRate.append('0');
            }
            else{
                gammaRate.append('0');
                epsilonRate.append('1');
            }
        }

        System.out.println("The result of multiplying the Gamma and Epsilon rates is " + (Integer.parseInt(gammaRate.toString(), 2) * Integer.parseInt(epsilonRate.toString(), 2)));
    }

    private static ArrayList<String> getValues(){
        try{
            ArrayList<String> binaryMeasurements = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("resources/Day3Input.txt"));

            String currentLine;
            while((currentLine = br.readLine()) != null)
                binaryMeasurements.add(currentLine);

            return binaryMeasurements;

        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
