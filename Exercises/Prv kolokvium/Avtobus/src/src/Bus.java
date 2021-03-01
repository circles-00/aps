import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        if(M > N)
            System.out.println((M-N)* 100 + N*100);
        else
            System.out.println(N*100);

        if(M == 0)
            System.out.println(N*100 + (M)*100);
        else
            System.out.println(N*100 + (M-1)*100);


    }

}
// I HAVE NO IDEA WHAT IS THIS EXERCISE FFS IDK HOW IT'S RIGHT BUT IT WORKS FUCK IT