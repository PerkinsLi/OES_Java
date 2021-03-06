package com.perkins.oes.service;

import java.util.List;

import com.perkins.oes.entity.Question;
import com.perkins.oes.exception.ServiceException;
import com.perkins.oes.util.PageUtil;

public interface QuestionService {

    String getQuestionID();

    void insert(Question question);

    void update(Question question);

    List<Question> findList(String searchText, String sort, String pageSize, String currentPage,
            PageUtil pageUtil);

    String deleteQuestion(String arrays) throws ServiceException;

    Question findById(String qid) throws ServiceException;

}
