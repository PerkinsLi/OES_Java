package com.perkins.oes.controller;

import java.util.List;
import java.util.Map;

import com.perkins.oes.Constants;
import com.perkins.oes.common.ModelAndView;
import com.perkins.oes.entity.Question;
import com.perkins.oes.exception.ServiceException;
import com.perkins.oes.service.QuestionService;
import com.perkins.oes.util.PageUtil;

public class QuestionController {
    private QuestionService questionService;

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public ModelAndView showList(Map<String, String> request, Map<String, Object> session) {
        String currentPage = request.get("currentPage");
        String pageSize = request.get("pagesize");
        String sort = request.get("sort");
        String searchText = request.get("searchText");

        ModelAndView modelAndView = new ModelAndView();
        PageUtil pageUtil = new PageUtil();
        List<Question> questions = questionService.findList(searchText, sort, pageSize, currentPage, pageUtil);

        modelAndView.addObject("questions", questions);
        modelAndView.addObject("page", pageUtil);
        modelAndView.addObject("searchText", searchText);
        modelAndView.setView("/WEB-INF/jsp/oes/contentadmin/main.jsp");

        return modelAndView;
    }

    public ModelAndView questionInformation(Map<String, String> request, Map<String, Object> session) {
        String qid = request.get("qid");

        ModelAndView modelAndView = new ModelAndView();
        try {
            Question question = new Question();
            question = questionService.findById(qid);
            modelAndView.addObject("question", question);
            modelAndView.setView("/WEB-INF/jsp/oes/contentadmin/information.jsp");
        } catch (ServiceException e) {
            e.printStackTrace();
            String errMessage = e.getMessage();
            modelAndView.addObject(Constants.ERR_MESSAGE, errMessage);
            modelAndView.setRedirect(true);
            modelAndView.setView("./showListQuestion.action");
        }

        return modelAndView;
    }

    public ModelAndView toSave(Map<String, String> request, Map<String, Object> session) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        String buffer = questionService.getQuestionID();
        modelAndView.addObject("questionID", buffer);
        modelAndView.setView("/WEB-INF/jsp/oes/contentadmin/create_question.jsp");

        return modelAndView;
    }

    public ModelAndView save(Map<String, String> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView();
        String questionID = request.get("questionID");
        String title = request.get("title");
        String a = request.get("a");
        String b = request.get("b");
        String c = request.get("c");
        String d = request.get("d");
        String rightAnswer = request.get("rightAnswer");
        Question question = new Question();
        question.setQuestionID(questionID);
        question.setTitle(title);
        question.setA(a);
        question.setB(b);
        question.setC(c);
        question.setD(d);
        question.setRightAnswer(rightAnswer);

        questionService.insert(question);
        modelAndView.setRedirect(true);
        modelAndView.setView("./showListQuestion.action");

        return modelAndView;
    }

    public ModelAndView delete(Map<String, String> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView();
        String array = request.get("arrays");

        try {
            String message = questionService.deleteQuestion(array);
            modelAndView.addObject(Constants.ERR_MESSAGE, message);
            modelAndView.setRedirect(true);
            modelAndView.setView("./showListQuestion.action");
        } catch (ServiceException e) {
            e.printStackTrace();
            modelAndView.addObject(Constants.ERR_MESSAGE, e.getMessage());
            modelAndView.setView("/WEB-INF/jsp/oes/contentadmin/main.jsp");
        }

        return modelAndView;
    }

    public ModelAndView toEdit(Map<String, String> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView();
        String qid = request.get("qid");

        try {
            Question question = new Question();
            question = questionService.findById(qid);
            modelAndView.addObject("question", question);
            modelAndView.setView("/WEB-INF/jsp/oes/contentadmin/edit_question.jsp");
        } catch (ServiceException e) {
            e.printStackTrace();
            String errMessage = e.getMessage();
            modelAndView.addObject(Constants.ERR_MESSAGE, errMessage);
            modelAndView.setRedirect(true);
            modelAndView.setView("./showListQuestion.action");
        }

        return modelAndView;
    }

    public ModelAndView edit(Map<String, String> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView();
        String questionID = request.get("questionID");
        String title = request.get("title");
        String a = request.get("a");
        String b = request.get("b");
        String c = request.get("c");
        String d = request.get("d");
        String rightAnswer = request.get("rightAnswer");
        int id = Integer.parseInt(request.get("id"));

        Question question = new Question();
        question.setQuestionID(questionID);
        question.setTitle(title);
        question.setA(a);
        question.setB(b);
        question.setC(c);
        question.setD(d);
        question.setRightAnswer(rightAnswer);
        question.setId(id);

        questionService.update(question);
        modelAndView.setRedirect(true);
        modelAndView.setView("./showListQuestion.action");

        return modelAndView;
    }

}
