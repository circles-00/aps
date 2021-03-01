package CoctailSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShakerSort {

    static void shakerSort(int a[], int n) {
        for(int i=0; i<n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] < a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }

            for(int i=n-1; i>=0; i--){
                for(int j=n-i-1; j>=0; j--){
                    if(a[j] < a[j-1]){
                        int temp = a[j];
                        a[j] = a[j-1];
                        a[j-1] = temp;
                    }
                }
            }

            for(int k=0; k<n; k++)
                System.out.print(a[k] + " ");
            System.out.print("\n");
        }


    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        shakerSort(a,n);
    }
}