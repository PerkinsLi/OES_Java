<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath }/static/style/contentadmin.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath }/static/style/question_information.css" 
      rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
      $(function(){
          /* Give the correct answer background color */
          var rightAnsw = $("#rightAnswer").val();
              if (rightAnsw == "answer_a") {
                  $("#a .answer_text").css("background-color", "#D2DAE3");
              } else if (rightAnsw == "answer_b") {
                  $("#b .answer_text").css("background-color", "#D2DAE3");
              } else if (rightAnsw == "answer_c") {
                  $("#c .answer_text").css("background-color", "#D2DAE3");
              } else if (rightAnsw == "answer_d") {
                  $("#d .answer_text").css("background-color", "#D2DAE3");
              }

          /* delete button */
          $("#delete").click(function(){
              var id = $("#questionId").val();
              urlList = "${pageContext.request.contextPath }/page/question/show-list-question";
              delUrl = "${pageContext.request.contextPath }/page/question/delete-question";
              var jsonList = {"arrays" : id};
              $.ajax({
                  url: delUrl,
                  type: "post",
                  data: jsonList,
                  dataType: "json",
                  success: function(data) {
                      $(window).attr('location',urlList);
                  },
                  error: function(data) {
                      alert("delete error!");
                  }
              });
              
          });

          /* edit button */
          $("#edit").click(function(){
              var id = $("#questionId").val();
              url = "${pageContext.request.contextPath }/page/question/to-edit?qid="+id;
              $(window).attr('location',url);
          });
      });
    </script>
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
             <div class="name"> ${USER.userName}</div>
             <div class="logout"> 
               <a href="${pageContext.request.contextPath }/page/user/login-out" > Logout </a>
             </div>
           </div>
         </div>
      </div>
      <div id="navigation">
        <div class="navigation">
          <ul>
            <li id="questionmanagement">
              <a href="${pageContext.request.contextPath }/page/question/show-list-question">
                Question Management
              </a>
            </li>
            <li id="exammanagement"> 
              <a href="${pageContext.request.contextPath }/page/exam/show-list-exam">
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
        <div id="QuestionInformation">
          <input type="hidden" id="questionId" value="${question.id }" />
          <div id="questionID">
            <div id="qid" class="create_family">Question ID ：</div>
            <div id="qText"><input type="text" readonly="readonly" id="questionIdText"
              value="${question.questionID }"/></div>
          </div>
          <div id="question">
            <div class="create_family">Question ：</div>
            <div id="questionContent">
              <span id="questionTitle">
                ${question.title }
              </span>
            </div>
          </div>
          <div id="answer">
            <div class="create_family">Answer ：</div>
            <div id="answerContent">
              <input type="hidden" id="rightAnswer" value="${question.rightAnswer }" />
              <div id="a" class="answerCon">
                <span id="spanA" title="a">A</span>
                <span class="answer_text">${question.answerA }</span>
              </div>
              <div id="b" class="answerCon">
                <span id="spanB" title="b">B</span>
                <span class="answer_text">${question.answerB }</span>
              </div>
              <div id="c" class="answerCon">
                <span id="spanC" title="c">C</span>
               <span class="answer_text">${question.answerC }</span>
              </div>
              <div id="d" class="answerCon">
                <span id="spanD" title="d">D</span>
                <span class="answer_text">${question.answerD }</span>
              </div>
            </div>
          </div>
          <div id="button">
            <input type="button" value="Edit" id="edit" class="buttonConClass"/>
            <input type="button" value="Delete" id="delete" class="buttonConClass"/>
          </div>
        </div>
      </div>
      <div id="footer"> Copyright 2017 @ Augmentum, Perkins. All Rights Reserved </div>
      </div>
  </body>
</html>