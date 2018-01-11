package com.perkins.oes;

public class Constants {
    public static final String ERROR_FIELDS = "ERROR_FIELDS";
    public static final String ERR_MESSAGE = "ERR_MESSAGE";
    public static final String USER = "USER";

    public static final String APP_CONTEXT_SESSION = "APP_CONTEXT_SESSION";
    public static final String APP_CONTEXT_USER = "APP_CONTEXT_USER";

    // Controller Path
    // user controller
    public static final String PATH_LOGIN_PAGE = "/login";
    public static final String PATH_LOGIN_OUT = "/page/user/login-out";
    public static final String PATH_SHOW_LOGIN = "/page/user/show-login";
    public static final String PATH_LOGIN = "/page/user/login";

    // question controller
    public static final String PATH_SHOW_CONTENTADMIN_MAIN = "oes/contentadmin/main";
    public static final String PATH_SHOW_INFORMATION_QUESTION = "oes/contentadmin/information";
    public static final String PATH_SHOW_CREATE_QUESTION = "oes/contentadmin/create_question";
    public static final String PATH_SHOW_EDIT_QUESTION = "oes/contentadmin/edit_question";

    public static final String PATH_QUESTION_SHOW_lIST = "/page/question/show-list-question";
    public static final String PATH_INFORMATION_QUESTION = "/page/question/question-information";
    public static final String PATH_TO_SAVE_QUESTION = "/page/question/to-save";
    public static final String PATH_DELETE_QUESTION = "/page/question/delete-question";
    public static final String PATH_TO_EDIT_QUESTION = "/page/question/to-edit";
    public static final String PATH_EDIT_QUESTION = "/page/question/edit";

    // exam controller
    public static final String PATH_SHOW_EXAM_LIST = "oes/contentadmin/exam_management";
    // Controller Path end

    // service
    // user
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_NAME_EMPTY = "UserName is required!";
    public static final String PASSWORD = "PASSWORD";
    public static final String PASSWORD_EMPTY = "Password is required!";

    // question
    public static final String DELETE_AUGMENTS_EMPTY = "You shoud choose one question.";
    public static final String FINDBYID_EMPTY = "Id is null!";
}
