<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath }/static/style/contentadmin.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath }/static/style/exam_list.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.10.2.min.js"></script>
    <title>Exam Management</title>
    <script type="text/javascript">
        $(function(){
            $("#navigation li").click(function(){
                $(this).addClass("selected").siblings().removeClass("selected");
            });
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
            <li >
              <a href="${pageContext.request.contextPath }/page/question/show-list-question">
                Question Management
              </a>
            </li>
            <li id="exammanagement" class="selected">
              <a href="${pageContext.request.contextPath }/page/exam/show-list-exam">
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
            <div id="dateBox">
              <input type="button" id="date" value="Date" />
              <input type="text" id="secondDate" />
              <div id="iconBox"><div id="iconLine"></div></div>
              <input type="text" id="firstDate" placeholder="2017-07-10" />
            </div>
          </div>
          <div id="questionManagementContent">
            <div id="questionManagementLeft">
              <ul>
                <li class="listOne">Exam List</li>
                <li class="listTwo">Create Exam</li>
              </ul>
            </div>
            <div id="questionManagementRight">
              <div id="listTable">
                <table class="question_table">
                  <tr class="question_list_title">
                    <td class="question_list_one">1</td>
                    <td class="question_list_two">
                      ID<img alt="" >
                    </td>
                    <td class="question_list_three">Name</td>
                    <td class="question_list_four">Effective Time</td>
                    <td class="question_list_five">
                      Duration(Mins)
                    </td>
                    <td class="question_list_six">Creator</td>
                    <td class="question_list_seven">Edit</td>
                    <td class="question_list_eight">
                      <img src="${pageContext.request.contextPath }/static/style/images/ICN_Unselected_15x15.png.png" />
                    </td>
                  </tr>
                 <tbody id="questionListTbody">
                   <c:forEach items="" var="q" varStatus="status">
                     <tr class="question_list" id="question_list">
                       <td class="question_list_one">1</td>
                       <td class="question_list_two" >E000001</td>
                       <td class="question_list_three">
                         Performance
                       </td>
                       <td class="question_list_four" ></td>
                       <td class="question_list_five" ></td>
                       <td class="question_list_six" ></td>
                       <td class="question_list_seven">
                         <img id="" 
                           src="${pageContext.request.contextPath }/static/style/images/ICN_Edit_15x15.png.png" />
                       </td>
                       <td class="question_list_eight">
                         <img id="" 
                           src="${pageContext.request.contextPath }/static/style/images/ICN_Unselected_15x15.png.png" />
                       </td>
                     </tr>
                   </c:forEach>
                 </tbody>
               </table>
             </div>
              <div id="questionListPage">
                <div id="pageSign">
                  <div id="pageLeft">
                    <a href="${pageContext.request.contextPath }/page/question/showListQuestion?currentPage=${page.currentPage-1}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                    <img alt="pageLeft" 
                      src="${pageContext.request.contextPath }/static/imges/BTN_PageLeft_20x15.png.png">
                    </a>
                  </div>
                  <div id="one">
                    <input type="hidden" id="pageSize" value="" />
                    <input type="hidden" id="pageCount" value="" />
                    <input type="hidden" id="sort" value="" />
                    <input type="hidden" id="searchText" value="" />
                    <a href="${pageContext.request.contextPath }/page/question/showListQuestion?currentPage=${page.currentPage}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                      1
                    </a>
                  </div>
                  <div id="two">
                    <a href="${pageContext.request.contextPath }/page/question/showListQuestion?currentPage=${page.currentPage+1}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                      2
                    </a>
                  </div>
                  <div id="three">
                    <a href="${pageContext.request.contextPath }/page/question/showListQuestion?currentPage=${page.currentPage+2}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                      3
                    </a>
                  </div>
                  <div id="omit">...</div>
                  <div id="lastPage">
                    <a href="${pageContext.request.contextPath }/page/question/showListQuestion?currentPage=${page.pageCount }&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                      7
                    </a>
                  </div>
                  <div id="pageRight">
                    <a href="${pageContext.request.contextPath }/page/question/showListQuestion?currentPage=${page.currentPage+1}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                    <img alt="pageRight" 
                      src="${pageContext.request.contextPath }/static/imges/BTN_PageRight_20x15.png .png">
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
  </body>
</html>