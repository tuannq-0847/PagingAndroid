package com.rikkeisoft.pagindandroid.test;

import java.util.Scanner;

public class InsertionTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;
            else
                a[i] = scanner.nextInt();
        }
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            if (j >= 0) {
                for (int k = 0; k <= j; k++) {
                    if (a[k] > a[i]) {
                        int t = a[k];
                        a[k] = a[i];
                        a[i] = t;
                    }
                }
            }
            for (int m = 0; m < n; m++) {
                System.out.print(a[m] + " ");
                if (m == n - 1) {
                    System.out.printf("\n");
                }
            }
        }
//
//        for (int i = 0; i < n; i++) {
//            System.out.print(a[i] + " ");
//        }
    }
}
