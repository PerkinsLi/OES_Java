<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>create question</title>
    <link href="<%=request.getContextPath()%>/static/style/create_question.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.10.2.min.js"></script> 
    <script type="text/javascript">
    $(function(){

        var clickAnswer = null;
        /* create question pohoto */
        $("#answerContent img").click(function(){
                imgObjs = $("#answerContent img");
                $(this).attr("src","<%=request.getContextPath()%>/static/imges/BTN_Radio_Selected_16x16.png.png");
                for (var i = 0; i < imgObjs.length; i++) {
                    if(this == imgObjs[i]) {
                        $(imgObjs[i]).attr("src",
                        "<%=request.getContextPath()%>/static/imges/BTN_Radio_Selected_16x16.png.png");
                        $(imgObjs[i]).parent().children("input").css("background", "#D2DAE3");
                        clickAnswer = $(imgObjs[i]).parent().children("span").attr("title");
                    } else {
                        $(imgObjs[i]).attr("src",
                        "<%=request.getContextPath() %>/static/imges/BTN_Radio_Unselected_16x16.png.png");
                        $(imgObjs[i]).parent().children("input").css("background", "#FFFFFF");
                    }
                }
        });
        /* create question pohoto end*/

        /* empty input */
        $("#createQuestionMain #cancel").click(function(){
            var url = "<%=request.getContextPath() %>/showListQuestion.action";
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
            var json = {"questionID":questionID, "title":title, "a":a, "b":b,"c":c,"d":d,"rightAnswer":rightAnswer};
            var url = "<%=request.getContextPath() %>/createQuestion.action?questionID="+questionID+"&title="+title;
            url += "&a="+a+"&b="+b+"&c="+c+"&d="+d+"&rightAnswer="+rightAnswer;
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
       value="<%=request.getAttribute("questionID")%>"/></div>
    </div>
    <div id="question">
      <div class="create_family">Question ：</div>
      <div id="questionContent"><textarea rows="5" cols="10" id="questionText"></textarea></div>
    </div>
    <div id="answer">
      <div class="create_family">Answer ：</div>
      <div id="answerContent">
        <div id="a" class="answerCon">
          <img alt="" src="<%=request.getContextPath() %>/static/imges/BTN_Radio_Unselected_16x16.png.png">
          <span id="spanA" title="a">A</span>
          <input type="text" id="answerA"/>
        </div>
        <div id="b" class="answerCon">
          <img alt="" src="<%=request.getContextPath() %>/static/imges/BTN_Radio_Unselected_16x16.png.png">
          <span id="spanB" title="b">B</span>
          <input type="text" id="answerB"/>
        </div>
        <div id="c" class="answerCon">
          <img alt="" src="<%=request.getContextPath() %>/static/imges/BTN_Radio_Unselected_16x16.png.png">
          <span id="spanC" title="c">C</span>
          <input type="text" id="answerC"/>
        </div>
        <div id="d" class="answerCon">
          <img alt="" src="<%=request.getContextPath() %>/static/imges/BTN_Radio_Unselected_16x16.png.png">
          <span id="spanD" title="d">D</span>
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