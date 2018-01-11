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

import com.perkins.oes.Constants;
import com.perkins.oes.entity.User;

public class SessionFilter implements Filter {

    private String notNeedLoginPage = "";
    protected FilterConfig filterConfig;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String requestUri = uri.substring(request.getContextPath().length() + 1);

        String[] pages = notNeedLoginPage.split(",");
        Boolean isAllow = false;
        for (String page : pages) {
            if (page.equals(requestUri)) {
                isAllow = true;
                break;
            }
        }

        if (isAllow) {
            filterChain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.USER);
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/page/user/show-login");
            } else {
                filterChain.doFilter(request, response);
            }
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String np = filterConfig.getInitParameter("notNeedLoginPage");
        if (np != null) {
            notNeedLoginPage = np;
        }
    }

}
