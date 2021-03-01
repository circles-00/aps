package StaticRouting;

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
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}

class HashMap<K extends Comparable<K>, E> {
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public HashMap(int m) {
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
//        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
//            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
//                curr.element = newEntry;
//                return;
//            }
//        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
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

public class RoutingHashJava {
    public static boolean checkRange(String sourceIp, String sourceSubnet){
        int c1=0, c2=0;
        String [] ip = sourceIp.split("\\.");
        String [] subnet = sourceSubnet.split("\\.");
        int [] ipOctaves = new int[4];
        int [] subnetOctaves = new int[4];
        for (String s:ip) {
            ipOctaves[c1] = Integer.parseInt(s);
            c1++;
        }
        for (String s:subnet) {
            subnetOctaves[c2] = Integer.parseInt(s);
            c2++;
        }

        int finalIP = ((ipOctaves[0] & 0xFF) << 24) |
                ((ipOctaves[1] & 0xFF) << 16) |
                ((ipOctaves[2] & 0xFF) << 8)  |
                ((ipOctaves[3] & 0xFF) << 0);

        int finalSubnet = ((subnetOctaves[0] & 0xFF) << 24) |
                ((subnetOctaves[1] & 0xFF) << 16) |
                ((subnetOctaves[2] & 0xFF) << 8)  |
                ((subnetOctaves[3] & 0xFF) << 0);

        int mask = -1 << 8;

        return (finalIP & mask) == (finalSubnet & mask);
    }

    public static void main(String [] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int nRouters = Integer.parseInt(input.readLine());
        HashMap<String, String> hashMap = new HashMap<>(31);

        for(int i=0; i<nRouters; i++){
            String routerInterface = input.readLine();
            String routingAddresses = input.readLine();
            String[] split = routingAddresses.split(",");
            for (String s: split)
                hashMap.insert(routerInterface, s);
        }
//        System.out.println(hashMap);

        int nAttempts = Integer.parseInt(input.readLine());
        for(int i=0; i<nAttempts; i++){
            boolean check = false;
            String routerInterface = input.readLine();
            String routingAddress = input.readLine();
            SLLNode<MapEntry<String, String>> search = hashMap.search(routerInterface);
            if(search != null){
                while(search != null){
                    check = checkRange(routingAddress, search.element.value);
                    if(check == true)
                        break;
                    search = search.succ;
                }
            }
            if(check == true)
                System.out.println("postoi");
            else
                System.out.println("ne postoi");
        }
    }
}
