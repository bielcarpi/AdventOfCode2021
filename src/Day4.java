import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day4 {

    private static final int BOARD_ROW_LENGTH = 5;

    public static void runOne(){
        int[] values = getValues();
        if(values == null) return;

        ArrayList<Tuple<Integer, Boolean>[][]> boards = getBoards();
        if(boards == null) return;

        int winnerIndex = -1; //Index of the winner in the arraylist
        int lastValue = -1; //Last value that the winner put

        mainloop:
        for(int i = 0; i < values.length; i++){

            for(int j = 0; j < boards.size(); j++){

                for(int k = 0; k < BOARD_ROW_LENGTH; k++){
                    for(int z = 0; z < BOARD_ROW_LENGTH; z++) {
                        if (boards.get(j)[k][z].x == values[i]) {
                            boards.get(j)[k][z].y = true;

                            //Now that the board is updated, check if some row or column is completed
                            for(int r = 0; r < BOARD_ROW_LENGTH; r++){
                                boolean rowCompleted = true;
                                boolean columnCompleted = true;

                                for(int c = 0; c < BOARD_ROW_LENGTH; c++){
                                    if (boards.get(j)[r][c].y == false) columnCompleted = false;
                                    if (boards.get(j)[c][r].y == false) rowCompleted = false;
                                }

                                if (rowCompleted || columnCompleted) {
                                    winnerIndex = j;
                                    lastValue = values[i];
                                    break mainloop;
                                }
                            }

                        }
                    }

                }
            }
        }

        int sum = 0;
        for(int r = 0; r < BOARD_ROW_LENGTH; r++){
            for(int c = 0; c < BOARD_ROW_LENGTH; c++){
                if(boards.get(winnerIndex)[r][c].y == false) sum += boards.get(winnerIndex)[r][c].x;
            }
        }

        System.out.println("The result is " + (sum * lastValue));
    }


    public static void runTwo(){
        int[] values = getValues();
        if(values == null) return;

        ArrayList<Tuple<Integer, Boolean>[][]> boards = getBoards();
        if(boards == null) return;

        int winnerIndex = -1; //Index of the winner in the arraylist
        int lastValue = -1; //Last value that the winner put
        ArrayList<Integer> winnersIndex = new ArrayList<>(); //Store the index of the winners

        mainloop:
        for(int i = 0; i < values.length; i++){

            for(int j = 0; j < boards.size(); j++){

                boardloop:
                for(int k = 0; k < BOARD_ROW_LENGTH; k++){
                    for(int z = 0; z < BOARD_ROW_LENGTH; z++) {
                        if (boards.get(j)[k][z].x == values[i]) {
                            boards.get(j)[k][z].y = true;

                            //Now that the board is updated, check if some row or column is completed
                            for(int r = 0; r < BOARD_ROW_LENGTH; r++){
                                boolean rowCompleted = true;
                                boolean columnCompleted = true;

                                for(int c = 0; c < BOARD_ROW_LENGTH; c++){
                                    if (boards.get(j)[r][c].y == false) columnCompleted = false;
                                    if (boards.get(j)[c][r].y == false) rowCompleted = false;
                                }

                                if (rowCompleted || columnCompleted) {
                                    for(int alreadyWinner: winnersIndex)
                                        if(j == alreadyWinner) break boardloop;

                                    winnersIndex.add(j);
                                    if(winnersIndex.size() == boards.size()){
                                        winnerIndex = j;
                                        lastValue = values[i];
                                        break mainloop;
                                    }
                                }
                            }

                        }
                    }

                }
            }
        }

        int sum = 0;
        for(int r = 0; r < BOARD_ROW_LENGTH; r++){
            for(int c = 0; c < BOARD_ROW_LENGTH; c++){
                if(boards.get(winnerIndex)[r][c].y == false) sum += boards.get(winnerIndex)[r][c].x;
            }
        }

        System.out.println("The result is " + (sum * lastValue));
    }


    private static int[] getValues(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("resources/Day4Input.txt"));
            String valuesString = br.readLine();
            String[] valuesArray = valuesString.split(",");
            int[] valuesInt = new int[valuesArray.length];

            for(int i = 0; i < valuesArray.length; i++)
                valuesInt[i] = Integer.parseInt(valuesArray[i]);

            return valuesInt;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<Tuple<Integer, Boolean>[][]> getBoards(){
        try{
            ArrayList<Tuple<Integer, Boolean>[][]> boards = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader("resources/Day4Input.txt"));

            //Move cursor to third line
            br.readLine();
            br.readLine();

            String currentLine;
            String[] currentLineSplit;
            while((currentLine = br.readLine()) != null){
                @SuppressWarnings("unchecked")
                Tuple<Integer, Boolean>[][] board = new Tuple[BOARD_ROW_LENGTH][BOARD_ROW_LENGTH];

                for(int i = 0; i < BOARD_ROW_LENGTH; i++){
                    currentLineSplit = currentLine.split("[ ]{1,}");
                    for(int j = 0, k = 0; j < currentLineSplit.length; j++){
                        if(currentLineSplit[j].equals("")){ //The split regex not working properly? We'll solve it with this
                            k++;
                            continue;
                        }
                        board[i][j - k] = new Tuple<>(Integer.parseInt(currentLineSplit[j]), false);
                    }

                    currentLine = br.readLine();
                }

                boards.add(board);
            }

            return boards;

        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    static class Tuple<X, Y> {
        public X x;
        public Y y;
        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
}
