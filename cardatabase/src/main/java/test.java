import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class test {
    public static int correctCount(int[] man, int[] answer){
        int correct = 0;
        int index = 0;
        for(int i = 0 ; i < answer.length ; i++){
            if(index >= man.length) index %= man.length;
            if(answer[i] == man[index++]) correct++;
        }
        return correct;
    }
    public static void main(String[] args) {
        int[] answer = {1,3,2,4,2};
        int[] man1 = {1, 2, 3, 4, 5};                // 5
        int[] man2 = {2, 1, 2, 3, 2, 4, 2, 5};       // 8
        int[] man3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}; // 10

//        List<Integer> list = new ArrayList<>();
//        int manFrist = correctCount(man1,answer);
//        int manSecond = correctCount(man2,answer);
//        int manThird = correctCount(man3,answer);
//        int max = manFirst;
//        for(int i=0 ; i < 3 ; i++){
//            if(correctCount(man2,answer) > max){
//                max = correctCount()
//            }
//        }
//        correctCount(man2,answer) correctCount(man3,answer)



    }
}
