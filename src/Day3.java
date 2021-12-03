import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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


    public static void runTwo(){
        ArrayList<String> values = getValues();
        if(values == null) return;

        ArrayList<String> ogRating = new ArrayList<>(values);
        ArrayList<String> co2Rating = new ArrayList<>(values);

        for(int i = 0; i < ogRating.get(0).length(); i++){
            if(ogRating.size() == 1) break;

            //Find most common value in that row
            int ones = 0;
            for(int j = 0; j < ogRating.size(); j++)
                if(ogRating.get(j).charAt(i) == '1') ones++;


            boolean moreOnes = ones >= (ogRating.size() - ones); //If there are more (or equal) ones than zeros

            //Pop all values that don't have that value
            for(int j = 0; j < ogRating.size(); j++){
                if((moreOnes && ogRating.get(j).charAt(i) == '0') || (!moreOnes && ogRating.get(j).charAt(i) == '1')){
                    ogRating.remove(j);
                    j--;
                }
            }
        }

        for(int i = 0; i < co2Rating.get(0).length(); i++){
            if(co2Rating.size() == 1) break;

            //Find most common value in that row
            int ones = 0;
            for(int j = 0; j < co2Rating.size(); j++)
                if(co2Rating.get(j).charAt(i) == '1') ones++;


            boolean moreOnes = ones >= (co2Rating.size() - ones); //If there are more ones than zeros

            //Pop all values that don't have that value
            for(int j = 0; j < co2Rating.size(); j++){
                if((moreOnes && co2Rating.get(j).charAt(i) == '1') || (!moreOnes && co2Rating.get(j).charAt(i) == '0')){
                    co2Rating.remove(j);
                    j--;
                }
            }
        }

        System.out.println(ogRating);
        System.out.println(co2Rating);
        System.out.println("The result of multiplying the OG and CO2 rating is " + (Integer.parseInt(ogRating.get(0), 2) * Integer.parseInt(co2Rating.get(0), 2)));
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
