import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utility {

    public static MyArrayList<String> loadBadWords(String filename) throws FileNotFoundException{
        MyArrayList<String> lst = new MyArrayList<>();

        Scanner scan = new Scanner(new File(filename));
        while (scan.hasNextLine()) {
            String lin = scan.nextLine();
            lst.add(lin);
        }
        scan.close();
        return lst;
    }


    public static boolean containsBadWord(String input, String filename){
        MyArrayList<String> lst;
    try {

            lst = Utility.loadBadWords(filename);
        }
        catch (FileNotFoundException e) {
            lst = new MyArrayList<>();
        }

        for(String i : lst) {
            if (input.toLowerCase().contains(i.toLowerCase())) {
                return true;
            }
        }
        return false;
    }


    public static boolean isEven(int number){
        if (number == 0){
            return true;
        }
        if (number == 1){
            return false;
        }
         return isEven(number - 2);
    }

    public static boolean isOdd(int number){
        if (number == 0){
            return false;
        }
        if (number == 1){
            return true;
        }
        return isOdd(number - 2);
    }

    public static int countInColumn(int[][] board, int col, int piece){
        return countInColumnHelper(board, col, piece, 0);
    }
    public static int countInColumnHelper(int[][] board, int col, int piece, int row){
        int count = 0;
        if (row >= board.length ){
            return count;
        }
        if (board[row][col] == piece){
        count++;}
        return count + countInColumnHelper(board, col, piece, row + 1);
    }














    }

