package XML;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

public class CheckXML {

    public static int checkValidXML(String [] check){
        LinkedStack<String> openedTags = new LinkedStack<>();
        int valid = 0;
        for(int i=0; i<check.length; i++){
            //check if it's a tag
            if(check[i].contains("[") || check[i].contains("]")){
                //if it is an opening tag push it into the stack
                if(!check[i].contains("/"))
                    openedTags.push(check[i]);

                //convert the opening tag on top of stack to closing tag and compare it with the real closing tag
                if(check[i].contains("/")){
                    String closedCmp = "[/" + openedTags.pop().substring(1);
                    if(check[i].equals(closedCmp))
                        valid = 1;
                    else {
                        valid = 0;
                        break;
                    }
                }
            }
        }
        return valid;
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        int valid;

        // Vasiot kod tuka
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni
        valid = checkValidXML(redovi);
        System.out.println(valid);

        br.close();
    }
}