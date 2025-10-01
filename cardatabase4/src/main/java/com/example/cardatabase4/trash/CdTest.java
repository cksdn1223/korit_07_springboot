package com.example.cardatabase4.trash;

import java.util.*;

public class CdTest {
    public static void main(String[] args) {
        String[] records = {
                "05:34 5961 IN",
                "06:00 0000 IN",
                "06:34 0000 OUT",
                "07:59 5961 OUT",
                "07:59 0148 IN",
                "18:59 0000 IN",
                "19:09 0148 OUT",
                "22:59 5961 IN",
                "23:00 5961 OUT"
        };
//        기본 시간(분)	기본 요금(원)	단위 시간(분)	단위 요금(원)
        int[] fees = {180, 5000, 10, 600};
        Map<String, Map<String, Integer>> info = new LinkedHashMap<>();
        for(String record : records){
            Map<String, Integer> detail = new HashMap<>();
            String[] recordArr = record.split(" ");
            int minutes = Integer.parseInt(recordArr[0].split(":")[0])*60+Integer.parseInt(recordArr[0].split(":")[1]);
            String carNumber = recordArr[1];
            if(recordArr[2].equals("IN")){
                detail.put("time", info.getOrDefault(carNumber,new HashMap<>()).getOrDefault("time",0)-minutes);
                detail.put("inOut",0);
                info.put(carNumber,detail);
            } else {
                detail.put("time",info.getOrDefault(carNumber,new HashMap<>()).getOrDefault("time",0)+minutes);
                detail.put("inOut",1);
                info.put(carNumber,detail);
            }
        }
        for(Map<String, Integer> detail : info.values()){
            if(detail.get("inOut")==0) detail.put("time",detail.getOrDefault("time",0)+(23*60+59));
            if(detail.get("time") <= fees[0]) System.out.println(fees[1]);
            else System.out.println(fees[1]+ Math.ceil((double) (detail.get("time") - fees[0]) /fees[2]) * fees[3]);
        }
        System.out.println(info);
//      이제 이걸 더한걸 자동차번호 와 요금으로 정리하고 번호 작은 순서대로 배열 리턴
    }
}
