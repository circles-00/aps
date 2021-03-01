package EvenOddSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OddEvenSort {

    static void oddEvenSort(int a[], int n) {
        int evenCount = 0, oddCount = 0;

        for(int i=0; i<n; i++){
            if(a[i]%2 == 0)
                evenCount++;
            else
                oddCount++;
        }

        int even[] = new int[evenCount];
        int odd[] = new int[oddCount];

        int evenIndex = 0;
        int oddIndex = 0;

        for(int i=0; i<n; i++){
            if(a[i]%2 == 0){
                even[evenIndex] = a[i];
                evenIndex++;
            }
            else {
                odd[oddIndex] = a[i];
                oddIndex++;
            }
        }

        for(int i=0; i<(evenCount-1); i++){
            for(int j=0; j<(evenCount-i-1); j++){
                if (even[j] < even[j + 1]) {
                    int tmp = even[j];
                    even[j] = even[j + 1];
                    even[j + 1] = tmp;
                }
            }
        }

        for(int i=0; i<(oddCount-1); i++){
            for(int j=0; j<(oddCount-i-1); j++){
                if (odd[j] > odd[j+1]) {
                    int tmp = odd[j];
                    odd[j] = odd[j+1];
                    odd[j + 1] = tmp;
                }
            }
        }

        for(int i=0; i<oddCount; i++)
            a[i] = odd[i];

        for(int i=oddCount, j=0; i<n; i++, j++)
            a[i] = even[j];
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
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}