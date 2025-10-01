package com.example.cardatabase4.trash.test;

import java.util.Scanner;
class VendingMachine {
    public void selectDrink(int choice) {
        // TODO: switch문을 사용하여 choice 값에 따라 음료 이름과 가격을 출력하시오.
        // 1: "콜라 - 1,000원", 2: "사이다 - 1,200원", 3: "물 - 800원"
        // 그 외의 번호는 "잘못된 번호입니다." 출력
        switch (choice){
            case 1:
                System.out.println("콜라 - 1,000원");
                break;
            case 2:
                System.out.println("사이다 - 1,200원");
                break;
            case 3:
                System.out.println("물 - 800원");
                break;
            default:
                System.out.println("잘못된 번호입니다.");
        }
    }
}

public class Simulator {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- 자판기 ---");
        System.out.println("1. 콜라 | 2. 사이다 | 3. 물");
        System.out.print("음료 번호를 선택하세요: ");

        int choice = scanner.nextInt();
        machine.selectDrink(choice);

        scanner.close();
    }
}