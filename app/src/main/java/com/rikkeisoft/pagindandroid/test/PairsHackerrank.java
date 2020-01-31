package com.rikkeisoft.pagindandroid.test;

import java.util.Scanner;

public class PairsHackerrank {
    public static void main(String[] args) {
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
//        for (int i = 0; i < n - 1; i++) {
//            int j = 1;
//            if (Math.abs(a[i] - a[j]) == k) {
//                System.out.println("i:..." + a[i] + "...j:..." + a[j]);
//                count++;
//            }
//        }
        int i = 0;
        int j = 1;
        while (i < n - 1 && j < n) {
            if (Math.abs(a[i] - a[j]) == k) {
                count++;
                j++;
            } else if (j == n - 1) {
                i++;
                j = i + 1;
            } else j++;
        }
        System.out.println(count);
    }
}
