import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day6 {
    private static final int DAYS = 80;

    public static void runOne(){
        ArrayList<Integer> values = getValues();
        if(values == null) return;

        for(int d = 0; d < DAYS; d++){

            int newLanternfish = 0;
            for(int l = 0; l < values.size(); l++){
                if(values.get(l) == 0){
                    values.set(l, 6);
                    newLanternfish++;
                }
                else values.set(l, values.get(l) - 1);
            }

            for(int n = 0; n < newLanternfish; n++)
                values.add(8);
        }

        System.out.println("After " + DAYS + " days, there would be " + values.size() + " lanternfish");
    }

    public static ArrayList<Integer> getValues(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("resources/Day6Input.txt"));
            String valuesString = br.readLine();
            String[] valuesArray = valuesString.split(",");

            ArrayList<Integer> values = new ArrayList<>();

            for(String n: valuesArray)
                values.add(Integer.parseInt(n));

            br.close();
            return values;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
