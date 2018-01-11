package com.perkins.oes.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.perkins.oes.dao.QuestionDao;
import com.perkins.oes.entity.Question;

public class QuestionDaoImpl extends SqlSessionDaoSupport implements QuestionDao {

    private final String className = Question.class.getName();

    @Override
    public List<Question> findQuestionPage(String searchText, String sort, int offset, int pagesize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchText", searchText);
        params.put("sort", sort);
        params.put("offset", offset);
        params.put("pagesize", pagesize);

        List<Question> questions = getSqlSession().selectList(className + ".getQuestionAll", params);

        return questions;
    }

    @Override
    public Question findById(final int id) {

        Question question = getSqlSession().selectOne(className + ".findById", id);
        return question;
    }

    @Override
    public void insert(final Question question) {
        getSqlSession().insert(className + ".insert", question);
    }

    @Override
    public void deleteQuestion(int[] arr) {
        getSqlSession().delete(className + ".delete", arr);
    }

    @Override
    public void updateQuestion(final Question question) {
        getSqlSession().update(className + ".update", question);
    }

    @Override
    public int getCount(String searchText) {
        return getSqlSession().selectOne(className + ".getCount", searchText);
    }

    @Override
    public Question getQuestionID() {
        Question question = getSqlSession().selectOne(className + ".checkQuestionID");
        return question;
    }

}
