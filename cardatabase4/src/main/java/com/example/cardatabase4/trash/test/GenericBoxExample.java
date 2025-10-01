package com.example.cardatabase4.trash.test;

// TODO: 제네릭 타입 T를 사용하는 Box 클래스를 작성하시오.
// 필드: private T content
// 생성자: content를 인자로 받아 초기화
// 메서드: public T getContent()
class Box<T> {
    private T content;

    public Box(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
public class GenericBoxExample {
    public static void main(String[] args) {
        // TODO: "Hello World" 문자열을 담는 Box<String> 객체를 생성하고 내용물을 출력하시오.
        Box<String> stringBox = new Box<>("Hello world");
        System.out.println("문자열 상자 내용: " + stringBox.getContent());
        // TODO: 숫자 123을 담는 Box<Integer> 객체를 생성하고 내용물을 출력하시오.
        Box<Integer> integerBox = new Box<>(123);
        System.out.println("정수 상자 내용: " + integerBox.getContent());
    }
}