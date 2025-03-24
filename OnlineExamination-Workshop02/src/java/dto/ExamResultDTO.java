/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author huudu
 */
public class ExamResultDTO {

    private int resultId;
    private int examId;
    private String student;
    private int score;

    public ExamResultDTO() {
    }

    public ExamResultDTO(int resultId, int examId, String student, int score) {
        this.resultId = resultId;
        this.examId = examId;
        this.student = student;
        this.score = score;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
