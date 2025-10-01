package com.example.cardatabase4.trash.test;

class Animal {
    public void makeSound() {
        System.out.println("동물이 소리를 냅니다.");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        /* TODO: "멍멍!" 출력 */
        System.out.println("멍멍!");
    }
    public void fetch() {
        /* TODO: "공을 가져옵니다." 출력 */
        System.out.println("공을 가져옵니다.");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        /* TODO: "야옹~" 출력 */
        System.out.println("야옹~");
    }
}

public class PolymorphismExample {
    public static void main(String[] args) {
        Animal[] animals = new Animal[2];
        // TODO: animals 배열의 0번 인덱스에 Dog 객체를, 1번 인덱스에 Cat 객체를 할당하시오.
        animals[0] = new Dog();
        animals[1] = new Cat();
        // TODO: 반복문을 사용하여 모든 동물의 makeSound() 메서드를 호출하시오.
        // 추가로, instanceof를 사용해 Dog일 경우 다운캐스팅하여 fetch() 메서드를 호출하시오.
        for(Animal animal : animals) {
            animal.makeSound();
            if(animal instanceof Dog) ((Dog) animal).fetch();
        }
    }
}