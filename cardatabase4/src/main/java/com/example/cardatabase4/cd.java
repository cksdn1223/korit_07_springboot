package com.example.cardatabase4;

import java.util.*;

public class cd {
    public static void main(String[] args) {
        int[] order = {4, 3, 1, 2, 5};
//        int[] order = {5,4,3,2,1};
        Stack<Integer> stack = new Stack<>();
        int count = 1;
        for(int i = 0 ; i < order.length ; i++){
            if(order[count-1]==i+1) stack.add(count++);
            else {
                stack.add();
            }
        }
        System.out.println(stack);

    }
}
