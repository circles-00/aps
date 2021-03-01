import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array<E> {
    private E data[];
    private int size;

    Array(int size){
        this.data = (E[]) new Object[size];
        this.size = size;
    }

    public void set (int position, E set){
        if(position>=0 && position<this.size)
            this.data[position] = set;
        else
            System.out.println("Cannot set data, invalid position");
    }

    public E get(int position){
        if(position>=0 && position<this.size)
            return this.data[position];
        else
            System.out.println("Cannot get data, invalid position");
        return null;
    }

    public int getSize(){
        return this.size;
    }

    public int findIndex(E find){
        for(int i=0; i<this.size; i++) {
            if (this.data[i].equals(find))
                return i;
        }
        return -1;
    }

    public void insert(int position, E ins){
        if(position>=0 && position<this.size){
            E[] newData = (E[]) new Object[size+1];
            for(int i=0; i<position; i++)
                newData[i] = this.data[i];
            newData[position] = ins;
            for(int i=position; i<this.size; i++)
                newData[i+1] = this.data[i];
            data = newData;
            this.size += 1;
        }
    }

    public void delete(int position){
        if(position>=0 && position<this.size){
            E[] newData = (E[]) new Object[size-1];
            for(int i=0; i<position; i++)
                newData[i] = this.data[i];
            for(int i=position+1; i<this.size; i++)
                newData[i-1] = data[i];
            this.data = newData;
            this.size -= 1;
        }
    }

    public void resize(int newSize){
        E[] newData = (E[]) new Object[newSize];
        int copySize = this.size;
        if(newSize < size)
            copySize = newSize;
        for(int i=0; i<copySize; i++)
            newData[i] = this.data[i];
        this.data = newData;
        this.size = newSize;
    }

    public int getSum(Array<Integer> array){
        int sum = 0;
        for(int i=0; i<array.getSize(); i++)
            sum += array.get(i);
        return sum;
    }

    public static double getAvgDiff(double avg, int diff){
        return Math.abs(avg - (double)diff);
    }

    public static int brojDoProsek(Array<Integer> niza){
        double avg = (double)niza.getSum(niza) / niza.getSize();
        int min;
        min = niza.get(0);
        for(int i=1; i<niza.getSize();i++){
            if(getAvgDiff(avg, niza.get(i)) < getAvgDiff(avg, min))
                min = niza.get(i);
            if(getAvgDiff(avg, niza.get(i)) == getAvgDiff(avg, min))
                if(min > niza.get(i))
                    min = niza.get(i);
        }
        return min;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        //Vashiot kod tuka...
        Array<Integer> niza = new Array<>(N);
        for(int i=0; i<niza.getSize(); i++)
            niza.set(i, Integer.parseInt(stdin.readLine()));
        System.out.println(brojDoProsek(niza));
    }



}
