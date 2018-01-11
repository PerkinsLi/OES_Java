<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/block.tld" prefix="block" %>
<!DOCTYPE html >
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ContendAdmin</title>
    <link href="${pageContext.request.contextPath }/static/style/contentadmin.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath }/static/style/question_management.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.10.2.min.js"></script> 
    <script type="text/javascript">
      $(function(){

          $("#navigation li").click(function(){
              $(this).addClass("selected").siblings().removeClass("selected");
          });

          /* question management start */
          $("#questionManagementLeft ul li.listTwo").click(function(){
              $("#path #pathInformation").html("Question Management > Create Question");
              $("#content").empty();
              $("#content").load("${pageContext.request.contextPath }/page/question/to-save");
          });

          var titleFlag = false;
          $(".question_list_title .question_list_five img").click(function(){
              if(titleFlag == false){
                  var imgObjs = $(".question_list_five img");
                  var length = $(".question_list_five img").length;
                  for (var i = 0; i<imgObjs.length; i++){
                      $(imgObjs[i]).attr("src",
                      "${pageContext.request.contextPath }/static/style/images/ICN_Selected_15x15.png.png");
                  }
                  titleFlag = true;
              } else {
                  var imgObjs = $(".question_list_five img");
                  var length = $(".question_list_five img").length;
                  for (var i = 0; i<imgObjs.length; i++){
                      $(imgObjs[i]).attr("src",
                      "${pageContext.request.contextPath }/static/style/images/ICN_Unselected_15x15.png.png");
                      }
                  titleFlag = false;
              }
          });

          var isAllSelected = true;
          $("#questionListTbody .question_list_five img").click(function(){
              var imgObjs = $("#questionListTbody .question_list_five img");
              selectUrl = "${pageContext.request.contextPath }/static/style/images/ICN_Selected_15x15.png.png";
              unselectUrl = "${pageContext.request.contextPath }/static/style/images/ICN_Unselected_15x15.png.png";
              nowUrl = $(this).attr("src");
              if(nowUrl == unselectUrl){
                  $(this).attr("src",
                  "${pageContext.request.contextPath }/static/style/images/ICN_Selected_15x15.png.png");
              } else {
                  $(this).attr("src",
                  "${pageContext.request.contextPath }/static/style/images/ICN_Unselected_15x15.png.png");
                  $(".question_list_title .question_list_five img").attr("src",
                          "${pageContext.request.contextPath }/static/style/images/ICN_Unselected_15x15.png.png");
                  titleFlag=false;
              }
              
              for (var i = 0; i<imgObjs.length; i++){
                  var url = $(imgObjs[i]).attr("src");
                  if (url == unselectUrl) {
                      isAllSelected = false;
                      return;
                  } else {
                      isAllSelected = true;
                  }
              }
              
              if (isAllSelected) {
                  $(".question_list_title .question_list_five img").attr("src",
                  "${pageContext.request.contextPath }/static/style/images/ICN_Selected_15x15.png.png");
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
              $("#lastPage").css("display","none");
          }else if (pageCount == (cureentPage+1)){
              $("#three").css("display","none");
          }

          var optionObjs = $("#questionListPageSelect option");
          var pagesize = $("#pageSize").val();
          for (var i=0; i<optionObjs.length; i++) {
              var currPageSize = $(optionObjs[i]).val();
              if (currPageSize == pagesize) {
                  $(optionObjs[i]).attr("selected", "selected");
              }
          }
          $("#questionListPageSelect").change(function(){
              var searchText = $("#search").val();
              var pagesize = $(this).val();
              var sort = $("#sort").val();
              var url = "${pageContext.request.contextPath }/page/question/show-list-question?pagesize="+pagesize;
              url += "&sort="+sort+"&searchText="+searchText;
              $(window).attr('location',url);
          });
          
          $("#go").click(function(){
              var searchText = $("#search").val();
              var page = $("#text").val();
              var pagesize = $("#pageSize").val();
              var sort = $("#sort").val();
              var url = "${pageContext.request.contextPath }/page/question/show-list-question?pagesize="+pagesize;
              url += "&currentPage="+page+"&sort="+sort+"&searchText="+searchText;
              var reg = /^[0-9]+$/;
              if (reg.test(page)) {
                  $(window).attr('location',url);
              } else {
                  alert("Please input right number!");
              }
          });

         
              var sort = $("#sort").val();
              if(sort == "ASC"){
                  $(".question_list_title .question_list_two img").attr("src",
                          "${pageContext.request.contextPath }/static/imges/ICN_Increase_10x15.png.png");
              }else if(sort == "DESC") {
                  $(".question_list_title .question_list_two img").attr("src",
                  "${pageContext.request.contextPath }/static/imges/ICN_Decrese_10x15.png.png");
              }else {
                  $(".question_list_title .question_list_two img").attr("src",
                  "${pageContext.request.contextPath }/static/imges/ICN_Increase_10x15.png.png");
              }
          
          

          $(".question_list_title .question_list_two img").click(function(){
              var src = $(this).attr("src");
              var changeSort = null;
              var dec = "${pageContext.request.contextPath }/static/imges/ICN_Decrese_10x15.png.png";
              var inc = "${pageContext.request.contextPath }/static/imges/ICN_Increase_10x15.png.png";
              if (src == inc){
                  $(this).attr("src", "${pageContext.request.contextPath }/static/imges/ICN_Decrese_10x15.png.png");
                  changeSort = "DESC";
              }else {
                  $(this).attr("src", "${pageContext.request.contextPath }/static/imges/ICN_Increase_10x15.png.png");
                  changeSort = "ASC";
              }

              var searchText = $("#search").val();
              var page = $("#text").val();
              var pagesize = $("#pageSize").val();
              var url = "${pageContext.request.contextPath }/page/question/show-list-question?pagesize="+pagesize;
              url += "&currentPage="+page+"&sort="+changeSort+"&searchText="+searchText;
              $(window).attr('location',url);
          });

          /* delete question */
          $("#questionListPage #del").click(function(){
              var flag = false;
              var imgSelectedObjs = $("#questionListTbody .question_list_five img");
              var selecteSrc = "${pageContext.request.contextPath }/static/style/images/ICN_Selected_15x15.png.png";
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
              var selecteSrc = "${pageContext.request.contextPath }/static/style/images/ICN_Selected_15x15.png.png";
              var arrays = new Array();
              for (var i = 0; i < imgSelectedObjs.length; i++){
                  var src = $(imgSelectedObjs[i]).attr("src");
                  if (src == selecteSrc){
                      var id = $(imgSelectedObjs[i]).attr("id");
                      arrays[i] = id;
                  }
              }
              var str = arrays.toString();
              var urlList = "${pageContext.request.contextPath }/page/question/delete-question";
              var jsonList = {"arrays" : str};
              $.ajax({
                  url: urlList,
                  type: "post",
                  data: jsonList,
                  dataType: "json",
                  success: function(data) {
                      for (var i = 0; i < arrays.length; i++) {
                          for (var j = 0; j < imgSelectedObjs.length; j++) {
                              var id = $(imgSelectedObjs[i]).attr("id");
                              if (arrays[i] == id) {
                                  $(imgSelectedObjs[i]).parent().parent().remove();
                              }
                          }
                      }
                      $("#popupWindow").css("display", "none");
                  },
                  error: function(data) {
                      alert("delete error!");
                      $("#popupWindow").css("display", "none");
                  }
              });
              //$(window).attr('location',url);
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
              var url = "${pageContext.request.contextPath }/page/question/show-list-question?searchText="+searchText;
              url += "&currentPage="+currentPage+"&pagesize="+pagesize+"&sort="+sort;
              $(window).attr('location',url);
          });
          var searchT=$("#searchText").val();
          $("#search").val(searchT);

          /* question information */
          $("#questionListTbody .question_list .question_list_three").click(function(){
              var id=$(this).children("#qId").val();
              url = "${pageContext.request.contextPath }/page/question/question-information?qid="+id;
              $(window).attr('location',url);
          });

          /* edit */
          $("#questionListTbody .question_list_four img").click(function(){
              var id = $(this).attr("id");
              url = "${pageContext.request.contextPath }/page/question/to-edit?qid="+id;
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
            <li id="questionmanagement" class="selected">
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
      <div id="path"><div id="pathInformation"></div></div>
      <div id="content">
        <block:display name="questionListBlock"/>
      </div>
      <div id="footer"> Copyright 2017 @ Augmentum, Perkins. All Rights Reserved </div>
    </div>
    <div id="popupWindow">
      <div id="popContent">
        <img alt="close" src="${pageContext.request.contextPath }/static/imges/BTN_Close_16x16.png.png">
        <span>Are sure delete the selected options</span>
        <div id="popInput">
          <input type="button" id="yes" value="Yes" />
          <input type="button" id="no" value="No" />
        </div>
      </div>
    </div>
  </body>
</html>