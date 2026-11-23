import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    
    // Checks if the number is suitable for that particular row, column and box.
    public static boolean isNumberSuitable(int[][] board, int row, int column, int number) {
        if (board[row][column] == 0) {
            for(int i=0; i<9;i++){
                if(board[row][i]==number) return false;
            }
            for(int i=0; i<9; i++){
                if(board[i][column]==number) return false;
            }
            int boxStartingRow= row/3*3;
            int boxStartingColumn = column/3*3;
            for(int tryRow=0; tryRow<3;tryRow++){
                for(int tryColumn=0; tryColumn<3; tryColumn++){
                    if(board[tryRow+boxStartingRow][tryColumn+boxStartingColumn] == number) return false;
                }
            }
        } else {
            for (int i=0;i<9;i++) {
                if (board[row][column] == board[i][column] && row!=i) return false;
            }
            for (int j=0;j<9;j++){
                if(board[row][column] == board[row][j] && column !=j) return false;
            }

        }
        return true;
    }
    
    
    // Solves the board using a backtracking alghoritm.
    public static boolean backtrackSolve(int[][] board){ 
        for (int row = 0 ; row < board.length ; row++){
            for (int column = 0 ; column < board[0].length ; column++){
                if (board[row][column] == 0){
                    int[] numbers = {1,2,3,4,5,6,7,8,9};
                    //shuffle(numbers); //backtracks from a random number (1-9)
                    for (int i = 0 ; i < numbers.length ; i++){
                        if (isNumberSuitable(board, row, column, numbers[i])){
                            board[row][column] = numbers[i];
                            if (backtrackSolve(board)){
                                return true;
                            }
                            board[row][column] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


    // Will use Fisher-Yates shuffle

    public static final Random RD = new Random();

    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int index = RD.nextInt(i + 1);
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    
    /*
    public static int solutionCount(int[][] board){
        int[][] comparisionBoard = copyBoard(board);
        if(backtrackSolve(comparisionBoard)) solutionCount++;
        return solutionCount;

    }
    */


    // Measures how long does it take to solve on average, can measure different alghoritms with a few tweaks. 
    public static double measureSolutionTime(int[][] board){
        int[][] dummyBoard = Board.copy(board);
        long startTime = System.nanoTime();
        backtrackSolve(dummyBoard);
        long endTime = System.nanoTime();
        double solutionTime = (endTime - startTime) / 1000000000.0;
        return solutionTime;        
    }

    // Needs to be in a while(boardNotSolved) loop.
    public static void solveWithUser(int[][] board, boolean tellMistake){
        int[][] dummyBoard = Board.copy(board);
        if (tellMistake) {
            System.out.println("Solving the board hold on for a few seconds...");
            backtrackSolve(dummyBoard);
            System.out.println("Solved the board.");
        }
        System.out.println("Enter your guess with this format: number, row, column");
        String guess = sc.nextLine();
        int numberToBeFilled = Integer.valueOf(guess.split(" ")[0]);
        int rowToBeFilled = Integer.valueOf(guess.split(" ")[1]);
        int columnToBeFilled = Integer.valueOf(guess.split(" ")[2]);
        if (tellMistake) {
            if (Integer.valueOf(dummyBoard[rowToBeFilled][columnToBeFilled])==numberToBeFilled) {
                board[rowToBeFilled][columnToBeFilled] = numberToBeFilled;
                System.out.println("Correct.");
            } else {
                System.out.println("Not correct.");
            }            
        } else{
                if (isNumberSuitable(board, rowToBeFilled, columnToBeFilled, numberToBeFilled)) {
                    board[rowToBeFilled][columnToBeFilled] = numberToBeFilled;
                } else {
                    System.out.println("Number is not suitable.");
            }
        }
    }

    public static boolean isBoardSolved(int[][] board){
        for (int i=0;i<9;i++) {
            for (int j=0;j<9;j++) {
                if (board[i][j]==0) return false;
                if (!(isNumberSuitable(board, i, j, board[i][j]))) return false;
            }
        }
        return true;
    }

    // Credits to claude
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Concept
    public static void json(String action, double[] results){
        //import board
        //change solutionTime(i)
    }

    //Benchmark under construction
    public static void benchmark(int[][] board){
        double solutionTimeSum =0;
        double[] solutionTime = new double[100];
        System.out.println("Make sure: your computer is plugged in, you have no open applications, you are running this from your OS terminal. PRESS ENTER TO CONTINUE");
        // warm up? make sure: you just started your computer?
        sc.nextLine();
        for (int i =0;i<100 ; i++) {
            solutionTime[i] = measureSolutionTime(board);
            System.out.println("("+(i+1)+")"+" Solution time: "+solutionTime[i]);
            solutionTimeSum += solutionTime[i];
        }
        System.out.println("Average solution time: "+(solutionTimeSum/100));
    }

    public static void gameplay(){
        //System.out.println("How hard do you want your starting board to be? Easy(1), medium(2), hard(3).");
        int[][] board = Board.get("testBoard", 0);
        while (!isBoardSolved(board)){
            clearScreen();
            Board.print(board);
            solveWithUser(board, false);
            //backtrackSolve(board);
            //printBoard(board);
        }
        System.out.println("You won!");
    }

    public static void main(String[] args) {
       
        //gameplay();
        //benchmark(Board.get("testBoardHard", 0));
    }
}
