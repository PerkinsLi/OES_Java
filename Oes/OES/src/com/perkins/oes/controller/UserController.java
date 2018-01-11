package com.perkins.oes.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.perkins.oes.AppContext;
import com.perkins.oes.Constants;
import com.perkins.oes.common.ModelAndView;
import com.perkins.oes.entity.User;
import com.perkins.oes.exception.ParameterException;
import com.perkins.oes.exception.ServiceException;
import com.perkins.oes.service.UserService;

public class UserController {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ModelAndView showLogin(Map<String, String> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView("input");

        return modelAndView;
    }

    public ModelAndView login(Map<String, String> request, Map<String, Object> session) {

        String userName = request.get("userName");
        String password = request.get("password");

        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = null;
            user = userService.login(userName, password);
            user.setPassword(null);
            modelAndView.addSessionAttribute(Constants.USER, user);
            modelAndView.setRedirect(true);
            modelAndView.setView("./showListQuestion.action");
        } catch (ParameterException parameterException) {
            Map<String, String> errorfields = parameterException.getErrorfields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorfields);
            modelAndView.setRedirect(true);
            modelAndView.setView("./showLogin.action");
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.ERR_MESSAGE, serviceException.getMessage() +
                    "[" + serviceException.getCode() + "]");
            modelAndView.setView("/WEB-INF/jsp/login.jsp");
        }

        return modelAndView;
    }

    public ModelAndView loginOut(Map<String, String> request, Map<String, Object> session) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        HttpServletRequest httpRequest = (HttpServletRequest) AppContext.getAppContext().
                getObject(Constants.APP_CONTEXT_REQUEST);
        HttpSession session2 = httpRequest.getSession();
        session2.invalidate();
        modelAndView.setRedirect(true);
        modelAndView.setView("/showLogin.action");
        return modelAndView;
    }

}
