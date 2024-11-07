package hasMap;

import java.util.HashMap;
import java.util.Map;

public class BasicStl {
    public static void main(String[] args) {
        int [] arr={4,2,3,4,3,2,2,2,4};
        System.out.println(frequentElement(arr));
    }
    static int frequentElement(int[] arr){
        int n=arr.length;
        Map<Integer ,Integer> mp=new HashMap<>();
        int max=arr[0];
        for(int i=0;i<n;i++){
            if(mp.containsKey(arr[i])){
                mp.put(arr[i],mp.get(arr[i])+1);
            }
            else mp.put(arr[i],1);
        }
        for(var key:mp.keySet()){
            if(mp.get(key)>mp.get(max)) max=key;
        }//value-frequency of element and key-representing an element:
        //System.out.println();
        return max;
    }
}
