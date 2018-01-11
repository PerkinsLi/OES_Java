package com.perkins.oes.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.perkins.oes.Constants;
import com.perkins.oes.entity.Question;
import com.perkins.oes.exception.ServiceException;
import com.perkins.oes.service.QuestionService;
import com.perkins.oes.util.PageUtil;

@Controller
@RequestMapping("/page/question")
public class QuestionController extends BaseController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/show-list-question")
    public ModelAndView showList(String currentPage, String pagesize, String sort,
            String searchText) {
        ModelAndView mod = new ModelAndView();
        PageUtil pageUtil = new PageUtil();

        List<Question> questions = questionService.findList(searchText, sort, pagesize, currentPage, pageUtil);

        mod.addObject("questions", questions);
        mod.addObject("page", pageUtil);
        mod.addObject("searchText", searchText);
        mod.setViewName(Constants.PATH_SHOW_CONTENTADMIN_MAIN);
        return mod;
    }

    @RequestMapping("/question-information")
    public ModelAndView questionInformation(String qid) {
        ModelAndView mod = new ModelAndView();
        try {
            Question question = new Question();
            question = questionService.findById(qid);
            mod.addObject("question", question);
            mod.setViewName(Constants.PATH_SHOW_INFORMATION_QUESTION);
        } catch (ServiceException e) {
            e.printStackTrace();
            String errMessage = e.getMessage();
            mod.addObject(Constants.ERR_MESSAGE, errMessage);
            logger.error(errMessage);
            mod.setView(getRedirectView(Constants.PATH_QUESTION_SHOW_lIST));

        }
        return mod;
    }

    @RequestMapping("/to-save")
    public ModelAndView toSave() {
        ModelAndView mod = new ModelAndView();
        String buffer = questionService.getQuestionID();
        mod.addObject("questionID", buffer);
        mod.setViewName(Constants.PATH_SHOW_CREATE_QUESTION);
        return mod;
    }

    @RequestMapping("/save")
    public ModelAndView save(Question question) {
        ModelAndView mod = new ModelAndView();
        questionService.insert(question);
        mod.setView(getRedirectView(Constants.PATH_QUESTION_SHOW_lIST));
        return mod;
    }

    @RequestMapping("/delete-question")
    @ResponseBody
    public String delete(String arrays) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String success = "{'success' : 'success'}";
        String SUCCESS = mapper.writeValueAsString(success);
        String error = "{'error' : 'error'}";
        String ERROR = mapper.writeValueAsString(error);
        try {
            questionService.deleteQuestion(arrays);
            return SUCCESS;
        } catch (ServiceException e) {
            e.getMessage();
            logger.error(e.getMessage());
            return ERROR;
        }

    }

    @RequestMapping("/to-edit")
    public ModelAndView toEdit(String qid) {
        ModelAndView mod = new ModelAndView();
        try {
            Question question = new Question();
            question = questionService.findById(qid);
            mod.addObject("question", question);
            mod.setViewName(Constants.PATH_SHOW_EDIT_QUESTION);
        } catch (ServiceException e) {
            e.printStackTrace();
            String errMessage = e.getMessage();
            mod.addObject(Constants.ERR_MESSAGE, errMessage);
            logger.error(errMessage);
            mod.setView(getRedirectView(Constants.PATH_QUESTION_SHOW_lIST));

        }
        return mod;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Question question) {
        ModelAndView mod = new ModelAndView();
        questionService.update(question);
        mod.setView(getRedirectView(Constants.PATH_QUESTION_SHOW_lIST));
        return mod;

    }

}
