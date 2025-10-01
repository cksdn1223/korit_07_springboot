package com.example.cardatabase4.trash.test;


class Product {
    private String name;
    // TODO: 생성된 상품 수를 추적하기 위한 private static int 필드를 선언하시오.
    private static int totalProducts = 0;
    public Product(String name) {
        this.name = name;
        // TODO: 객체가 생성될 때마다 static 필드의 값을 1 증가시키시오.
        totalProducts++;
    }

    // TODO: static 필드의 값을 반환하는 public static 메서드를 작성하시오.
    public static int getTotalProducts() {
        return totalProducts;
    }
}

public class ProductCounter {
    public static void main(String[] args) {
        System.out.println("초기 상품 수: " + Product.getTotalProducts());

        new Product("사과");
        new Product("바나나");
        new Product("오렌지");

        // TODO: 총 상품 수를 static 메서드를 통해 출력하시오.
        System.out.println("총 상품 수: " + Product.getTotalProducts());
    }
}