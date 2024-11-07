package binarySearch;

import java.text.DecimalFormat;
import java.util.*;

public class problems2 {
    static boolean possibleSplit(int [] arr,int k,int mid){
        int count =1,sum=0;
        for(int i=0;i<arr.length;i++){
            if(sum+arr[i]<=mid)sum+=arr[i];
            else{
                count+=1;
                sum=arr[i];
            }
        }
        return count<=k;
    }
    static int  splitArray(int [] arr,int k){
        int sum=0;
        int max=Integer.MIN_VALUE;
        int n=arr.length;
        for(int i=0;i<n;i++){
            max=Math.max(max,arr[i]);
            sum+=arr[i];
        }
        int st=max,end=sum;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(possibleSplit(arr,k,mid)) end=mid-1;
            else st=mid+1;
        }
        return st;
    }
    static double minimiseGasStations(int [] arr,int k){
        int diff=Integer.MIN_VALUE;
        for(int i=1;i<arr.length;i++){
            diff=Math.max(diff,arr[i]-arr[i-1]);
        }
        double end=diff,st=0;
        while(end-st>0.01){
            double mid=st+(end-st)/2;
            if(possibleGas(arr,k,mid)) end=mid;
            else st=mid;
        }
        return end;
    }

    static boolean possibleGas(int[] arr, int k, double mid) {
        int  prev=arr[0];
        int count=0;
        for(int i=1;i<arr.length;i++){
            count+=(int)Math.ceil((arr[i]-prev)/mid)-1;
            prev=arr[i];
            if(count>k) return false;
        }
        return count<=k;
    }
    static double medianSortedTwoArrays(int [] arr1,int [] arr2){
        int n1=arr1.length,n2=arr2.length;
        if(n1>n2) return medianSortedTwoArrays(arr2,arr1);
        int st=0,end=n1;
        int size=(n1+n2+1)/2;
        while(st<=end){
            int mid=st+(end-st)/2;
            int mid2=size-mid;
            int r1=Integer.MAX_VALUE,r2=Integer.MAX_VALUE,l1=Integer.MIN_VALUE,l2=Integer.MIN_VALUE;

            if(mid<n1) r1=arr1[mid];
            if(mid2<n2) r2=arr2[mid];
            if(mid>0) l1=arr1[mid-1];
            if(mid2>0) l2=arr2[mid2-1];
            if(l1<=r2&&l2<=r1){
                if((n1+n2)%2!=0) return Math.max(l1,l2);
                return (Math.min(r1,r2)+Math.max(l1,l2))/2.0;
            }
            else if(l1>r2) end=mid-1;
            else st=mid+1;
        }
        return 0;
    }
    static int findmaximumfrequency(int [] arr){
        int n=arr.length;
        HashMap<Integer,Integer> mp=new HashMap<>();
        HashMap<Integer,Integer> num=new HashMap<>();
        int max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            if(num.containsKey(arr[i])) {
                int freq=num.get(arr[i]);
                max=Math.max(max,freq+1);
                if(mp.containsKey(freq+1)) mp.put(freq+1,mp.get(freq+1)+1);
                else mp.put(freq+1,1);
                num.put(arr[i],freq+1);
            }
            else{
                max=Math.max(max,1);
                num.put(arr[i],1);
                if(!mp.containsKey(1))mp.put(1,1);
                else mp.put(1,mp.get(1)+1);
            }
        }
        return mp.get(max)*max;
    }
    static int index(int [] arr,int k){
        int st=0;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]>=k) end=mid-1;
            else st=mid+1;
        }
        return st;
    }
    static int index1(int [] arr,int k){
        int st=0;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]>k) end=mid-1;
            else st=mid+1;
        }
        return st;
    }
    static int kthelementSorted(int [] arr1,int [] arr2,int k){
        int st=0;
        k=k-1;
        int end=arr1.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            int check=index(arr2,arr1[mid])+mid;
            //check signifies how much you are greater than others.
            if(check==k) return arr1[mid];
            else if(check>k) end=mid-1;
            else st=mid+1;
        }
         st=0;
        end=arr2.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            int check=index1(arr1,arr2[mid])+mid;
            //check signifies how much you are greater than others.
            if(check==k) return arr2[mid];
            else if(check>k) end=mid-1;
            else st=mid+1;
        }
        return -1;
    }
    static double test(int [] arr1,int [] arr2){
        int n1=arr1.length,n2=arr2.length;
        if(n1>n2) return test(arr2,arr1);
        int st=0;
        int end=n1;
        int lengthmid=(n1+n2+1)/2;
        while(st<=end){
            int mid1=st+(end-st)/2;
            int mid2=lengthmid-mid1;
            int r1=Integer.MAX_VALUE ,r2=Integer.MAX_VALUE,l1=Integer.MIN_VALUE,l2=Integer.MIN_VALUE;
            if(mid1<n1) r1=arr1[mid1];
            if(mid2<n2) r2=arr2[mid2];
            if(mid1>0) l1=arr1[mid1-1];
            if(mid2>0) l2=arr2[mid2-1];
            if(l1<=r2&&l2<=r1){
                if((n1+n2)%2!=0) return Math.max(l1,l2);
                return (Math.min(r1,r2)+Math.max(l1,l2))/2.0;
            }
            else if(l1>r2) end=mid1-1;
            else st=mid1+1;
        }
        return 0;
    }
    static int kthelementSortedArrays(int [] arr1,int[] arr2,int k){
        int n1=arr1.length,n2=arr2.length;
        if(n1>n2) return kthelementSortedArrays(arr2,arr1,k);
        int st=Math.max(0,k-n2),end=Math.min(n1,k);//imp concepts
        while(st<=end){
            int mid=st+(end-st)/2;
            int mid2=k-mid;
            int r1=Integer.MAX_VALUE,r2=Integer.MAX_VALUE,l1=Integer.MIN_VALUE,l2=Integer.MIN_VALUE;

            if(mid<n1) r1=arr1[mid];
            if(mid2<n2) r2=arr2[mid2];
            if(mid>0) l1=arr1[mid-1];
            if(mid2>0) l2=arr2[mid2-1];
            if(l1<=r2&&l2<=r1){
                return Math.max(l1,l2);
            }
            else if(l1>r2) end=mid-1;
            else st=mid+1;
        }
        return 0;
    }
    static int countZeroes(int [] arr){
        int st=0;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]==1) end=mid-1;
            else st=mid+1;
        }
        return arr.length-st;
    }
    static int maximumZeroesRow(int [][] arr1){
        int n= arr1.length;
        int max=Integer.MIN_VALUE;
        int row=0;
        for(int i=0;i<n;i++){
            int ans=countZeroes(arr1[i]);
            if(ans>max){
                max=ans;
                row=i;
            }
        }
        return row;
    }
    static int maximum(int [][] arr,int col){
        int max=Integer.MIN_VALUE;
        int idx=-1;
        for(int i=0;i<arr.length;i++){
            if(arr[i][col]>max){
                idx=i;
                max=arr[i][col];
            }
        }
        return idx;
    }
    static int[]findPeakMatrix(int [][] arr){
        int st=0;
        int end=arr[0].length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            int idx=maximum(arr,mid);
            int left=(mid-1>=0)?arr[idx][mid-1]:-1;
            int right=(mid<arr[0].length-1)?arr[idx][mid+1]:-1;
            if(arr[idx][mid]>left&&arr[idx][mid]>right) return new int[]{idx,mid};
            else if(left>arr[idx][mid]) end=mid-1;
            else st=mid+1;
        }
        return new int[]{-1,-1};
    }
    static int findSmaller(int [] arr,int val){
        int st=0;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]<=val) st=mid+1;
            else end=mid-1;
        }
        return st;
    }
    static int smallerValues(int[][] arr,int val){
        int n=arr.length;
        int sum=0;
        for(int i=0;i<n;i++){
             sum+=findSmaller(arr[i],val);
        }
        return sum;
    }
    static int findmedianatrix(int [][] arr){
        int n=arr.length;
        int m=arr[0].length;
        int req=(m*n)/2;
        int st=Integer.MAX_VALUE,end=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            st=Math.min(arr[i][0],st);
            end=Math.max(end,arr[i][m-1]);
        }
        while(st<=end){
            int mid=st+(end-st)/2;
            int index=smallerValues(arr,mid);
            if(index<=req) st=mid+1;
            else end=mid-1;
        }
        return st;
    }
    public static  long findEnd(long ans){

        long st=0;
        long end=ans;
        while(st<=end){
            long mid=st+(end-st)/2;
            long check= (long) ((ans)/Math.pow(2,mid));
            if(check<=0) end=mid-1;
            else st=mid+1;
        }
        return (long)Math.pow(2,st);
    }
    public static long bit(long ans,long x){
        long st=ans+1;
        long end=findEnd(ans+1)-1;
        while(st<=end){
            long mid=st+(end-st)/2;
            long check=mid & x;
            if(check>=x) end=mid-1;
            else st=mid+1;
        }
        return st;
    }
    public static long minEnd(int n, int x) {
        long ans=x;
        for(int i=0;i<n-1;i++){
            ans=bit(ans,x);
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(minEnd(4,1));
    }
}
