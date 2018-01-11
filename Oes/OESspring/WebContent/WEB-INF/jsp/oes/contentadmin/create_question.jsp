<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>create question</title>
    <link href="${pageContext.request.contextPath }/static/style/create_question.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.10.2.min.js"></script> 
    <script type="text/javascript">
    $(function(){

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

        /* create question */
        $("#createQuestionMain #create").click(function(){
            var questionID = $("#questionIdText").val();
            var title = $("#questionText").val();
            var a = $("#answerA").val();
            var b = $("#answerB").val();
            var c = $("#answerC").val();
            var d = $("#answerD").val();
            var rightAnswer = clickAnswer;
            if (!title) {
                $("#questionText").css("border", "1px dashed #FF3030");
                return;
            } else {
                $("#questionText").css("border", "1px solid #2E4358");
            }

            if (!a){
                $("#answerA").css("border", "1px dashed #FF3030");
                return;
            } else {
                $("#answerA").css("border", "1px solid #2E4358");
            }

            if (!b) {
                $("#answerB").css("border", "1px dashed #FF3030");
                return;
            } else {
                $("#answerB").css("border", "1px solid #2E4358");
            }

            if (!c) {
                $("#answerC").css("border", "1px dashed #FF3030");
                return;
            } else {
                $("#answerC").css("border", "1px solid #2E4358");
            }

            if (!d) {
                $("#answerD").css("border", "1px dashed #FF3030");
                return;
            } else {
                $("#answerD").css("border", "1px solid #2E4358");
            }

            if (!rightAnswer) {
                alert("RightAnswer is required!");
                return;
            }
            var url = "${pageContext.request.contextPath }/page/question/save?questionID="+questionID+"&title="+title;
            url += "&answerA="+a+"&answerB="+b+"&answerC="+c+"&answerD="+d+"&rightAnswer="+rightAnswer;
            $(window).attr('location',url);
        });

    });
    </script>
  </head>
<body>
  <div id="createQuestionMain">
    <div id="questionID">
      <div id="qid" class="create_family">Question ID ：</div>
      <div id="qText"><input type="text" readonly="readonly" id="questionIdText"
       value="${questionID}"/></div>
    </div>
    <div id="question">
      <div class="create_family">Question ：</div>
      <div id="questionContent"><textarea rows="5" cols="10" id="questionText"></textarea></div>
    </div>
    <div id="answer">
      <div class="create_family">Answer ：</div>
      <div id="answerContent">
        <div id="a" class="answerCon">
          <img alt="" src="${pageContext.request.contextPath }/static/imges/BTN_Radio_Unselected_16x16.png.png">
          <span id="spanA" title="answer_a">A</span>
          <input type="text" id="answerA"/>
        </div>
        <div id="b" class="answerCon">
          <img alt="" src="${pageContext.request.contextPath }/static/imges/BTN_Radio_Unselected_16x16.png.png">
          <span id="spanB" title="answer_b">B</span>
          <input type="text" id="answerB"/>
        </div>
        <div id="c" class="answerCon">
          <img alt="" src="${pageContext.request.contextPath }/static/imges/BTN_Radio_Unselected_16x16.png.png">
          <span id="spanC" title="answer_c">C</span>
          <input type="text" id="answerC"/>
        </div>
        <div id="d" class="answerCon">
          <img alt="" src="${pageContext.request.contextPath }/static/imges/BTN_Radio_Unselected_16x16.png.png">
          <span id="spanD" title="answer_d">D</span>
          <input type="text" id="answerD"/>
        </div>
      </div>
    </div>
    <div id="button">
      <input type="button" value="Create" id="create" class="buttonConClass"/>
      <input type="button" value="Cancel" id="cancel" class="buttonConClass"/>
      <div></div>
    </div>
  </div>
</body>
</html>