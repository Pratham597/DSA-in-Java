import java.util.Scanner;

public class problem1 {
    static long helper(long sum,long n){
        if(n>sum) return -1;
        long st=1;
        long end=sum;
        while(st<=end){
            long mid=st+(end-st)/2;
            long ans=(n*(n+1)*mid)/2;
            if(ans==sum) return mid;
            if(ans>sum) end=mid-1;
            else st=mid+1;
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt();
        for (int i = 0; i < k; i++) {
            int n = sc.nextInt();
            long sum = sc.nextLong();
            int p = sc.nextInt();
            long ans = helper(sum, n);
            if (ans == -1) System.out.println(ans);
            else System.out.println(ans * p);
        }
        sc.close();
    }
}
