# Milestones

## November 17, 2026

* Github repository "sudoku-solver" was created, with sudokuCoz.java.

* sudokuCoz.java had the following methods:
  1. yazdir(int[][] tahta), printed a 9x9 board in style.
  2. uygunMu(int[][] tahta, int satir, int sutun, int sayi), checked if the sayi(number) variable can be placed on satir(row), sutun(column) and 3x3 box in tahta(board).
  3. coz(int[][] tahta), solved 2D array tahta using a recursive backtrack algorithm with the help of uygunMu() method.
  4. main(String[] args), the execution point of the program that calls the methods above and it had a 9x9 sudoku board built in.
   

## November 18, 2026

* The 9x9 sudoku board inside main method was switched to a harder board, making it longer to solve using the recursive backtrack algorithm.

* The Program can now measure how long it takes to solve the board.
* .
* .
* .

## November 19, 2026

## November 20, 2026

## November 21, 2026

## November 22, 2026

* Turned the double solutionTime variable to double[] SolutionTime with length 100. This will enable the program to store 100 different solution times in it's memory.

## November 23, 2026

* Created docs/milestones.md to keep track of progress and practice documentation.

* Splitted Sudoku.java into Main.java (where the most of the program still are) and Board.java (has Sudoku.java's board methods).

* Removed magic numbers from Board.java in order to achieve the goal of no magic numbers in the program.

* Methods of the new Board.java :
  1. shuffle().
  2. printBoard() -> print().
  3. getBoard() -> get().
  4. stringToBoard() -> convert().
  5. copyBoard() -> copy().


* Created Solve.java from Main.java 

* Methods of the new Solve.java
  1. isNumberSuitable().
  2. backtrackSolve() -> backtrack().
  3. isBoardSolved() -> isSolved().
  
* All source code is now in src/main/ .

## November 24, 2026

* Added 2 important methods for Solve.java:
  1. possibleNumberCount(int[][] board, int row, int column) for adding suitable numbers for that particular cell using isNumberSuitable() into a ArrayList cellPossibleNumberAmount and returns cellPossibleNumberAmount.size();
  2. checkAllPossibleNumbers(int[][] board) for counting every suitable number for every empty cell on the board and turning the counted values into a board.
