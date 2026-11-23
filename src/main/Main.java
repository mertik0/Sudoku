import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    
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
        Solve.backtrack(dummyBoard);
        long endTime = System.nanoTime();
        double solutionTime = (endTime - startTime) / 1000000000.0;
        return solutionTime;        
    }

    // Needs to be in a while(boardNotSolved) loop.
    public static void solveWithUser(int[][] board, boolean tellMistake){
        int[][] dummyBoard = Board.copy(board);
        if (tellMistake) {
            System.out.println("Solving the board hold on for a few seconds...");
            Solve.backtrack(dummyBoard);
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
                if (Solve.isNumberSuitable(board, rowToBeFilled, columnToBeFilled, numberToBeFilled)) {
                    board[rowToBeFilled][columnToBeFilled] = numberToBeFilled;
                } else {
                    System.out.println("Number is not suitable.");
            }
        }
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
        while (!Solve.isSolved(board)){
            clearScreen();
            Board.print(board);
            solveWithUser(board, false);
            //backtrackSolve(board);
            //printBoard(board);
        }
        clearScreen();
        Board.print(board);
        System.out.println("You won!");
    }

    public static void main(String[] args) {
       
        //gameplay();
        //benchmark(Board.get("testBoardHard", 0));
    }
}
