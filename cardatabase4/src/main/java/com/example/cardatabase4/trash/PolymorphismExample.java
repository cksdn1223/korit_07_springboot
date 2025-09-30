package com.example.cardatabase4.trash;

class Animal {
    public void makeSound() {
        System.out.println("동물이 소리를 냅니다.");
    }
}
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("멍멍");
    }
    public void fetch() {
        System.out.println("공을 가져옵니다.");
    }
}
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("야옹");
    }
}
public class PolymorphismExample {
    public static void main(String[] args) {
        Animal[] animals = new Animal[2];
        Animal dog = new Dog();
        Animal cat = new Cat();
        animals[0] = dog;
        animals[1] = cat;
        for(Animal animal : animals) {
            animal.makeSound();
            if(animal instanceof Dog){
                ((Dog) animal).fetch();
            }
        }

    }
}
