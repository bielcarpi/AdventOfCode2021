import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day6 {
    private static final int DAYS = 80;
    private static final int DAYS_TWO = 256;

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


    public static void runTwo(){
        ArrayList<Integer> initialValues = getValues();
        if(initialValues == null) return;

        //We'll store state in a hashmap
        //The first int, the num of days that the lanternfish needs to create another lanternfish
        //The second int, the num of lanternfish on that state
        HashMap<Integer, Long> values = new HashMap<>();

        //Fill the hashmap with initial values
        for(int i = 0; i < 10; i++)
            values.put(i, 0L);
        for(int value: initialValues)
            values.put(value, values.get(value) + 1);


        for(int d = 0; d < DAYS_TWO; d++){
            long dayZeroQuantity = values.get(0);
            for(int i = 0; i < 9; i++)
                values.put(i, values.get(i+1));

            values.put(6, values.get(6) + dayZeroQuantity); //The ones with day zero, now day 5
            values.put(8, dayZeroQuantity); //Add new lanternfish (each day-zero lanternfish creates a new one)
        }


        long totalLanternfish = 0;
        for(int i: values.keySet())
            totalLanternfish += values.get(i);

        System.out.println("After " + DAYS_TWO + " days, there would be " + totalLanternfish + " lanternfish");
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
