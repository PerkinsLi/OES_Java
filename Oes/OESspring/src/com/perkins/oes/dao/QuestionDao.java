package com.perkins.oes.dao;

import java.util.List;

import com.perkins.oes.entity.Question;

public interface QuestionDao {

    Question getQuestionID();

    void insert(final Question question);

    int getCount(String searchText);

    List<Question> findQuestionPage(String searchText, String sort, int offset, int pagesize);

    void deleteQuestion(int[] arr);

    Question findById(final int id);

    void updateQuestion(final Question question);

}
