<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.perkins.oes.Constants" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>login</title>
    <link href="<%=request.getContextPath()%>/static/style/login.css" rel="stylesheet" type="text/css"/>
    <script>
    function login() {
        var loginFormObj = document.getElementById("loginForm");
        var userName = document.getElementById("userName").value;
        var isSubmitForm = true;
        if (!userName) {
            document.getElementById("iconLoginNameError").style.visibility ="visible";
            isSubmitForm = false;
            return;
        }

        var password = document.getElementById("password").value;
        if (!password) {
            document.getElementById("iconLoginPasswordError").style.visibility = "visible";
            isSubmitForm = false;
            return;
        }

        if (isSubmitForm) {
            loginFormObj.submit();
        }
    }

    var flag = true;
    function select () {
        if (flag) {
            document.getElementById("rememberSelect").style.background = 
                "url('static/style/images/BTN_RemeberMe_Select_12x12.png.png')";
            flag=false;
        } else {
            document.getElementById("rememberSelect").style.background = 
                "url('static/style/images/BTN_RemeberMe_Unselect_12X12.png.png')";
            flag=true;
        }
    }

    function init () {
        var top_messageValue = document.getElementById("topMessage").innerHTML;
        var error_messageObj = document.getElementById("errorMessage");
        if(top_messageValue != "null") {
            error_messageObj.style.visibility = "visible";
        }else {
            error_messageObj.style.visibility = "hidden";
        }
    }
    //when the html load end onload this method
    window.onload = init;
    </script>
  </head>
  <body>
    <%
      String errmessage = (String)request.getAttribute(Constants.ERR_MESSAGE);
    %>
    <div id="main">
      <div id="content">
        <div id="left">
          <div id="logo"></div>
          <div id="oesName">Online Exam Web System</div>
        </div>
        <div id="right">
          <div id="welcome">Welcome to login</div>
          <div id="form">
            <form action="login.action" method="post" id="loginForm">
              <div id="errorMessage" >
                <span id="topMessage"><%=errmessage %></span>
              </div>
              <div id="name" class="form_input">
                <i id="iconUser" ></i>
                <input type="text" name="userName" id="userName" placeholder="Username" />
                <i id="iconLoginNameError" ></i>
              </div>
              <div id="pw" class="form_input">
                <i id="iconPassword" ></i>
                <input type="password" name="password" id="password" placeholder="Password" />
                <i id="iconLoginPasswordError" ></i>
              </div>
              <input class="button" type="button" value="Login" onclick="login()" />
              <div class="remeber_forget">
                <div id="rember">
                  <i id="rememberSelect" class="icon_rember" onclick="select()"></i>
                  <span class="rember_text" onclick="select()">Remember me</span>
                </div>
                <div id="forget"><span class="forget_text">Forgot Password ?</span></div>
              </div>
              </form>
          </div>
        </div>
      </div>
      <div id="footer"><span class="footer_text">Copyright 2017 @ Augmentum, Perkins. All Rights Reserved</span></div>
    </div>

  </body>

</html>