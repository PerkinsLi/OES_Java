<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/block.tld" prefix="block" %>
<!DOCTYPE>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
  </head>
  <body>
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
                      <img src="${pageContext.request.contextPath }/static/style/images/ICN_Unselected_15x15.png.png" />
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
                           src="${pageContext.request.contextPath }/static/style/images/ICN_Edit_15x15.png.png" />
                       </td>
                       <td class="question_list_five">
                         <img id="${q.id }" 
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
                    <a href="${pageContext.request.contextPath }/page/question/show-list-question?currentPage=${page.currentPage-1}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                    <img alt="pageLeft" 
                      src="${pageContext.request.contextPath }/static/imges/BTN_PageLeft_20x15.png.png">
                    </a>
                  </div>
                  <div id="one">
                    <input type="hidden" id="pageSize" value="${page.pageSize}" />
                    <input type="hidden" id="pageCount" value="${page.pageCount }" />
                    <input type="hidden" id="sort" value="${page.sort }" />
                    <input type="hidden" id="searchText" value="${searchText }" />
                    <a href="${pageContext.request.contextPath }/page/question/show-list-question?currentPage=${page.currentPage}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                      ${page.currentPage }
                    </a>
                  </div>
                  <div id="two">
                    <a href="${pageContext.request.contextPath }/page/question/show-list-question?currentPage=${page.currentPage+1}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                      ${page.currentPage+1}
                    </a>
                  </div>
                  <div id="three">
                    <a href="${pageContext.request.contextPath }/page/question/show-list-question?currentPage=${page.currentPage+2}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                      ${page.currentPage+2}
                    </a>
                  </div>
                  <div id="omit">...</div>
                  <div id="lastPage">
                    <a href="${pageContext.request.contextPath }/page/question/show-list-question?currentPage=${page.pageCount }&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                      ${page.pageCount }
                    </a>
                  </div>
                  <div id="pageRight">
                    <a href="${pageContext.request.contextPath }/page/question/show-list-question?currentPage=${page.currentPage+1}&pagesize=${page.pageSize}&sort=${page.sort}&searchText=${searchText }">
                    <img alt="pageRight" 
                      src="${pageContext.request.contextPath }/static/imges/BTN_PageRight_20x15.png .png">
                    </a>
                  </div>
                  <div id="selector">
                    <select id="questionListPageSelect">
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                      <option value="4">4</option>
                      <option value="5">5</option>
                      <option value="6">6</option>
                      <option value="7">7</option>
                      <option value="8">8</option>
                      <option value="9">9</option>
                      <option value="10">10</option>
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
  </body>
</html>