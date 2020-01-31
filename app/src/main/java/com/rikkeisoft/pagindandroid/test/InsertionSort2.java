package com.rikkeisoft.pagindandroid.test;

import java.util.Scanner;

public class InsertionSort2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;
            else
                a[i] = scanner.nextInt();
        }

        int key = a[n - 1];

        for (int j = n - 2; j >= 0; j--) {
            if (key < a[j]) {
                a[j + 1] = a[j];
                for (int m = 0; m < n; m++) {
                    System.out.print(a[m] + " ");
                    if (m == n - 1) {
                        System.out.println();
                    }
                }
            } else {
                a[j + 1] = key;
                for (int m = 0; m < n; m++) {
                    System.out.print(a[m] + " ");
                    if (m == n - 1) {
                        System.out.println();
                    }
                }
                break;
            }
        }
        if (key < a[0]) {
            a[0] = key;
            for (int m = 0; m < n; m++) {
                System.out.print(a[m] + " ");
                if (m == n - 1) {
                    System.out.println();
                }
            }
        }
    }
}
