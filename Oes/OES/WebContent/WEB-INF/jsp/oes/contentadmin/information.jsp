<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<%=request.getContextPath()%>/static/style/contentadmin.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.10.2.min.js"></script>
    <title>Question Information</title>
  </head>
  <body>
    <div id="contentAdmin">
      <div id="header">
        <div id="headerLeft">
          <div id="logo"></div>
          <div id="systemName"> Oline Exam Web System</div>
        </div>
         <div id="headerRight">
           <div id="chinese"> 中文 </div>
           <div id="information">
             <div class="icon_user"></div>
             <div class="name"> Perkins Li</div>
             <div class="logout"> 
               <a href="<%=request.getContextPath()%>/loginOut.action" > Logout </a>
             </div>
           </div>
         </div>
      </div>
      <div id="navigation">
        <div class="navigation">
          <ul>
            <li id="questionmanagement">
              <a href="<%=request.getContextPath()%>/showListQuestion.action">
                Question Management
              </a>
            </li>
            <li id="exammanagement"> 
              <a href="#">
              Exam Management
              </a>
            </li>
          </ul>
        </div>
      </div>
      <div id="path">
        <div id="pathInformation">
          QuestionManagement > Question List > <span>${question.questionID }</span>
        </div>
      </div>
      <div id="content">
        <jsp:include page="./question_information.jsp"></jsp:include>
      </div>
      <div id="footer"> Copyright 2017 @ Augmentum, Perkins. All Rights Reserved </div>
      </div>
  </body>
</html>