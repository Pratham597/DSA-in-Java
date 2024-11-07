package hasMap;

import java.util.HashMap;
import java.util.LinkedList;

public class implementation {
    public static class HashMap<K,V>{
        private final int capacity=4;
        private int n=0;
        private LinkedList<Node>[] buckets;
        private float load=0.75f;
        private class Node{
            K key;
            V value;
            Node(K key,V value){
                this.key=key;
                this.value=value;
            }
        }
        private int HashFunc(K key){
            int hc=key.hashCode();
            return Math.abs(hc%buckets.length);
        }
        private void initbuckets(int n){
            buckets=new LinkedList[n];
            for(int i=0;i<buckets.length;i++){
                buckets[i]=new LinkedList<>();
            }
        }
        public HashMap(){
            initbuckets(capacity);
        }
        // Traverse a linked ;ist for node with key if found it returns idx otherwise it returns null;
        private int searchInBucket(LinkedList<Node> l1, K key){
            for(int i=0;i<l1.size();i++){
                if(l1.get(i).key==key) return i;
            }
            return -1;
        }
        void put(K key,V value){
            int idx=HashFunc(key);
            LinkedList<Node> current=buckets[idx];
            int search=searchInBucket(current,key);
            if(search==-1){
                Node temp=new Node(key,value);//element of that array
                current.add(temp);
                n++;
            }
            else {//updating a value if it is there:
                current.get(search).value = value;
            }
            if(n>=buckets.length*load){
                rehash();
            }
        }
        void rehash(){
            n=0;
            LinkedList<Node>[] temp=buckets;
            initbuckets(buckets.length*2);
            for(var x:temp){
                for(var y:x){
                    put(y.key,y.value);
                }
            }
        }
        void displaY(){
            for(var x:buckets){
                for(var y:x){
                    System.out.printf(y.key+"="+y.value+" ");
                }
            }
            System.out.println();
        }
        V get(K key){
            int idx=HashFunc(key);
            LinkedList<Node> l1= buckets[idx];
            for(int i=0;i<l1.size();i++){
                if(l1.get(i).key==key) {
                    return l1.get(i).value;
                }
            }
            return null;
        }
        int size(){
            return n;
        }
        V remove(K key){
            int idx=HashFunc(key);
            LinkedList<Node> l1= buckets[idx];
            int e1=searchInBucket(l1,key);
            if(e1!=-1){
                Node currNode=l1.get(e1);
                V val=currNode.value;
                l1.remove((e1));
                n--;
                return val;

            }
            return null;
        }
    }
    public static void main(String[] args) {
        HashMap<String ,Integer> mp=new HashMap<>();
        mp.put("a",1);
        mp.put("b",2);
        mp.put("c",3);
        mp.put("d",4);
        mp.put("e",5);
        mp.put("f",6);
        mp.put("g",7);
        mp.put("h",8);
        System.out.println(mp.capacity);
        mp.displaY();
        System.out.println(mp.get("d"));
        System.out.println(mp.size());
    }
}
