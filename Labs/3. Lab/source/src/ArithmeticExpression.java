import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int solveSingle(char c[]){
        char sign='/';
        int first=0, second=0, counter=1;
        for(int i=0; i<c.length; i++){
            if(c[i] == '+' || c[i] == '-')
                sign = c[i];
            if(counter == 1) {
                if (Character.isDigit(c[i])) {
                    first = c[i] - '0';
                    counter++;
                }
            }
            else {
                if (Character.isDigit(c[i]))
                    second = c[i] - '0';
            }
        }
        if(sign == '+')
            return first+second;
        else if(sign == '-')
            return first-second;
        else
            return -1;
    }
    static int presmetaj(char c[], int l, int r) {
        if(r-l == 5)
            return solveSingle(c);
        if(l == r)
            return
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
