package com.perkins.oes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.perkins.oes.Constants;

@Controller
@RequestMapping("/page/exam")
public class ExamController extends BaseController {

    @RequestMapping("/show-list-exam")
    public ModelAndView showListExam() {
        ModelAndView mod = new ModelAndView();
        mod.setViewName(Constants.PATH_SHOW_EXAM_LIST);
        return mod;
    }
}
