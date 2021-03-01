import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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


class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}


class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}



class Lekovi implements Comparable<Lekovi> {

    private int daliIma;
    private String ime;
    private int cena, brParce;

    public Lekovi( String ime,int daliIma, int cena, int brParce) {
        this.ime = ime;
        this.daliIma = daliIma;
        this.cena = cena;
        this.brParce = brParce;
    }

    @Override
    public String toString(){
        return ime+" "+daliIma+" "+cena+" "+brParce+"\n";
    }

    public int getDaliIma() {
        return daliIma;
    }

    public void setDaliIma(int daliIma) {
        this.daliIma = daliIma;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getBrParce() {
        return brParce;
    }

    public void setBrParce(int brParce) {
        this.brParce = brParce;
    }
    @Override
    public int hashCode(){
        int c1,c2,c3;
        c1 = ime.charAt(0);
        c2 = ime.charAt(1);
        c3 = ime.charAt(2);
        return (29*(29*(29*0 + c1)+c2)+c3)%102780;
    }
    @Override
    public int compareTo(Lekovi lek) {
        return this.ime.compareTo(lek.ime);
    }
}


public class Apteka {
    public static void main(String[] args)throws IOException {

        CBHT<String,Lekovi > table = new CBHT<String,Lekovi>(99);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N ; i++){
            String line = br.readLine();
            String[] parts = line.split(" ");
            Lekovi lek = new Lekovi(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));
            table.insert(parts[0], lek);
        }
        while(true){
            String poracani = br.readLine();
            String[] split = poracani.split(" ");
            if (poracani.equals("KRAJ")) break;
            if(table.search(split[0].toUpperCase())!= null){
                if(Integer.parseInt(split[1]) < table.search(split[0].toUpperCase()).element.value.getBrParce()){
                    System.out.println(table.search(split[0].toUpperCase()).element.value.toString());
                    System.out.println("Napravena naracka");

                }
                else {
                    System.out.println("Nema dovolno lekovi");
                }
            }
            else {
                System.out.println("Nema takov lek");
            }
        }


    }

}
