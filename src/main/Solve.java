public class Solve {

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
    public static boolean backtrack(int[][] board){ 
        for (int row = 0 ; row < board.length ; row++){
            for (int column = 0 ; column < board[0].length ; column++){
                if (board[row][column] == 0){
                    int[] numbers = {1,2,3,4,5,6,7,8,9};
                    //shuffle(numbers); //backtracks from a random number (1-9)
                    for (int i = 0 ; i < numbers.length ; i++){
                        if (isNumberSuitable(board, row, column, numbers[i])){
                            board[row][column] = numbers[i];
                            if (backtrack(board)){
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

    public static boolean isSolved(int[][] board){
        for (int i=0;i<9;i++) {
            for (int j=0;j<9;j++) {
                if (board[i][j]==0) return false;
                if (!(isNumberSuitable(board, i, j, board[i][j]))) return false;
            }
        }
        return true;
    }


}