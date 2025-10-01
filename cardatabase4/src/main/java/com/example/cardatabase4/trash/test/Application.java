package com.example.cardatabase4.trash.test;

class SettingsManager {
    // TODO: private static 필드로 instance를 선언하시오.
    private static SettingsManager instance;
    // TODO: 생성자를 private으로 선언하시오.
    private SettingsManager() {}
    // TODO: public static getInstance() 메서드를 구현하시오.
    // instance가 null일 때만 객체를 생성하고, 항상 같은 instance를 반환해야 합니다.
    public static SettingsManager getInstance() {
        if(instance == null) instance = new SettingsManager();
        return instance;
    }
}

public class Application {
    public static void main(String[] args) {
        // TODO: SettingsManager의 인스턴스를 두 번 가져와서 두 변수에 할당하시오.
        SettingsManager instance1 = SettingsManager.getInstance();
        SettingsManager instance2 = SettingsManager.getInstance();
        // TODO: 두 인스턴스가 같은지(==) 비교하여 결과를 출력하시오.
        // 예: "두 인스턴스는 동일합니다." 또는 "두 인스턴스는 다릅니다."
        if(instance1 == instance2) System.out.println("두 인스턴스는 동일합니다.");
        else System.out.println("두 인스턴스는 다릅니다.");
    }
}