import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {

    static Scanner sc = new Scanner(System.in);


    public static void printBoard(int[][] board) {  // Prints the whole board, needs an update to print 16x16 boards. use "Math.sqrt()"
        System.out.println("============================");
        for (int i = 0; i < board.length; i++) {
            if(i%3==0) System.out.println("");
            for (int a = 0; a < board[i].length; a++) {
                if(a%3==0) System.out.print("| ");
                System.out.print(board[i][a] + " ");
                
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("============================");
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
                    shuffle(numbers); //backtracks from a random number (1-9)
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

    // Generates a board with first row being randomized.
    private static int[][] generate(){ 
        int[][] board = new int[9][9];
        for (int i = 0 ; i < board[0].length ; i++){
            board[0][i] = i + 1;
        } 
        shuffle(board[0]);// Shuffles the first row.
        backtrackSolve(board);
        return board;
    }

    // Will use Fisher-Yates shuffle
    private static void shuffle(int[] arr){
        Random rd = new Random();
        for (int i = arr.length - 1 ; i >= 0 ; i--){
            int index = rd.nextInt(i+1);
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
    public static void measureSolutionTime(int[][] board, int solveTimes){
        double solutionTimeSum=0;
        int[][] dummyBoard = copyBoard(board);

        for (int i=0;i<solveTimes ; i++) {
            dummyBoard = copyBoard(board);
            long startTime = System.nanoTime();
            backtrackSolve(dummyBoard);
            long endTime = System.nanoTime();
            double solutionTime = (endTime - startTime) / 1000000000.0;
            solutionTimeSum += solutionTime;
            System.out.println("("+(i+1)+")"+" Solution time for backtracking: " + solutionTime + " seconds");
        }

        System.out.println("Solved "+ solveTimes+" times, average time for a solution: "+ solutionTimeSum/solveTimes);
        
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
        printBoard(board);
        if (tellMistake) {
            System.out.println("Solving the board hold on for a few seconds...");
            backtrackSolve(dummyBoard);
            System.out.println("Solved the board.");
            printBoard(dummyBoard);
        }
        System.out.println("What number?");
        int numberToBeFilled = sc.nextInt();
        System.out.println("Which row?");
        int rowToBeFilled = sc.nextInt();
        System.out.println("Which column?");
        int columnToBeFilled = sc.nextInt();

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
        
        printBoard(board);

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

    public static void main(String[] args) {
        // int[satir][sutun]
        int[][] startingBoard = {
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
        

                 
        //System.out.println("How hard do you want your starting board to be? Easy(1), medium(2), hard(3).");
        //int hardnessBoard = sc.nextInt();
        //generate(hardnessBoard);
        
        //System.out.println("Would you prefer the game to tell you if you made a mistake? true/false");
        //boolean tellMistake = sc.nextBoolean();
        //solveWithUser(startingBoard, tellMistake);
        
        printBoard(startingBoard);
        System.out.println("Board solved? "+ isBoardSolved(startingBoard));
        backtrackSolve(startingBoard);
        printBoard(startingBoard);
        System.out.println("Board solved? "+ isBoardSolved(startingBoard));

        //printBoard(generate()); //This is a temporary action.
    }
}
