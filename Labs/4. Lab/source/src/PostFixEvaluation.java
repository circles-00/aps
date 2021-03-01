import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class LinkedStack<E> implements Stack<E> {

    //Stekot e pretstaven na sledniot nacin: top e link do prviot jazol
    // na ednostrano-povrzanata lista koja sodrzi gi elementite na stekot .
    private SLLNode<E> top;

    public LinkedStack () {
        // Konstrukcija na nov, prazen stek.
        top = null;
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (top == null);
    }

    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (top == null)
            throw new NoSuchElementException();
        return top.element;
    }

    public void clear () {
        // Go prazni stekot.
        top = null;
    }

    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        top = new SLLNode<E>(x, top);
    }

    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (top == null)
            throw new NoSuchElementException();
        E topElem = top.element;
        top = top.succ;
        return topElem;
    }

}


public class PostFixEvaluation {

    public static int solve(char array []){
        LinkedStack<Integer> operands = new LinkedStack<>();
        for(int i=0; i<array.length; i++) {
            if (array[i] == ' ')
                continue;
            //construct the number and push it to the stack
            else if (Character.isDigit(array[i])){
                int number = 0;
                while(Character.isDigit(array[i])){
                    number = number * 10 + (array[i]-'0');
                    i++;
                }
                i--;
                operands.push(number);
            }
            //evaluate the expression for the last 2 numbers and push the result to the stack
            else {
                int lastOperand = operands.pop();
                int secondLastOperand = operands.pop();
                if(array[i] == '+')
                    operands.push(secondLastOperand + lastOperand);
                else if(array[i] == '-')
                    operands.push(secondLastOperand - lastOperand);
                else if(array[i] == '*')
                    operands.push(secondLastOperand * lastOperand);
                else if(array[i] == '/')
                    operands.push(secondLastOperand / lastOperand);
            }
        }

        //return the final result
        return operands.pop();
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        System.out.println(solve(exp));

        br.close();

    }
}
