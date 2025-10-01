package com.example.cardatabase4.trash.test;

import java.util.HashSet;
import java.util.Set;

public class MeetingAttendees {
    public static void main(String[] args) {
        Set<String> attendees = new HashSet<>();

        // TODO: "김철수", "이영희", "박지성", "김철수"를 attendees에 추가하시오.
        attendees.add("김철수");
        attendees.add("이영희");
        attendees.add("박지성");
        attendees.add("김철수");
        // TODO: "박지성"이 명단에 있는지 확인하고, "박지성 참석 여부: [true/false]" 형식으로 출력하시오.
        System.out.println("박지성 참석 여부: " + attendees.contains("박지성"));
        // TODO: 최종 참석자 수를 "최종 참석자 수: [숫자]" 형식으로 출력하시오.
        System.out.println("최종 참석자 수: " + attendees.size());
        System.out.println("전체 명단: " + attendees);
    }
}
//박지성 참석 여부: true
//최종 참석자 수: 3
//전체 명단: [박지성, 김철수, 이영희]