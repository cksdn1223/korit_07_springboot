package com.example.cardatabase4.trash.test;

// TODO: Exception을 상속받는 OutOfStockException 클래스를 작성하시오.
class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}

class Product1 {
    private String name;
    private int stock;

    public Product1(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public void decreaseStock(int quantity) throws OutOfStockException {
        // TODO: quantity가 stock보다 크면 OutOfStockException을 발생시키는 코드를 작성하시오.
        // 그렇지 않으면 stock을 quantity만큼 감소시킵니다.
        if(quantity > this.stock) throw new OutOfStockException("재고부족:" + quantity + "개 주문 불가. ");
        this.stock-=quantity;
    }

    public int getStock() {
        return stock;
    }
}
//main 메서드에서 try-catch 블록을 사용하여 decreaseStock 메서드를 호출하고, OutOfStockException이 발생했을 때 적절한 메시지를 "실행 예"와 같이 출력하세요.
public class InventoryManager {
    public static void main(String[] args) {
        Product1 laptop = new Product1("노트북", 10);
        int orderQuantity = 15;

        // TODO: try-catch 블록을 사용하여 laptop.decreaseStock()을 호출하고,
        // OutOfStockException을 처리하여 실행 예와 같이 출력하시오.
        try{
            laptop.decreaseStock(orderQuantity);
        } catch (OutOfStockException e) {
            System.out.print(e.getMessage());
        } finally {
            System.out.println("현재 재고: " + laptop.getStock() + "개.");
        }
    }
}
