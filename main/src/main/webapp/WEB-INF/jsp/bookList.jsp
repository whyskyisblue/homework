<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>site</title>
<link rel="stylesheet" href="style.css" />
</head>
<body>
<div class="wrap">
	<div style="text-align: right;">
		<strong>${loginId}</strong>様がログイン中です。<a href="/logout" class="btn-danger btn-xs">サインアウト</a>
    </div>
    
    <br>
    <br>
    <div style="overflow: hidden; margin: 12px 0;">
        <p style="float: left; margin: 0;">本一覧 (本の総合: <fmt:formatNumber value="${totalCount}" pattern="#,###" />)</p>
        <form action="/bookList" method="get" style="float: right;" class="search-form">
            <input type="hidden" name="page" value="${page}" />
            <input type="hidden" name="asc" value="${asc}" />
            <input type="text" name="keyword" value="${keyword}" autofocus />
            <input type="submit" class="btn-primary btn-small" value="검색" />
        </form>
    </div>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>
                    <c:choose>
                        <c:when test="${asc}">
                            <a href="/bookList?page=${page}&asc=false&amp;keyword=${keyword}">タイトル▲</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/bookList?page=${page}&asc=true&amp;keyword=${keyword}">タイトル▼</a>
                        </c:otherwise>
                    </c:choose>
                </th>
                <th>著書</th>
                <th>出版社</th>
                <th>出版日</th>
                <th style="text-align: right;">値段</th>
                <th>&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty bookVOList}">
                    <tr>
                        <td colspan="6" style="text-align: center; color: red;">
                            登録された本がありません。
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="bookVO" items="${bookVOList}">
                        <tr>
                            <td><a href="/bookUpdate?id=${bookVO.id}&page=${page}&asc=${asc}&amp;keyword=${keyword}">${bookVO.title.length() > 10 ? bookVO.title.substring(0, 10).concat('...') : bookVO.title}</a></td>
                            <td>${bookVO.author}</td>
                            <td>${bookVO.publisher}</td>
                            <td><fmt:formatDate value="${bookVO.date}" type="date" pattern="yyyy年 M月 d日" /></td>
                            <td style="text-align: right;"><fmt:formatNumber value="${bookVO.cost}" pattern="#,###" />円</td>
                            <td style="width: 80px;"><a href="/bookDelete?id=${bookVO.id}&page=${page}&asc=${asc}&amp;keyword=${keyword}" class="btn-danger btn-small" onclick="return confirm('削除しますか?');">削除</a></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    <div style="margin: 12px auto; text-align: center;">
        <c:if test="${!empty pageBlockPrev}">
            <a href="?page=${pageBlockPrev}&amp;asc=${asc}&amp;keyword=${keyword}">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${pageBlockStart}" end="${pageBlockEnd}">
            <a href="?page=${i}&amp;asc=${asc}&amp;keyword=${keyword}">
                <c:choose>
                    <c:when test="${page == i}">
                        <strong>[${i}]</strong>
                    </c:when>
                    <c:otherwise>
                        ${i}
                    </c:otherwise>
                </c:choose>
            </a>
        </c:forEach>
        <c:if test="${!empty pageBlockNext}">
            <a href="?page=${pageBlockNext}&amp;asc=${asc}&amp;keyword=${keyword}">&gt;</a>
        </c:if>
    </div>
    <div style="margin: 12px auto; text-align: right;">
        <a href="/bookCreate?page=${page}&asc=${asc}&amp;keyword=${keyword}" class="btn-primary">登録</a>
    </div>
</div>
</body>
</html>
