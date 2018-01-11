<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Question detail</title>
    <link href="${pageContext.request.contextPath }/static/style/question_information.css" 
      rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
      $(function(){
          /* Give the correct answer background color */
          var rightAnsw = $("#rightAnswer").val();
              if (rightAnsw == "a") {
                  $("#a .answer_text").css("background-color", "#D2DAE3");
              } else if (rightAnsw == "b") {
                  $("#b .answer_text").css("background-color", "#D2DAE3");
              } else if (rightAnsw == "c") {
                  $("#c .answer_text").css("background-color", "#D2DAE3");
              } else if (rightAnsw == "d") {
                  $("#d .answer_text").css("background-color", "#D2DAE3");
              }

          /* delete button */
          $("#delete").click(function(){
              var id = $("#questionId").val();
              url = "<%=request.getContextPath()%>/deleteQuestion.action?arrays="+id;
              $(window).attr('location',url);
          });

          /* edit button */
          $("#edit").click(function(){
              var id = $("#questionId").val();
              url = "<%=request.getContextPath()%>/toEditQuestion.action?qid="+id;
              $(window).attr('location',url);
          });
      });
    </script>
  </head>
  <body>
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
            <span class="answer_text">${question.a }</span>
          </div>
          <div id="b" class="answerCon">
            <span id="spanB" title="b">B</span>
            <span class="answer_text">${question.b }</span>
          </div>
          <div id="c" class="answerCon">
            <span id="spanC" title="c">C</span>
            <span class="answer_text">${question.c }</span>
          </div>
          <div id="d" class="answerCon">
            <span id="spanD" title="d">D</span>
            <span class="answer_text">${question.d }</span>
          </div>
        </div>
      </div>
      <div id="button">
        <input type="button" value="Edit" id="edit" class="buttonConClass"/>
        <input type="button" value="Delete" id="delete" class="buttonConClass"/>
        <div></div>
      </div>
    </div>
  </body>
</html>