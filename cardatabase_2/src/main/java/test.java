import java.util.*;

public class test {

    public static void main(String[] args) {
        int[][] man = {{1,2,3,4,5},{2,1,2,3,2,4,2,5},{3,3,1,1,2,2,4,4,5,5}};
        int[] answers = {1,2,3,4,5};
        int[] score = new int[3];
        for(int i = 0 ; i < answers.length ; i++){
            for(int j = 0 ; j < man.length ; j++){
                if (answers[i] == man[j][i % man[j].length]) score[j]++;
            }
        }
        int max = Arrays.stream(score).max().getAsInt();
        int size = 0;
        for(int i = 0 ; i < score.length ; i++){
            if(score[i] == max) size++;
        }


    }
}
