package com.example.cardatabase4.trash.test;


public class DivisionCalculator {
    public static void divide(int a, int b) {
        System.out.println("나눗셈 결과: " + (a / b));
    }

    public static void main(String[] args) {
        // TODO: try-catch-finally 블록을 사용하여 10을 0으로 나누는 코드를 작성하고 예외를 처리하시오.
        // divide(10, 0)을 호출해야 합니다.
        try{
            divide(10, 0);
        }catch(ArithmeticException e){
            System.out.println("0으로 나눌 수 없습니다.");
        }finally{
            System.out.println("계산을 종료합니다.");
        }

    }
}