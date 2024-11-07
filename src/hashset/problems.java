package hashset;

import java.util.HashSet;

public class problems {
    static int largestsubseq(int [] arr){
//        int k,len=0;
//        HashSet<Integer> hs=new HashSet<>();
//        for(int i=0;i<arr.length;i++){
//            hs.add(arr[i]);
//        }
//        for(int i=0;i<arr.length;i++){
//            int x=arr[i];
//            k=0;
//           Thats my method to check whether x=starting point.
//            while(hs.contains(x)&&!hs.contains(x-1)){
//                hs.remove(x);
//                x+=1;
//                k+=1;
//            }
//            System.out.println(k);
//            len=Math.max(len,k);
//        }
//        return len;

        int ans=0;
        HashSet<Integer> hs=new HashSet<>();
        for(int x:arr) hs.add(x);
        for(int x:hs){
            if(!hs.contains(x-1)){
                int curr=x;
                int k=1;
                while(hs.contains(curr+1)){
                    k+=1;
                    curr+=1;
                }
                ans=Math.max(k,ans);
            }
        }
        return ans;
    }
    static int distinctPairsInBag(int [] arr){
        int len=0;//arr refers to the bag;
        HashSet<Integer> hs=new HashSet<>();//refers table of question:
        //seecond attempt:
        for(int x:arr){
            if(!hs.contains(x)) hs.add(x);
            else hs.remove(x);
            len=Math.max(len,hs.size());
        }
        return len;
        //first attempt:
//        for(int x:arr) hs.add(x);
//        for(int i=0;i<arr.length;i++){
//            if(!hs.contains(arr[i]))  k-=1;
//            else{
//                hs.remove(arr[i]);
//                k+=1;
//            }
//            len=Math.max(len,k);
//         }
//         return len;
    }

    public static void main(String[] args) {
    }
}
