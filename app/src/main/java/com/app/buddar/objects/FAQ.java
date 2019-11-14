package com.app.buddar.objects;

/**
 * FAQ Item OBJ Class
 * Class by: Luis Felipe Alvarez Sanchez
 */
public class FAQ {
    private String question;
    private String answer;
    public FAQ(){

    }
    public FAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
