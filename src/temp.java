import java.util.ArrayList;
import java.util.LinkedList;

public class temp {
    static void merge(int[] arr,int l,int mid,int r){
        int [] ans=new int[mid+1-l];
        int [] bar=new int[r-mid];
        int z=0;
        for(int i=l;i<=mid;i++){
            ans[z++]=arr[i];
        }
        z=0;
        for(int i=mid+1;i<=r;i++){
            bar[z++]=arr[i];
        }
        int i=0,j=0,k=l;
        while(i<mid+1-l&&j<r-mid){
            if(ans[i]<bar[j]){
                arr[k++]=ans[i++];
            }
            else{
                arr[k++]=bar[j++];
            }
        }
        while(i<mid+1-l){
            arr[k++]=ans[i++];
        }
        while(j<r-mid){
            arr[k++]=bar[j++];
        }
    }
    static void mergesort(int[] arr,int l,int r){
        if(l>=r) return;
        int mid=l+(r-l)/2;
        mergesort(arr,l,mid);
        mergesort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }
    static ArrayList<Integer> search(String pat, String txt)
    {
        StringBuilder ans=new StringBuilder();
        ArrayList<Integer> arr=new ArrayList<>();
        int k=0 ,len=pat.length();
        for(int i=0;i<txt.length();i++){
            ans.append(txt.charAt(i));
            k++;
            if(k==len){
                System.out.println(ans);
                if((ans+"").equals(pat)){
                    arr.add(i-len+2);
                    System.out.println(arr);
                }
                ans.setLength(0);
                k=0;
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        ArrayList<Integer> arr= search("geek","geeksforgeeks");
        //optimise bubble sort for nearly sorted arrays:
        //quick sort:-
//        for(int i=1;i<n-1;i++){
//            int j=i;
//            while(j>0&&arr[j]<arr[j-1]){
//                int temp=arr[j];
//                arr[j]=arr[j-1];
//                arr[j-1]=temp;
//                j--;
//            }
//        }
//        for(int i=0;i<n-1;i++){
//            int min=i;
//            for(int j=i+1;j<n;j++){
//                if(arr[min]>arr[j]) min=j;
//            }
//            int temp=arr[min];
//            arr[min]=arr[i];
//            arr[i]=temp;
//        }
//        for(int i=0;i<n-1;i++){
//            boolean flag=false;
//            for(int j=0;j<n-i-1;j++){
//                if(arr[j]>arr[j+1]){
//                    int temp=arr[j];
//                    arr[j]=arr[j+1];
//                    arr[j+1]=temp;
//                    flag=true;
//                }
//                if(!flag) break;
//            }
//        }
//        5,1,2,4,3,6,7
//        int [] arr={1,2,3,9,0,8,7};
//        int n=arr.length;
//        quicksort(arr,2,5);
//
////        mergesort(arr,1,n-1);
//        for(int var:arr){
//            System.out.print(var+" ");
//        }

    }
    static int partition(int[] arr,int st,int end){
        int mid=arr[st+(end-st)/2];
        int ct=st;
        for(int i=st;i<=end;i++){
            if(arr[i]<mid) ct++;
        }
        int temp=arr[ct];
        arr[ct]=mid;
        arr[st+(end-st)/2]=temp;
        int i=st,j=end;
        while(i<ct&&j>ct){
            while(arr[i]<=arr[ct]) i++;
            while(arr[j]>=arr[ct]) j--;
            if(i<ct&&j>ct){
                temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }
        return ct;
    }
    static void quicksort(int[]arr,int st,int end){
        if(st>=end) return;
        int pi=partition(arr,st,end);
        quicksort(arr,st,pi-1);
        quicksort(arr,pi+1,end);
    }
    static int gcd(int x,int y){
        if(y==0) return x;
        return gcd(y,x%y);
    }
    static String slice(String s,int idx){
        if(idx==s.length()) return " ";
        else if(s.charAt(idx)!='a') return s.charAt(idx)+slice(s,idx+1);
        return slice(s,idx+1);
    }
    static void seq(String s,String ans){
        if(s.length()==0){
            System.out.print(ans+" ");
            return;
        }
        char c=s.charAt(0);
        seq(s.substring(1),ans+c);
        seq(s.substring(1),ans);
    }
    static ArrayList<String> sequence(String s){
        ArrayList<String> str=new ArrayList<>();
        if(s.length()==0) {
             str.add(" ");
             return str;
        }
        char c=s.charAt(0);
        ArrayList<String> str1=sequence(s.substring(1));
        str.addAll(str1);
        for(String x:str1){
            str.add(c+x);
        }
        return str;
    }
}
