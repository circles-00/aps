import java.util.Scanner;


public class LDS {

    private static int najdolgaOpagackaSekvenca(int[] a) {
        int count, indexBuffer;
        int maximum = 0;
        for(int i=0; i<a.length; i++){
            count = 1;
            indexBuffer = i;
            for(int j=i; j<a.length; j++){
                if(a[j] < a[indexBuffer]){
                    count++;
                    indexBuffer = j;
                }
            }
            if(count > maximum)
                maximum = count;
        }
        return maximum;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }

}
