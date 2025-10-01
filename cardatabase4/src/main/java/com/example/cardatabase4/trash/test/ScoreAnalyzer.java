package com.example.cardatabase4.trash.test;

public class ScoreAnalyzer {
    public int countPassingStudents(int[] scores, int passingScore) {
        int passingStudentsCount = 0;
        // TODO: for문을 사용하여 scores 배열을 순회하고,
        // if문을 사용하여 passingScore 이상인 학생 수를 세는 코드를 작성하시오.
        for(int score : scores) {
            if(score>=passingScore) passingStudentsCount++;
        }
        return passingStudentsCount;
    }

    public static void main(String[] args) {
        ScoreAnalyzer analyzer = new ScoreAnalyzer();
        int[] scores = {88, 95, 72, 68, 77, 91, 60};
        int passingScore = 75;

        // TODO: countPassingStudents 메서드를 호출하고 결과를 출력하시오.
        int passingStudentsCount = analyzer.countPassingStudents(scores,passingScore);
        System.out.println(passingScore + "점 이상으로 통과한 학생은 " + passingStudentsCount +"입니다.");
    }
}