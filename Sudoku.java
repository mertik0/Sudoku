import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {

    static Scanner sc = new Scanner(System.in);


    public static void printBoard(int[][] board) {  // Prints the whole board, needs an update to print 16x16 boards. use "Math.sqrt()"
        for (int i = 0; i < board.length; i++) {
            if(i%3==0 || i==9) System.out.println("");
            for (int j = 0; j < board[i].length; j++) {
                if(j%3==0) System.out.print("| ");
                System.out.print(board[i][j] + " ");
                
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
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
    private static boolean backtrackSolve(int[][] board){ 
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

    // gets board: testBoard/randomized/imported
    private static int[][] getBoard(String rule, int hardness){
        // board[row][column]
        int[][] board = new int[9][9];
        if (rule.equals("testBoard")) {
            board = new int[][] {
                {0, 0, 7, 6, 5, 4, 3, 2, 1},
                {2, 4, 6, 1, 7, 3, 9, 8, 5},
                {3, 5, 1, 9, 2, 8, 7, 4, 6},
                {1, 2, 8, 5, 3, 7, 6, 9, 4},
                {6, 3, 4, 8, 9, 2, 1, 5, 7},
                {7, 9, 5, 4, 6, 1, 8, 3, 2},
                {5, 1, 9, 2, 8, 6, 4, 7, 3},
                {4, 7, 2, 3, 1, 9, 5, 6, 8},
                {8, 6, 3, 7, 4, 5, 2, 1, 9}
            };
             
         } else if (rule.equals("random")) {
            for (int i = 0 ; i < board[0].length ; i++){
                board[0][i] = i + 1;
            } 
            shuffle(board[0]);// Shuffles the first row.
            backtrackSolve(board);
        } else if (rule.equals("import")){
            //import code here
            board = new int[9][9]; 
        } else if (rule.equals("testBoardHard")){
            board = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 8, 5},
                {0, 0, 1, 0, 2, 0, 0, 0, 0},
                {0, 0, 0, 5, 0, 7, 0, 0, 0},
                {0, 0, 4, 0, 0, 0, 1, 0, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 0},
                {5, 0, 0, 0, 0, 0, 0, 7, 3},
                {0, 0, 2, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 4, 0, 0, 0, 9}
            };
        }
        return board;
    }

    // Will use Fisher-Yates shuffle

    private static final Random RD = new Random();

    private static void shuffle(int[] arr) {
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

    
    // May be used to copy boards and compare different alghoritms on them.
    public static int[][] copyBoard(int[][] board){
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i=0; i<board.length ; i++) {
            for (int j=0 ;j<board[0].length ;j++ ) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }


    // Measures how long does it take to solve on average, can measure different alghoritms with a few tweaks. 
    public static double measureSolutionTime(int[][] board){
        int[][] dummyBoard = copyBoard(board);
        long startTime = System.nanoTime();
        backtrackSolve(dummyBoard);
        long endTime = System.nanoTime();
        double solutionTime = (endTime - startTime) / 1000000000.0;
        return solutionTime;        
    }

    // Turns a 81char string to a 9x9 board. Needs an update, fix possible outOfBounds exception by filling the remaining of the board with 0, try other stringToBoard mechanisms. Make it possible to generate 16x16 boards.
    public static int[][] stringToBoard(String input){
        int[][] newBoard = new int[9][9];
        for (int i=0;i<9;i++) {
             for (int j=0;j<9 ;j++ ) {
                newBoard[i][j] = Integer.valueOf(input.split("")[(i*9+j)]);
             }
         } 
         return newBoard;
    }


    // Needs to be in a while(boardNotSolved) loop.
    public static void solveWithUser(int[][] board, boolean tellMistake){
        int[][] dummyBoard = copyBoard(board);
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
        int[][] board = getBoard("testBoard", 0);
        while (!isBoardSolved(board)){
            clearScreen();
            printBoard(board);
            solveWithUser(board, false);
            //backtrackSolve(board);
            //printBoard(board);
        }
        System.out.println("You won!");
    }

    public static void main(String[] args) {
       
       //gameplay();
        benchmark(getBoard("testBoardHard", 0));
    }
}
