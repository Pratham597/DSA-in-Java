package sorting;
import java.util.*;
public class sortingbasics {
    public static void main(String[] args) {
        int [] arr ={1,2,3,7,8,4,5,6};
       // bubbleSort(arr);
        //selectionSort(arr);
        //insertionSort(arr);
        //mergeSort(arr,0,arr.length-1);
         quickSort(arr,0,arr.length-1);
        for(int x:arr)
            System.out.print(x+" ");
    }
    static int partiton(int[] arr,int st,int end){
        int mid=(st+end)/2;
        int check=arr[mid];
        int count=st;
        for(int i=st;i<=end;i++)
        {
            if(arr[i]<check){ count++;}
        }
        int temp=arr[count];
        arr[count]=check;
        arr[mid]=temp;
        int i=st,j=end;
        while(i<j){
          while(arr[i]<=check) i++;
          while(arr[j]>check) j--;
          if(i<count&&j>count)
          {
              temp=arr[i];
              arr[i]=arr[j];
              arr[j]=temp;
              i++;
              j--;
          }
        }
        return count;
    }
    static void quickSort(int [] arr,int st ,int end){
        if(st>=end) return;
        int x=partiton(arr,st,end);
        quickSort(arr, st,x-1);
        quickSort(arr,x+1,end);

    }
    static void merge(int[] arr, int st,int mid,int end){
        int l1=mid-st+1;
        int l2=end-mid;
        int [] arr1=new int[l1];
        int [] arr2=new int[l2];
        for(int i=0;i<l1;i++) arr1[i]= arr[st+i];
        for(int j=0;j<l2;j++) arr2[j]= arr[mid+1+j];
        int idx1=0;
        int idx2=0;
        int idx=st;
        while(idx1<l1&&idx2<l2){
            if(arr1[idx1]<arr2[idx2]) arr[idx++]=arr1[idx1++];
            else arr[idx++]=arr2[idx2++];
        }
        while(idx1<l1) arr[idx++]=arr1[idx1++];
        while(idx2<l2) arr[idx++]=arr2[idx2++];
    }
    static void  mergeSort(int [] arr, int st,int end)
    {   if(st>=end) return ;
        int mid=(st+end)/2;
        mergeSort(arr,st,mid);
        mergeSort(arr,mid+1,end);
        merge(arr,st,mid,end);
    }
    static void bubbleSort(int [] arr)
    {  int n = arr.length;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n-i-1;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
    static void selectionSort(int [] arr)
    {
        int n=arr.length;
        int idx=0;
        for(int i=0;i<n-1;i++){
            idx=i;
            for(int j=i;j<=n-1;j++){
                if(arr[idx]>arr[j]) idx=j;
            }
            //swap lowest value to current index value
            int temp=arr[idx];
            arr[idx]=arr[i];
            arr[i]=temp;
        }
    }
    static void insertionSort(int[] arr){
        int n =arr.length;
        for(int i=1;i<=n-1;i++)// we are including last index because we are checking evry element.
        {   int j=i;
            while(j>0&&arr[j]<arr[j-1])
            {
                int temp=arr[j];
                arr[j]=arr[j-1];
                arr[j-1]=temp;
                j--;
            }

        }

    }
}
