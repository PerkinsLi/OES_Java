<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ContendAdmin</title>
    <link href="<%=request.getContextPath()%>/static/style/contentadmin.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/static/style/question_management.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.10.2.min.js"></script> 
    <script type="text/javascript">
      $(function(){

          $("#navigation li").click(function(){
              $(this).addClass("selected").siblings().removeClass("selected");
          });

          /* question management start */
          $("#questionManagementLeft ul li.listTwo").click(function(){
              $("#path #pathInformation").html("Question Management > Create Question");
              $("#content").empty();
              $("#content").load("<%=request.getContextPath()%>/toCreateQuestion.action");
          });

          var titleFlag = false;
          $(".question_list_title .question_list_five img").click(function(){
              if(titleFlag == false){
                  var imgObjs = $(".question_list_five img");
                  var length = $(".question_list_five img").length;
                  for (var i = 0; i<imgObjs.length; i++){
                      $(imgObjs[i]).attr("src",
                      "<%=request.getContextPath()%>/static/style/images/ICN_Selected_15x15.png.png");
                  }
                  titleFlag = true;
              } else {
                  var imgObjs = $(".question_list_five img");
                  var length = $(".question_list_five img").length;
                  for (var i = 0; i<imgObjs.length; i++){
                      $(imgObjs[i]).attr("src",
                      "<%=request.getContextPath()%>/static/style/images/ICN_Unselected_15x15.png.png");
                      }
                  titleFlag = false;
              }
          });

          $("#questionListTbody .question_list_five img").click(function(){
              var imgObjs = $("#questionListTbody .question_list_five img");
              selectUrl = "<%=request.getContextPath()%>/static/style/images/ICN_Selected_15x15.png.png";
              unselectUrl = "<%=request.getContextPath() %>/static/style/images/ICN_Unselected_15x15.png.png";
              nowUrl = $(this).attr("src");
              if(nowUrl == unselectUrl){
                  $(this).attr("src",
                  "<%=request.getContextPath()%>/static/style/images/ICN_Selected_15x15.png.png");
              } else {
                  $(this).attr("src",
                  "<%=request.getContextPath()%>/static/style/images/ICN_Unselected_15x15.png.png");
                  $(".question_list_title .question_list_five img").attr("src",
                          "<%=request.getContextPath()%>/static/style/images/ICN_Unselected_15x15.png.png");
                  titleFlag=false;
              }
          });

          var pageCount = parseInt($("#one #pageCount").val());
          var cureentPage = parseInt($("#one a").html());
          if(pageCount <= 2){
              $("#two").css("display","none");
              $("#three").css("display","none");
          }else if(pageCount <= 3){
              $("#three").css("display","none");
          }

          if(pageCount == cureentPage) {
              $("#two").css("display","none");
              $("#three").css("display","none");
          }else if (pageCount == (cureentPage+1)){
              $("#three").css("display","none");
          }

          $("#questionListPageSelect").change(function(){
              var searchText = $("#search").val();
              var pagesize = $(this).val();
              var sort = $("#sort").val();
              var url = "<%=request.getContextPath()%>/showListQuestion.action?pagesize="+pagesize+"&sort="+sort;
              url += "&searchText="+searchText;
              $(window).attr('location',url);
          });
          
          $("#go").click(function(){
              var searchText = $("#search").val();
              var page = $("#text").val();
              var pagesize = $("#pageSize").val();
              var sort = $("#sort").val();
              var url = "<%=request.getContextPath()%>/showListQuestion.action?pagesize="+pagesize;
              url += "&currentPage="+page+"&sort="+sort+"&searchText="+searchText;
              $(window).attr('location',url);
          });

         
              var sort = $("#sort").val();
              if(sort == "ASC"){
                  $(".question_list_title .question_list_two img").attr("src",
                          "<%=request.getContextPath() %>/static/imges/ICN_Increase_10x15.png.png");
              }else if(sort == "DESC") {
                  $(".question_list_title .question_list_two img").attr("src",
                  "<%=request.getContextPath()%>/static/imges/ICN_Decrese_10x15.png.png");
              }else {
                  $(".question_list_title .question_list_two img").attr("src",
                  "<%=request.getContextPath() %>/static/imges/ICN_Increase_10x15.png.png");
              }
          
          

          $(".question_list_title .question_list_two img").click(function(){
              var src = $(this).attr("src");
              var changeSort = null;
              var dec = "<%=request.getContextPath()%>/static/imges/ICN_Decrese_10x15.png.png";
              var inc = "<%=request.getContextPath() %>/static/imges/ICN_Increase_10x15.png.png";
              if (src == inc){
                  $(this).attr("src", "<%=request.getContextPath()%>/static/imges/ICN_Decrese_10x15.png.png");
                  changeSort = "DESC";
              }else {
                  $(this).attr("src", "<%=request.getContextPath() %>/static/imges/ICN_Increase_10x15.png.png");
                  changeSort = "ASC";
              }

              var searchText = $("#search").val();
              var page = $("#text").val();
              var pagesize = $("#pageSize").val();
              var url = "<%=request.getContextPath()%>/showListQuestion.action?pagesize="+pagesize;
              url += "&currentPage="+page+"&sort="+changeSort+"&searchText="+searchText;
              $(window).attr('location',url);
          });

          /* delete question */
          $("#questionListPage #del").click(function(){
              var flag = false;
              var imgSelectedObjs = $("#questionListTbody .question_list_five img");
              var selecteSrc = "<%=request.getContextPath()%>/static/style/images/ICN_Selected_15x15.png.png";
              for (var i = 0; i < imgSelectedObjs.length; i++){
                  var src = $(imgSelectedObjs[i]).attr("src");
                  if (src == selecteSrc){
                      $("#popupWindow").css("display", "block");
                     return;
                  }
              }
          });

          $("#popupWindow img").click(function(){
              $("#popupWindow").css("display", "none");
          });

          $("#popupWindow #yes").click(function(){
              var imgSelectedObjs = $("#questionListTbody .question_list_five img");
              var selecteSrc = "<%=request.getContextPath()%>/static/style/images/ICN_Selected_15x15.png.png";
              var arrays = new Array();
              for (var i = 0; i < imgSelectedObjs.length; i++){
                  var src = $(imgSelectedObjs[i]).attr("src");
                  if (src == selecteSrc){
                      var id = $(imgSelectedObjs[i]).attr("id");
                      arrays[i] = id;
                  }
              }
              var str = arrays.toString();
              var url = "<%=request.getContextPath()%>/deleteQuestion.action?arrays="+str;
              $(window).attr('location',url);
          });

          $("#popupWindow #no").click(function(){
              $("#popupWindow").css("display", "none");
          });

          /* search question */
          $("#iconSearch").click(function(){
              var currentPage = $("#text").val();
              var pagesize = $("#pageSize").val();
              var sort = $("#sort").val();
              var searchText = $("#search").val();
              var url = "<%=request.getContextPath()%>/showListQuestion.action?searchText="+searchText;
              url += "&currentPage="+currentPage+"&pagesize="+pagesize+"&sort="+sort;
              $(window).attr('location',url);
          });
          var searchT=$("#searchText").val();
          $("#search").val(searchT);

          /* question information */
          $("#questionListTbody .question_list .question_list_three").click(function(){
              var id=$(this).children("#qId").val();
              url = "<%=request.getContextPath()%>/questionInformation.action?qid="+id;
              $(window).attr('location',url);
          });

          /* edit */
          $("#questionListTbody .question_list_four img").click(function(){
              var id = $(this).attr("id");
              url = "<%=request.getContextPath()%>/toEditQuestion.action?qid="+id;
              $(window).attr('location',url);
          });
          /* question management end */
      });
    </script>
    <style type="text/css">
      .selected {
          background-color: #D2DAE3;
      }
    </style>
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
             <div class="name"> ${USER.user_name }</div>
             <div class="logout"> 
               <a href="<%=request.getContextPath()%>/loginOut.action" > Logout </a>
             </div>
           </div>
         </div>
      </div>
      <div id="navigation">
        <div class="navigation">
          <ul>
            <li id="questionmanagement" class="selected">
              <a href="<%=request.getContextPath()%>/showListQuestion">
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
      <div id="path"><div id="pathInformation"></div></div>
      <div id="content">
        <div id="questionManagement">
          <div id="questionManagementHeader">
            <div class="questionManagementInput">
              <div id="inputBox">
                <input type="text" name="serach" id="search" placeholder="Please input the keyword" />
              </div>
              <i id="iconSearch"></i>
            </div>
          </div>
          <div id="questionManagementContent">
            <div id="questionManagementLeft">
              <ul>
                <li class="listOne">Question List</li>
                <li class="listTwo">Create Question</li>
              </ul>
            </div>
            <div id="questionManagementRight">
              <div id="listTable">
                <table class="question_table">
                  <tr class="question_list_title">
                    <td class="question_list_one"></td>
                    <td class="question_list_two">
                      ID<img alt="" >
                    </td>
                    <td>description</td>
                    <td class="question_list_four">Edit</td>
                    <td class="question_list_five">
                      <img src="<%=request.getContextPath() %>/static/style/images/ICN_Unselected_15x15.png.png" />
                    </td>
                  </tr>
                 <tbody id="questionListTbody">
                   <c:forEach items="${questions }" var="q" varStatus="status">
                     <tr class="question_list" id="question_list">
                       <td class="question_list_one">${status.index+1 }</td>
                       <td class="question_list_two" id="${q.id }">${q.questionID }</td>
                       <td class="question_list_three">
                         <input type="hidden" id="qId" value="${q.id }" />
                         ${q.title }
                       </td>
                       <td class="question_list_four">
                         <img id="${q.id }" 
                           src="<%=request.getContextPath() %>/static/style/images/ICN_Edit_15x15.png.png" />
                       </td>
                       <td class="question_list_five">
                         <img id="${q.id }" 
                           src="<%=request.getContextPath() %>/static/style/images/ICN_Unselected_15x15.png.png" />
                       </td>
                     </tr>
                   </c:forEach>
                 </tbody>
               </table>
             </div>
              <div id="questionListPage">
                <div id="pageSign">
                  <div id="pageLeft">
                    <a href="<%=request.getContextPath()%>/showListQuestion.action?currentPage=${page.currentPage-1}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                    <img alt="pageLeft" 
                      src="<%=request.getContextPath() %>/static/imges/BTN_PageLeft_20x15.png.png">
                    </a>
                  </div>
                  <div id="one">
                    <input type="hidden" id="pageSize" value="${page.pageSize}" />
                    <input type="hidden" id="pageCount" value="${page.pageCount }" />
                    <input type="hidden" id="sort" value="${page.sort }" />
                    <input type="hidden" id="searchText" value="${searchText }" />
                    <a href="<%=request.getContextPath()%>/showListQuestion.action?currentPage=${page.currentPage}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                      ${page.currentPage }
                    </a>
                  </div>
                  <div id="two">
                    <a href="<%=request.getContextPath()%>/showListQuestion.action?currentPage=${page.currentPage+1}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                      ${page.currentPage+1}
                    </a>
                  </div>
                  <div id="three">
                    <a href="<%=request.getContextPath()%>/showListQuestion.action?currentPage=${page.currentPage+2}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                      ${page.currentPage+2}
                    </a>
                  </div>
                  <div id="omit">...</div>
                  <div id="lastPage">
                    <a href="<%=request.getContextPath()%>/showListQuestion.action?currentPage=${page.pageCount }&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                      ${page.pageCount }
                    </a>
                  </div>
                  <div id="pageRight">
                    <a href="<%=request.getContextPath()%>/showListQuestion.action?currentPage=${page.currentPage+1}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                    <img alt="pageRight" 
                      src="<%=request.getContextPath() %>/static/imges/BTN_PageRight_20x15.png .png">
                    </a>
                  </div>
                  <div id="selector">
                    <select id="questionListPageSelect">
                      <option>1</option>
                      <option>2</option>
                      <option>3</option>
                      <option>4</option>
                      <option>5</option>
                      <option>6</option>
                      <option>7</option>
                      <option>8</option>
                      <option>9</option>
                      <option>10</option>
                    </select>
                  </div>
                  <div id="perPage">Per Page</div>
                  <input type="text" id="text" />
                  <input type="button" id="go" value="Go">
                </div>
                <input type="button" id="del" value="Delete">
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="footer"> Copyright 2017 @ Augmentum, Perkins. All Rights Reserved </div>
    </div>
    <div id="popupWindow">
      <div id="popContent">
        <img alt="close" src="<%=request.getContextPath() %>/static/imges/BTN_Close_16x16.png.png">
        <span>Are sure delete the selected options</span>
        <div id="popInput">
          <input type="button" id="yes" value="Yes" />
          <input type="button" id="no" value="No" />
        </div>
      </div>
    </div>
  </body>
</html>