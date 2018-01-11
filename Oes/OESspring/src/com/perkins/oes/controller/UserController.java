package com.perkins.oes.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.perkins.oes.Constants;
import com.perkins.oes.entity.User;
import com.perkins.oes.exception.ParameterException;
import com.perkins.oes.exception.ServiceException;
import com.perkins.oes.service.UserService;

@Controller
@RequestMapping("/page/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/show-login")
    public ModelAndView showLogin(ModelAndView mod) {
        mod.setViewName(Constants.PATH_LOGIN_PAGE);
        return mod;
    }

    @RequestMapping("/login")
    public ModelAndView login(User user) {
        ModelAndView mod = new ModelAndView();
        try {
            user = userService.login(user.getUserName(), user.getPassword());
            user.setPassword(null);
            this.addSession(Constants.USER, user);
            mod.setView(getRedirectView(Constants.PATH_QUESTION_SHOW_lIST));
        } catch (ParameterException parameterException) {
            Map<String, String> errorfields = parameterException.getErrorfields();
            mod.addObject(Constants.ERROR_FIELDS, errorfields);
            logger.error(errorfields);
            mod.setView(getRedirectView(Constants.PATH_QUESTION_SHOW_lIST));
        } catch (ServiceException serviceException) {
            mod.addObject(Constants.ERR_MESSAGE, serviceException.getMessage());
            logger.error(serviceException.getMessage());
            mod.setViewName(Constants.PATH_LOGIN_PAGE);
        }
        return mod;
    }

    @RequestMapping("/login-out")
    public ModelAndView loginOut(ModelAndView mod, HttpSession session)
            throws IOException {
        invalidate();
        mod.setView(getRedirectView(Constants.PATH_SHOW_LOGIN));
        return mod;
    }

}
