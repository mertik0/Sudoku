public class Board {

	// Prints the whole board, can print 4x4 / 9x9 / 16x16 ......
	public static void print(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if(i % Math.sqrt(board.length) == 0 || i == board.length) System.out.println("");
            for (int j = 0; j < board[i].length; j++) {
                if(j % Math.sqrt(board.length) == 0) System.out.print("| ");
                System.out.print(board[i][j] + " ");
                
            }
            System.out.println("");
        }
        System.out.println("");
    }

    // May be used to copy boards and compare different alghoritms on them.
    public static int[][] copy(int[][] board){
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i=0; i<board.length ; i++) {
            for (int j=0 ;j<board[0].length ;j++ ) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    // Turns a 81char string to a 9x9 board. Needs an update, fix possible outOfBounds exception by filling the remaining of the board with 0, try other stringToBoard mechanisms. Make it possible to generate 16x16 boards. MAKE THE BOARD A STRING[][] AND STORE VALUES FROM 1-9 AND A-G
    public static int[][] convert(String input){

    	int[][] values = new int[(int)(Math.sqrt(input.split("").length))][(int)(Math.sqrt(input.split("").length))];
    	int length = (int)(Math.sqrt(input.split("").length));
        int[][] newBoard = new int[length][length];
        for (int i=0;i<length;i++) {
             for (int j=0;j<length ;j++ ) {
                newBoard[i][j] = Integer.valueOf(input.split("")[(i*length+j)]);
             }
         } 
         return newBoard;
    }

    // gets board: testBoard/randomized/imported
    public static int[][] get(String rule, int hardness){
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
            Main.shuffle(board[0]);// Shuffles the first row.
            Main.backtrackSolve(board);
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


}