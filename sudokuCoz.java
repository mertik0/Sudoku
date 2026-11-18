import java.util.Random;

public class sudokuCoz {

    public static void yazdir(int[][] tahta) {

        for (int i = 0; i < tahta.length; i++) {
            if(i%3==0) System.out.println("");
            for (int a = 0; a < tahta[i].length; a++) {
                if(a%3==0) System.out.print("| ");
                System.out.print(tahta[i][a] + " ");
                
            }
            System.out.println("");
        }
    }
    
    public static boolean uygunMu(int[][] tahta, int satir, int sutun, int sayi) {
        for(int i=0; i<9;i++){
            if(tahta[satir][i]==sayi) return false;
        }
        for(int i=0; i<9; i++){
            if(tahta[i][sutun]==sayi) return false;
        }
        int kutuBaslangicSatiri= satir/3*3;
        int kutuBaslangicSutunu = sutun/3*3;
        for(int satirDenemeDegiskeni=0; satirDenemeDegiskeni<3;satirDenemeDegiskeni++){
            for(int sutunDenemeDegiskeni=0; sutunDenemeDegiskeni<3; sutunDenemeDegiskeni++){
                if(tahta[satirDenemeDegiskeni+kutuBaslangicSatiri][sutunDenemeDegiskeni+kutuBaslangicSutunu] == sayi) return false;
            }
        }
        return true;
    }
    
    public static boolean coz(int[][] tahta) {
        for (int satir = 0; satir < 9; satir++) {
            for (int sutun = 0; sutun < 9; sutun++) {
                if (tahta[satir][sutun] == 0) {          
                    for (int sayi = 1; sayi <= 9; sayi++) {
                        if (uygunMu(tahta, satir, sutun, sayi)) {
                            tahta[satir][sutun] = sayi;   
                            if(coz(tahta)) return true;
                            else tahta[satir][sutun] = 0;
                            
                        }
                    }
                    return false; 
                }
            }
        }
        return true; 
    }

    private static boolean solve(int[][] board){
        for (int row = 0 ; row < board.length ; row++){
            for (int column = 0 ; column < board[0].length ; column++){
                if (board[row][column] == 0){
                    int[] numbers = {1,2,3,4,5,6,7,8,9};
                    shuffle(numbers);
                    for (int i = 0 ; i < numbers.length ; i++){
                        if (uygunMu(board, row, column, numbers[i])){
                            board[row][column] = numbers[i];
                            if (solve(board)){
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

    private static int[][] generate(){
        int[][] board = new int[9][9];
        for (int i = 0 ; i < board[0].length ; i++){
            board[0][i] = i + 1;
        } 
        shuffle(board[0]);// Shuffles the first row.
        solve(board);
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


    public static void main(String[] args) {
        // int[satir][sutun]
        int[][] tahta = {
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
        //long baslangicZamani = System.nanoTime();
        //boolean cozuldu = coz(tahta);
        //long bitisZamani = System.nanoTime();
        //double cozumSuresi = (bitisZamani - baslangicZamani) / 1000000000.0;
        //System.out.println("Çözüm süresi: "+ cozumSuresi + " saniye");
        //if(cozuldu) yazdir(tahta);
        //else System.out.println("Çözüm yok.");
        System.out.println("==============");
        yazdir(generate()); //This is a temporary action.
    }
}
