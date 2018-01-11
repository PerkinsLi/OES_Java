<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.perkins.oes.Constants" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>login</title>
    <link href="${pageContext.request.contextPath }/static/style/login.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.cookie.js"></script>
    <script>
        $(function(){

            $("#loginBtn").click(function(){

                var isUserName = false;
                var userName = $("#userName").val();
                if (userName) {
                    $("#iconLoginNameError").css("visibility", "hidden");
                    isUserName = true;
                } else {
                    $("#iconLoginNameError").css("visibility", "visible");
                    isUserName = false;
                }

                var isPassword = false;
                var password = $("#password").val();
                if (password) {
                    $("#iconLoginPasswordError").css("visibility", "hidden");
                    isPassword = true;
                } else {
                    $("#iconLoginPasswordError").css("visibility", "visible");
                    isPassword = false;
                }

                var bgurl = "url('${pageContext.request.contextPath }/static/style/images/BTN_RemeberMe_Select_12x12.png.png')";
                var selectUrl = $("#rememberSelect").css("background-image");
                if (bgurl == selectUrl) {
                    $.cookie('nameCookie', userName, { expires: 7 });
                    $.cookie('psdCookie', password, { expires: 7 });
                } else {
                    $.cookie('nameCookie', '', { expires: -1 });
                    $.cookie('psdCookie', '', { expires: -1 });
                }

                if (isUserName && isPassword) {
                    $("#loginForm").submit();
                }
            });

            var flag = true;
            $("#rememberSelect").click(function(){
                if (flag) {
                    $("#rememberSelect").css("background-image",
                        "url('${pageContext.request.contextPath }/static/style/images/BTN_RemeberMe_Select_12x12.png.png')");
                    flag=false;
                } else {
                    $("#rememberSelect").css("background-image",
                        "url('${pageContext.request.contextPath }/static/style/images/BTN_RemeberMe_Unselect_12X12.png.png')");
                    flag=true;
                }
            });

            window.onload = function(){ 
              var error_messageObj = $("#errorMessage");
              var error_messageVal = $("#topMessage").text();
              if(error_messageVal) {
                  error_messageObj.css("visibility", "visible");
              }else {
                  error_messageObj.css("visibility", "hidden");
              }
              //cookie
              var nameCookie = $.cookie("nameCookie");
              var psdCookie = $.cookie("psdCookie");
              if (nameCookie && psdCookie) {
                  $("#rememberSelect").css("background-image",
                  "url('${pageContext.request.contextPath }/static/style/images/BTN_RemeberMe_Select_12x12.png.png')");
                  $("#userName").val(nameCookie);
                  $("#password").val(psdCookie);
              }
            }

        });
    </script>
  </head>
  <body>
    <div id="main">
      <div id="content">
        <div id="left">
          <div id="logo"></div>
          <div id="oesName">Online Exam Web System</div>
        </div>
        <div id="right">
          <div id="welcome">Welcome to login</div>
          <div id="form">
            <form action="login" method="post" id="loginForm">
              <div id="errorMessage" >
                <span id="topMessage">${ERR_MESSAGE}</span>
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
              <input class="button" type="button" value="Login" id="loginBtn" />
              <div class="remeber_forget">
                <div id="rember">
                  <i id="rememberSelect" class="icon_rember" ></i>
                  <span class="rember_text" >Remember me</span>
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