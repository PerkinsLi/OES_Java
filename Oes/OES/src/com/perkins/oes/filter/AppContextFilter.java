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

import com.perkins.oes.AppContext;
import com.perkins.oes.Constants;

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
        AppContext appContext = AppContext.getAppContext();
        appContext.addObjects(Constants.APP_CONTEXT_REQUEST, request);
        appContext.addObjects(Constants.APP_CONTEXT_RESPONSE, response);

        try {
            chain.doFilter(request, response);
        } catch (IOException ioException) {
            throw ioException;
        } catch (ServletException servletException) {
            throw servletException;
        } catch (RuntimeException runtimeException) {
            throw runtimeException;
        } finally {
            appContext.clear();

        }

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
