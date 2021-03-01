package MVR;

import java.util.NoSuchElementException;
import java.util.Scanner;

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

interface Queue<E> {
    public boolean isEmpty ();
    public int size ();
    public E peek ();
    public void clear ();
    public void enqueue (E x);
    public E dequeue ();
}

class LinkedQueue<E> implements Queue<E> {
    SLLNode<E> front, rear;
    int length;

    public LinkedQueue () {
        clear();
    }

    public boolean isEmpty () {
        return (length == 0);
    }

    public int size () {
        return length;
    }

    public E peek () {
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear () {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue (E x) {
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue () {
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null)  rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}


class Gragjanin{
    private String imePrezime;
    private int lKarta;
    private int pasos;
    private int vozacka;

    public Gragjanin(String imePrezime, int lKarta, int pasos, int vozacka) {
        this.imePrezime = imePrezime;
        this.lKarta = lKarta;
        this.pasos = pasos;
        this.vozacka = vozacka;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public int getlKarta() {
        return lKarta;
    }

    public int getPasos() {
        return pasos;
    }

    public int getVozacka() {
        return vozacka;
    }

    public void setlKarta(int lKarta) {
        this.lKarta = lKarta;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public void setVozacka(int vozacka) {
        this.vozacka = vozacka;
    }
}

public class MVR {

    public static void checkFirst(Gragjanin [] ppl){
        LinkedQueue<Gragjanin> lKarti = new LinkedQueue<Gragjanin>();
        LinkedQueue<Gragjanin> pasosi = new LinkedQueue<Gragjanin>();
        LinkedQueue<Gragjanin> vozacki = new LinkedQueue<Gragjanin>();

        for(int i=0; i<ppl.length; i++){
            if(ppl[i].getlKarta() == 1)
                lKarti.enqueue(ppl[i]);
            else if(ppl[i].getPasos() == 1)
                pasosi.enqueue(ppl[i]);
            else if(ppl[i].getVozacka() == 1)
                vozacki.enqueue(ppl[i]);
        }

        while(!lKarti.isEmpty()){
                if(lKarti.peek().getPasos() == 1)
                    pasosi.enqueue(lKarti.dequeue());
                else if(lKarti.peek().getVozacka() == 1)
                    vozacki.enqueue(lKarti.dequeue());
                else
                    System.out.println(lKarti.dequeue().getImePrezime());
        }


        while(!pasosi.isEmpty()){
            if(pasosi.peek().getVozacka() == 1)
                vozacki.enqueue(pasosi.dequeue());
            else
                System.out.println(pasosi.dequeue().getImePrezime());
        }

        while(!vozacki.isEmpty()){
            System.out.println(vozacki.dequeue().getImePrezime());
        }


    }

    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);
        int N = Integer.parseInt(br.nextLine());
        Gragjanin covek[] = new Gragjanin[N];
        for (int i = 0; i < N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            covek[i] = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
        }
        checkFirst(covek);
    }
}
