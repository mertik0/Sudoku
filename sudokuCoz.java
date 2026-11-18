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

    public static int[][] generate(){
        int[][] board = new int[9][9];
        for (int i = 0 ; i < board[0].length ; i++){
            board[0][i] = i + 1;
        } 
        shuffle(board);
        return board;
    }

    // Will use Fisher-Yates shuffle

    public static void shuffle(int[][] arr){
        Random rd = new Random();
        for (int i = arr[0].length - 1 ; i >= 0 ; i--){
            int index = rd.nextInt(i+1);
            int temp = arr[0][index];
            arr[0][index] = arr[0][i];
            arr[0][i] = temp;
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
        long baslangicZamani = System.nanoTime();
        boolean cozuldu = coz(tahta);
        long bitisZamani = System.nanoTime();
        double cozumSuresi = (bitisZamani - baslangicZamani) / 1000000000.0;
        System.out.println("Çözüm süresi: "+ cozumSuresi + " saniye");
        if(cozuldu) yazdir(tahta);
        else System.out.println("Çözüm yok.");
        System.out.println("============");
        int[][] attempt = generate();

        shuffle(attempt);

        yazdir(attempt); //This is a temporary action.
    }
}
