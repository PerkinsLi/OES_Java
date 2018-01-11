package com.perkins.oes.entity;

import java.io.Serializable;
import java.util.Date;

public class Question implements Serializable {
    private static final long serialVersionUID = -3457424276642825000L;
    private int id;
    private String title;
    private String a;
    private String b;
    private String c;
    private String d;
    private String rightAnswer;
    private Date createDate;
    private Date updateDate;
    private String status;
    private String questionID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", title=" + title + ", a=" + a + ", b=" + b + ", c=" + c + ", d=" + d
                + ", rightAnswer=" + rightAnswer + ", createDate=" + createDate + ", updateDate=" + updateDate
                + ", status=" + status + ", questionID=" + questionID + "]";
    }

}
