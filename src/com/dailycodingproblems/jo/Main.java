/*
A non-empty array A consisting of N non-negative integers is given. Its binarian is defined as :
    binarian(A) = pow2(A[0]) + pow2(A[1]) + pow2(A[n-1])
    pow2(K) = 2^k

For example, the binarian for array A such that :
    A[0]=1
    A[1]=5
    A[2]=4

equals 50 :

    binarian(A) = pow2(A[0]) + pow2(A[1]) + pow2(A[2])
                = pow2(1) + pow2(5) + pow2(4)
                = 2 + 32 + 16
                = 50

Write a function solution(int[] A) that, given an array A consisting of N non-negative integers, returns the length of
the shortest array that has the same binarian as array A.

- N is an integer within the range [1..100000]
- Each element of array A is an integer within the range [0..10000]
*/

package com.dailycodingproblems.jo;

import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        /*int[] test = new int[100000];
        int pluzun = 10000;
        for(int i=0;i<test.length;i++) {
            test[i] = pluzun;
            pluzun ++;
        }*/
        int[] test = {1,0,2,0,0,2,10000,10000,4,4,10000,10000};
        System.out.println(solution(test));
    }

    public static int solution(int[] A) {
        TreeMap<Integer, Integer> repetitions = new TreeMap<>();
        int lengthSolution = 0;
        for(int i=0;i<A.length;i++) {
            int item = A[i];
            if (repetitions.containsKey(item))
                repetitions.put(item, repetitions.get(item) + 1);
            else
                repetitions.put(item, 1);
        }
        repetitions = factorize(repetitions,repetitions.firstEntry());
        for(Map.Entry<Integer,Integer> entry : repetitions.entrySet()) {
            int value = entry.getValue();
            if(value == 1)
                lengthSolution ++;
        }
        return lengthSolution;
    }

    public static TreeMap<Integer, Integer> factorize(TreeMap<Integer, Integer> remaining, Map.Entry<Integer, Integer> entry) {
        int key = entry.getKey();
        int value = entry.getValue();
        if (value > 1 && value % 2 == 0) {
            if (remaining.containsKey(key + 1)) {
                remaining.put(key + 1, remaining.get(key + 1) + value / 2);
                remaining.put(key,0);
            }
            else {
                remaining.put(key + 1, value / 2);
                remaining.put(key,0);
            }
        } else if (value > 1 && value % 2 == 1) {
            if (remaining.containsKey(key + 1)) {
                remaining.put(key + 1, remaining.get(key + 1) + (value - 1) / 2);
                remaining.put(key,1);
            }
            else {
                remaining.put(key + 1, (value - 1) / 2);
                remaining.put(key, 1);
            }
        }
            while(remaining.higherEntry(key) != null) {
                if(remaining.higherEntry(key).getValue() > 1)
                    factorize(remaining,remaining.higherEntry(key));
                key++;
            }
        return remaining;
    }
}
