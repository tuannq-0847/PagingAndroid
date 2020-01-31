package com.rikkeisoft.pagindandroid.test;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SherlockAnagrams {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        String a[] = new String[q];
        Map<String, Integer> mp = new HashMap<>();
        for (int i = 0; i < q; i++) {
            if (!sc.hasNext()) break;
            else
                a[i] = sc.next();
        }
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < a[i].length(); j++) {
                for (int k = j + 1; k <= a[i].length(); k++) {
                    String key = sortResult(a[i].substring(j, k));
                    if (!mp.containsKey(key)) {
                        mp.put(key, 0);
                    }
                    mp.put(key, mp.get(key) + 1);
                }
            }
            System.out.println(mp.values().stream().mapToInt(ans -> ans * (ans - 1) / 2).sum());
            mp.clear();
        }
        sc.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static String sortResult(String substring) {
        return substring.chars().sorted().mapToObj(characters -> String.valueOf((char) characters)).collect(Collectors.joining());
    }
}
