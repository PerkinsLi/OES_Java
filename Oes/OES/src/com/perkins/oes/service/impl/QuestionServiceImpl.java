package com.perkins.oes.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.perkins.oes.dao.QuestionDao;
import com.perkins.oes.entity.Question;
import com.perkins.oes.exception.ServiceException;
import com.perkins.oes.service.QuestionService;
import com.perkins.oes.util.PageUtil;
import com.perkins.oes.util.StringUtil;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public String getQuestionID() {
        int a = (int) ((Math.random() * 9 + 1) * 100000);
        StringBuffer buffer = new StringBuffer();
        buffer.append("Q");
        buffer.append(a);
        String qid = buffer.toString();
        Question question = questionDao.getQuestionID(qid);

        if (question == null) {
            return qid;
        } else {
            getQuestionID();
        }
        return null;
    }

    @Override
    public int insert(Question question) {
        int id = questionDao.insert(question);
        return id;

    }

    @Override
    public int update(Question question) {
        int id = questionDao.updateQuestion(question);
        System.out.println("修改：" + id);
        return id;

    }

    @Override
    public List<Question> findList(String searchText, String sort, String pageSize, String currentPage,
            PageUtil pageUtil) {
        int page = 0;
        int offset = 0;
        int pagesize = 0;

        List<Question> questions = new ArrayList<Question>();
        pageUtil.setTotalCount(questionDao.getCount(searchText));

        if (StringUtil.IsEmpty(sort)) {
            sort = "ASC";
            pageUtil.setSort(sort);
        } else {
            pageUtil.setSort(sort);
        }

        if (StringUtil.IsEmpty(pageSize)) {
            pagesize = pageUtil.getPageSize();
        } else {
            int size = Integer.parseInt(pageSize);
            pageUtil.setPageSize(size);
            pagesize = pageUtil.getPageSize();
        }

        if (StringUtil.IsEmpty(currentPage)) {
            page = pageUtil.getCurrentPage();
        } else {
            page = Integer.parseInt(currentPage);
        }
        if (page >= pageUtil.getPageCount()) {
            pageUtil.setCurrentPage(pageUtil.getPageCount());
        } else if (page <= 1) {
            pageUtil.setCurrentPage(1);
        } else {
            pageUtil.setCurrentPage(page);
        }

        offset = pageUtil.getOffset();
        questions = questionDao.findQuestionPage(searchText, sort, offset, pagesize);
        return questions;

    }

    @Override
    public String deleteQuestion(String arrays) throws ServiceException {

        if (StringUtil.IsEmpty(arrays)) {
            throw new ServiceException("You shoud choose one question.");
        }

        String[] array = arrays.split(",");
        int[] arr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (!"".equals(array[i])) {
                arr[i] = Integer.parseInt(array[i]);
            }
        }

        questionDao.deleteQuestion(arr);
        String successful = "Delete success";
        return successful;

    }

    @Override
    public Question findById(String qid) throws ServiceException {
        if (StringUtil.IsEmpty(qid)) {
            throw new ServiceException("id is null!");
        }

        int id = Integer.parseInt(qid);
        return questionDao.findById(id);

    }

}
