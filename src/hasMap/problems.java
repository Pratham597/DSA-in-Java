package hasMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class problems {
    static boolean anagramsCheck(String s1,String s2){
        if(s1.length()!=s2.length()) return false;
        Map<Character,Integer> mp1=new HashMap<>();
        Map<Character,Integer> mp2=new HashMap<>();
        int i=0;
        while(i<s1.length()){
            //storing the frequency of an characters in string and then last compare.
            char c1=s1.charAt(i);
            char c2=s2.charAt(i);
            i++;
            if(c1==c2) continue;
            if(mp1.containsKey(c1)){
                mp1.put(c1,mp1.get(c1)+1);
            }
            else mp1.put(c1,1);
            if(mp2.containsKey(c2)){
                mp2.put(c2,mp2.get(c2)+1);
            }
            else mp2.put(c2,1);
        }
        return mp1.equals(mp2);
    }
    //second approach:-
    static boolean anagrams(String s1,String s2){
        if(s1.length()!=s2.length()) return false;
        Map<Character,Integer> mp1=new HashMap<>();
        for(int i=0;i<s1.length();i++){
            char c1=s1.charAt(i);
            if(mp1.containsKey(c1)){
                mp1.put(c1,mp1.get(c1)+1);
            }
            else mp1.put(c1,1);
        }
        for(int i=0;i<s2.length();i++){
            char c2=s2.charAt(i);
            if(!mp1.containsKey(c2)) return false;
            mp1.put(c2,mp1.get(c2)-1);
        }
        for(int x:mp1.values()){
            if(x!=0) return false;
        }
        return true;
    }
    //wap to find pair in arrray to locate target:
    static int[]twoSum(int[] arr ,int sum){
        int [] ans=new int[2];
        ans[0]=-1;
        Map<Integer,Integer> mp=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(mp.containsKey(sum-arr[i])) {
                ans[0]=mp.get(sum-arr[i]);
                ans[1]=i;
                return ans;
            }
            mp.put(arr[i],i);
        }
        return ans;
    }
    //wap to check whether given strings are isomorphic or not isomorphic.
    static boolean isomorphic(String s1,String s2){
        Map<Character,Character> mp=new HashMap<>();
        for(int i=0;i<s1.length();i++){
            char c1=s1.charAt(i);
            char c2=s2.charAt(i);
            if(!mp.containsKey(c1)) {
                if(mp.containsValue(c2)) return false;
                mp.put(c1,c2);
            }
            else if(mp.get(c1)!=c2) return false;
        }
        return true;
    }
    //wap to find the length of largest subaaray sum to zero
    static int largestSubArray(int [] arr){
        int len=-1;
        for(int i=0;i<arr.length-1;i++){
            int sum=arr[i];
            for(int j=i+1;j<arr.length;j++){
                sum+=arr[j];
                if(sum==0){
                    if(len<j-i) len=j-i;
                }
            }
        }
        return len+1;
    }
    //wah pratham maza aa gya!tusi great ho tohfa kabool karo.
    static int largestsubarray(int[] arr){
        Map<Integer,Integer> mp=new HashMap<>();
        mp.put(0,-1);
        int len=0,sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(mp.containsKey(sum)){
                int x=mp.get(sum);
                if(len<i-x) len=i-x;
            }
            else mp.put(sum,i);
        }
        return len;
    }
    public static void main(String[] args) {
        int arr[]={5,2,1,-3,10 ,2,1,-3,0,4};
        System.out.println(largestsubarray(arr));

    }

}

