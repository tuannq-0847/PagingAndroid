package com.rikkeisoft.pagindandroid.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
        Map<Integer, Integer> mp1 = new HashMap<>();
        Map<Integer, Integer> mp2 = new HashMap<>();
        Set<Map.Entry<Integer,Integer>> st1 = mp1.entrySet();
        Set<Map.Entry<Integer,Integer>> st2 = mp2.entrySet();
        int n = new Scanner(System.in).nextInt();
        int[] arr = new int[n];
        int[] inc = new int[n];
        int[] dec = new int[n];
        boolean[] used = new boolean[n];
        Arrays.fill(used, Boolean.FALSE);
        for (int i = 0; i < n; i++) {
            arr[i] = new Scanner(System.in).nextInt();
            inc[i] = arr[i];
            dec[i] = arr[i];
            mp1.put(inc[i], i);
            mp2.put(dec[i], i);
        }
        //sort increase
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (inc[j] > inc[j + 1]) {
                    int t = inc[j];
                    inc[j] = inc[j + 1];
                    inc[j + 1] = t;
                }
            }
        }

        //sort decrease
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (dec[j] < dec[j + 1]) {
                    int t = dec[j];
                    dec[j] = dec[j + 1];
                    dec[j + 1] = t;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            if (!used[j]) {
                ans++;
                used[j] = true;
                //  j = mp.get(i).intValue();
            }
        }
   //     System.out.println(ans);
    }
}
