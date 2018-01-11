<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edit Question</title>
	<link href="${pageContext.request.contextPath }/static/style/contentadmin.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath }/static/style/create_question.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
    $(function(){

        var rightAnsw = $("#rightAnswer").val();
        if (rightAnsw == "answer_a") {
            $("#a input").css("background-color", "#D2DAE3");
            $("#a img").attr("src",
            "${pageContext.request.contextPath }/static/imges/BTN_Radio_Selected_16x16.png.png");
        } else if (rightAnsw == "answer_b") {
            $("#b input").css("background-color", "#D2DAE3");
            $("#b img").attr("src",
            "${pageContext.request.contextPath }/static/imges/BTN_Radio_Selected_16x16.png.png");
        } else if (rightAnsw == "answer_c") {
            $("#c input").css("background-color", "#D2DAE3");
            $("#c img").attr("src",
            "${pageContext.request.contextPath }/static/imges/BTN_Radio_Selected_16x16.png.png");
        } else if (rightAnsw == "answer_d") {
            $("#d input").css("background-color", "#D2DAE3");
            $("#d img").attr("src",
            "${pageContext.request.contextPath }/static/imges/BTN_Radio_Selected_16x16.png.png");
        }

        var clickAnswer = null;
        /* create question pohoto */
        $("#answerContent img").click(function(){
                imgObjs = $("#answerContent img");
                $(this).attr("src","${pageContext.request.contextPath }/static/imges/BTN_Radio_Selected_16x16.png.png");
                for (var i = 0; i < imgObjs.length; i++) {
                    if(this == imgObjs[i]) {
                        $(imgObjs[i]).attr("src",
                        "${pageContext.request.contextPath }/static/imges/BTN_Radio_Selected_16x16.png.png");
                        $(imgObjs[i]).parent().children("input").css("background", "#D2DAE3");
                        clickAnswer = $(imgObjs[i]).parent().children("span").attr("title");
                    } else {
                        $(imgObjs[i]).attr("src",
                        "${pageContext.request.contextPath }/static/imges/BTN_Radio_Unselected_16x16.png.png");
                        $(imgObjs[i]).parent().children("input").css("background", "#FFFFFF");
                    }
                }
        });
        /* create question pohoto end*/

        /* empty input */
        $("#createQuestionMain #cancel").click(function(){
            var url = "${pageContext.request.contextPath }/page/question/show-list-question";
            $(window).attr('location',url);
        });

        /* edit question */
        $("#createQuestionMain #save").click(function(){
            var id=$("#questionId").val();
            var questionID = $("#questionIdText").val();
            var title = $("#questionText").val();
            var a = $("#answerA").val();
            var b = $("#answerB").val();
            var c = $("#answerC").val();
            var d = $("#answerD").val();
            var rightAnswer = clickAnswer;
            var url = "${pageContext.request.contextPath }/page/question/edit?questionID="+questionID+"&title="+title;
            url += "&answerA="+a+"&answerB="+b+"&answerC="+c+"&answerD="+d+"&rightAnswer="+rightAnswer+"&id="+id;
            $(window).attr('location',url);
        });

    });
    </script>
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
        <div id="createQuestionMain">
    <input type="hidden" id="questionId" value="${question.id }"/>
    <input type="hidden" id="rightAnswer" value="${question.rightAnswer }" />
    <div id="questionID">
      <div id="qid" class="create_family">Question ID ：</div>
      <div id="qText"><input type="text" readonly="readonly" id="questionIdText"
       value="${question.questionID }"/></div>
    </div>
    <div id="question">
      <div class="create_family">Question ：</div>
      <div id="questionContent"><textarea rows="5" cols="10" id="questionText">${question.title }</textarea></div>
    </div>
    <div id="answer">
      <div class="create_family">Answer ：</div>
      <div id="answerContent">
        <div id="a" class="answerCon">
          <img alt="" src="${pageContext.request.contextPath }/static/imges/BTN_Radio_Unselected_16x16.png.png">
          <span id="spanA" title="answer_a">A</span>
          <input type="text" id="answerA" value="${question.answerA }" />
        </div>
        <div id="b" class="answerCon">
          <img alt="" src="${pageContext.request.contextPath }/static/imges/BTN_Radio_Unselected_16x16.png.png">
          <span id="spanB" title="answer_b">B</span>
          <input type="text" id="answerB" value="${question.answerB }" />
        </div>
        <div id="c" class="answerCon">
          <img alt="" src="${pageContext.request.contextPath }/static/imges/BTN_Radio_Unselected_16x16.png.png">
          <span id="spanC" title="answer_c">C</span>
          <input type="text" id="answerC" value="${question.answerC }" />
        </div>
        <div id="d" class="answerCon">
          <img alt="" src="${pageContext.request.contextPath }/static/imges/BTN_Radio_Unselected_16x16.png.png">
          <span id="spanD" title="answer_d">D</span>
          <input type="text" id="answerD" value="${question.answerD }" />
        </div>
      </div>
    </div>
    <div id="button">
      <input type="button" value="Save" id="save" class="buttonConClass"/>
      <input type="button" value="Cancel" id="cancel" class="buttonConClass"/>
      <div></div>
    </div>
  </div>
      </div>
      <div id="footer"> Copyright 2017 @ Augmentum, Perkins. All Rights Reserved </div>
      </div>
  </body>
</html>