package com.example.cardatabase4.trash.test;
//String 타입의 배열 fruits를 선언하고 "사과", "바나나", "딸기", "포도"로 초기화합니다.
//향상된 for문을 사용하여 fruits 배열의 각 요소를 순서대로 "맛있는 [과일이름]" 형식으로 출력합니다.
public class FruitPrinter {
    public static void main(String[] args) {
        String[] fruits = {"사과", "바나나", "딸기", "포도"};

        // TODO: 향상된 for문을 사용하여 배열의 모든 과일 이름을 지정된 형식으로 출력하시오.
        for(String fruit : fruits) System.out.println("맛있는 " + fruit);
    }
}
//맛있는 사과
//맛있는 바나나
//맛있는 딸기
//맛있는 포도