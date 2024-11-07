package OOPs;
import java.util.*;

public class creatingArrayList {
    public static void main(String[] args) {
        //arraylist arr1=new arraylist(5);
        //arr1.add(2,3);
        //System.out.println(arr1.size);
        // System.out.println(arr1.arr[2]);
          arraylist arr =new arraylist();
          arr.add(2);
//        arr.add(3);
//        arr.add(4);
//        arr.add(6);
//        arr.add(7);
//        arr.set(0,1);
          arr.printArray();
          System.out.println(arr.length());
    }
}
class arraylist{
    int [] arr=new int[0];
    int length=0;
    int idx=0;

    arraylist()
    {

    }
    void add(int ele)
    {
        if(length==arr.length)
        {
            arr=Arrays.copyOf(arr,length+1);

        }
        arr[idx++]=ele;
        length++;
    }
    void set(int pos , int ele)
    {
        arr[pos]=ele;
    }
    int length()
    {
        return arr.length;
    }
    void printArray()
    {
        for(int x: arr)
            System.out.println(x);
    }
}
