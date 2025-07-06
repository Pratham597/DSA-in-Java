package Strings;

import java.util.Scanner;

public class RabinKarp {
    static int mod = (int) (1e9 + 7);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String t = sc.next();
        int n = s.length(), m = t.length();
        long[] hash = new long[n + 1];
        long[] modinv = new long[n + 1];
        // Considering that len(t)<=len(s).


        // Precomputing the hash
        long ans = getHash(t);
        getHash(s, hash, modinv);




        // Looping  the sliding window of len(t) over the hash array of string t.
        for (int i = m, j = 0; i <= n; i++, j++) {
            long pre = ((hash[i] - hash[j]) * modinv[j]) % mod;
//            System.out.println(pre+" "+ans);
            if (pre == ans) {
                System.out.println((j + 1) + " " + (j + m));
            }
        }
    }

    static long getHash(String s) {
        long mul = 31;
        long pow = 1;
        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int pos = s.charAt(i) - 'a' + 1;
            ans = (ans + (pow * pos) % mod) % mod;
            pow = (pow * mul) % mod;
        }
        return ans;
    }

    static void getHash(String s, long[] hash, long[] modinv) {
        long mul = 31;
        long pow = 1;
        modinv[0] = modinv(pow);
        long ans = 0;
        for (int i = 1; i <= s.length(); i++) {
            int pos = s.charAt(i - 1) - 'a' + 1;
            ans = (ans + (pow * pos) % mod) % mod;
            pow = (pow * mul) % mod;
            modinv[i] = modinv(pow);
            hash[i] = ans;
        }
    }

    static long modinv(long n) {
        return pow(n, mod - 2);
    }

    static long pow(long a, long b) {
        long result = 1;
        a = a % mod;

        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a) % mod;
            }
            a = (a * a) % mod;
            b = b / 2;
        }
        return result % mod;
    }
}
