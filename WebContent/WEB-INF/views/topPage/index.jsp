<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>日報管理システムへようこそ</h2>
        <h3>【自分の日報 一覧】</h3>
        <table id="report_list">
            <tbody>
                <tr>
                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_go_time">出勤時間</th>
                    <th class="report_leave_time">退勤時間</th>
                    <th class="report_action">操作</th>
                    <th class="iine">いいね</th>
                </tr>
                <c:forEach var="report" items="${reports}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out
                                value="${report.employee.name}" /></td>
                        <td class="report_date"><fmt:formatDate
                                value='${report.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="report_title">${report.title}</td>
                        <td class="report_go_time">${report.go_time}</td>
                        <td class="report_leave_time">${report.leave_time}</td>
                        <td class="report_action"><a
                            href="<c:url value='reports/show?id=${report.id}' />">詳細を見る</a></td>
                        <td class="report_iine"><c:if
                                test="${report.search =='true'}">
                                <c:set var="data" />
                                <c:out value="${data}" />
                                <form method="POST"
                                    action="${pageContext.request.contextPath}/reports/delete">
                                    <input type="hidden" name="_token" value="${_token}" /> <input
                                        type="hidden" name="report_id" value="${report.id}" />
                                    <button type="submit">
                                        <img src="${pageContext.request.contextPath}/img/favorite.png"
                                            alt="いいね">
                                    </button>

                                </form>
                            </c:if> <c:out value="${report.search}" /> <c:if
                                test="${report.search == 'false'}">
                                <c:set var="data" />
                                <c:out value="${data}" />
                                <form method="POST"
                                    action="${pageContext.request.contextPath}/reports/iine">

                                    <input type="hidden" name="_token" value="${_token}" /> <input
                                        type="hidden" name="report_id" value="${report.id}" />
                                    <button type="submit">
                                        <img src="${pageContext.request.contextPath}/img/blank.png"
                                            alt="いいね">
                                    </button>





                                </form>
                            </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            (全 ${reports_count} 件)<br />
            <c:forEach var="i" begin="1" end="${((reports_count - 1) / 15) + 1}"
                step="1">
                <c:choose>
                    <c:when test="${i == page }">
                        <c:out value="${i}" />&nbsp;
                </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p>
            <a href="<c:url value='/reports/new' />">新規日報の登録</a>
        </p>
    </c:param>
</c:import>