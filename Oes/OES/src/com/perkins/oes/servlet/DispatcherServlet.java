package com.perkins.oes.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.perkins.oes.common.ActionConfig;
import com.perkins.oes.common.BeanFactory;
import com.perkins.oes.common.ModelAndView;
import com.perkins.oes.common.ResultConfig;
import com.perkins.oes.common.ViewParameterConfig;
import com.perkins.oes.util.StringUtil;

public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String suffix = ".action";
    Map<String, ActionConfig> actionConfigs = new HashMap<String, ActionConfig>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String suffixFromConf = config.getInitParameter("suffix");
        if (!StringUtil.IsEmpty(suffixFromConf)) {
            suffix = suffixFromConf;
        }
        InputStream inputStream = null;
        try {
            inputStream = DispatcherServlet.class.getClassLoader().getResourceAsStream("action.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            Element element = document.getDocumentElement();

            NodeList actionNodes = element.getElementsByTagName("action");
            if (actionNodes == null) {
                return;
            }
            int actionLength = actionNodes.getLength();
            for (int i = 0; i < actionLength; i++) {
                Element actionElement = (Element) actionNodes.item(i);
                ActionConfig actionConfig = new ActionConfig();

                String name = actionElement.getAttribute("name");
                actionConfig.setName(name);
                String clsName = actionElement.getAttribute("class");
                actionConfig.setClassName(clsName);
                String methodName = actionElement.getAttribute("method");
                actionConfig.setMethodName(methodName);

                String httpMethod = actionElement.getAttribute("httpMethod");
                if (StringUtil.IsEmpty(httpMethod)) {
                    httpMethod = "GET";
                }
                String[] httpMethodArr = httpMethod.split(",");

                actionConfig.setHttpMethod(httpMethodArr);

                for (String httpMethodItem : httpMethodArr) {
                    actionConfigs.put(name + suffix + "#" + httpMethodItem.toUpperCase(), actionConfig);
                }
                NodeList resultNodes = actionElement.getElementsByTagName("result");
                if (resultNodes != null) {
                    int resultLength = resultNodes.getLength();
                    for (int j = 0; j < resultLength; j++) {
                        Element resultElement = (Element) resultNodes.item(j);
                        ResultConfig resultConfig = new ResultConfig();

                        String resultName = resultElement.getAttribute("name");
                        resultConfig.setName(resultName);

                        String resultView = resultElement.getAttribute("view");
                        resultConfig.setView(resultView);

                        String resultRedirect = resultElement.getAttribute("redirect");
                        if (StringUtil.IsEmpty(resultRedirect)) {
                            resultRedirect = "false";
                        }
                        resultConfig.setRedirect(Boolean.parseBoolean(resultRedirect));

                        String viewParameter = resultElement.getAttribute("viewParameter");

                        if (!StringUtil.IsEmpty(viewParameter)) {
                            String[] viewParameterArr = viewParameter.split(",");
                            for (String viewParameterItem : viewParameterArr) {
                                String[] viewParameterItemArr = viewParameterItem.split(":");
                                String key = viewParameterItemArr[0].trim();
                                String from = "attribute";
                                if (viewParameterItemArr.length == 2) {
                                    from = viewParameterItemArr[1].trim();
                                }
                                ViewParameterConfig viewParameterConfig = new ViewParameterConfig(key, from);
                                resultConfig.addViewParameterConfig(viewParameterConfig);
                            }
                        }
                        actionConfig.addResult(resultName, resultConfig);

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public DispatcherServlet() {
        super();

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        String requestUri = uri.substring(request.getContextPath().length() + 1);
        // Gets http request' method
        // GET http://localhost:8080/oes/showLogin.action HTTP 1/1
        String httpMethod = request.getMethod();
        if (StringUtil.IsEmpty(requestUri)) {
            requestUri = "showLogin" + this.suffix + "#" + httpMethod.toUpperCase();
        } else {
            requestUri = requestUri + "#" + httpMethod.toUpperCase();
        }

        ActionConfig actionConfig = actionConfigs.get(requestUri);
        HttpSession session = request.getSession();
        if (actionConfig != null) {
            String clsName = actionConfig.getClassName();
            try {
                Class<?>[] param = new Class[2];
                param[0] = Map.class;
                param[1] = Map.class;

                Object controller = BeanFactory.getInstance().getBean(clsName);
                String methodName = actionConfig.getMethodName();
                Method method = controller.getClass().getMethod(methodName, param);

                // get sessions from session attribute
                Map<String, Object> sessionMap = new HashMap<String, Object>();
                Enumeration<String> getSessionKeys = session.getAttributeNames();
                while (getSessionKeys.hasMoreElements()) {
                    String getKey = getSessionKeys.nextElement();
                    sessionMap.put(getKey, session.getAttribute(getKey));
                }
                // end

                // get request attribute from httpserlvet request
                Map<String, String> RequestParamMap = new HashMap<String, String>();
                Enumeration<String> getRequestParamKeys = request.getParameterNames();
                while (getRequestParamKeys.hasMoreElements()) {
                    String getKey = getRequestParamKeys.nextElement();
                    RequestParamMap.put(getKey, request.getParameter(getKey));
                }
                // end
                Object[] objects = new Object[2];
                objects[0] = RequestParamMap;
                objects[1] = sessionMap;
                ModelAndView modelAndView = (ModelAndView) method.invoke(controller, objects);

                // put fromControllerSession in session attribute
                Map<String, Object> fromControllerSession = modelAndView.getSessions();
                Set<String> keys = fromControllerSession.keySet();
                for (String key : keys) {
                    session.setAttribute(key, fromControllerSession.get(key));
                }
                // end
                // put fromControllerRequest in request attribute
                Map<String, Object> fromControllerRequests = modelAndView.getRequests();
                Set<String> keyRequests = fromControllerRequests.keySet();
                for (String key : keyRequests) {
                    request.setAttribute(key, fromControllerRequests.get(key));
                }
                // end

                String view = modelAndView.getView();
                ResultConfig resultConfig = actionConfig.getResult(view);
                if (resultConfig == null) {
                    if (modelAndView.isRedirect()) {
                        response.sendRedirect(request.getContextPath() + "/" + view);
                    } else {
                        request.getRequestDispatcher(view).forward(request, response);
                    }
                } else {
                    String resultView = request.getContextPath() + "/" + resultConfig.getView();
                    if (resultConfig.isRedirect()) {
                        List<ViewParameterConfig> viewParameterConfigs = resultConfig.getViewParameterConfigs();
                        if (viewParameterConfigs == null || viewParameterConfigs.isEmpty()) {
                            response.sendRedirect(resultView);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            for (ViewParameterConfig viewParameterConfig : viewParameterConfigs) {
                                String name = viewParameterConfig.getName();
                                String from = viewParameterConfig.getFrom();
                                String value = "";
                                if ("attribute".equals(from)) {
                                    value = (String) request.getAttribute(name);
                                } else if ("parameter".equals(from)) {
                                    value = request.getParameter(name);
                                } else if ("session".equals(from)) {
                                    value = (String) request.getSession().getAttribute(name);
                                } else {
                                    value = (String) request.getAttribute(name);
                                }

                                if (!StringUtil.IsEmpty(value)) {
                                    sb.append(name + "=" + value + "&");
                                }

                            }

                            if (resultView.indexOf("?") > 0) {
                                resultView = resultView + "&" + sb.toString();
                            } else {
                                resultView = resultView + "?" + sb.toString();
                            }
                            response.sendRedirect(resultView);
                        }

                    } else {
                        request.getRequestDispatcher(resultConfig.getView()).forward(request, response);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(500);
            }
        } else {
            response.sendError(404);
        }
    }
}
