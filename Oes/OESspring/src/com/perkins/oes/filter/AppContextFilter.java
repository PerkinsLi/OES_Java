package com.perkins.oes.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.perkins.oes.AppContext;
import com.perkins.oes.Constants;
import com.perkins.oes.entity.User;

public class AppContextFilter implements Filter {

    public AppContextFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        AppContext.setContextPath(request.getContextPath());
        AppContext appContext = AppContext.getAppContext();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.USER);
        if (user != null) {
            appContext.addObjects(Constants.APP_CONTEXT_USER, user);
        }
        appContext.addObjects(Constants.APP_CONTEXT_SESSION, session);

        try {
            chain.doFilter(request, response);
        } finally {
            appContext.clear();
        }

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
