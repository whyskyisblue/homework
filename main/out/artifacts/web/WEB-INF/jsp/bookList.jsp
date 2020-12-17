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
    <p>本一覧 (本の総合: <fmt:formatNumber value="${totalCount}" pattern="#,###" />)</p>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>
                    <c:choose>
                        <c:when test="${asc}">
                            <a href="/bookList?page=${page}&asc=false">タイトル▲</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/bookList?page=${page}&asc=true">タイトル▼</a>
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
                            <td><a href="/bookUpdate?id=${bookVO.id}&page=${page}&asc=${asc}">${bookVO.title.length() > 10 ? bookVO.title.substring(0, 10).concat('...') : bookVO.title}</a></td>
                            <td>${bookVO.author}</td>
                            <td>${bookVO.publisher}</td>
                            <td><fmt:formatDate value="${bookVO.date}" type="date" pattern="yyyy年 M月 d日" /></td>
                            <td style="text-align: right;"><fmt:formatNumber value="${bookVO.cost}" pattern="#,###" />円</td>
                            <td style="width: 80px;"><a href="/bookDelete?id=${bookVO.id}&page=${page}&asc=${asc}" class="btn-danger btn-small" onclick="return confirm('削除しますか?');">削除</a></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    <div style="margin: 12px auto; text-align: center;">
        <c:if test="${!empty pageBlockPrev}">
            <a href="?page=${pageBlockPrev}&amp;asc=${asc}">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${pageBlockStart}" end="${pageBlockEnd}">
            <a href="?page=${i}&amp;asc=${asc}">
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
            <a href="?page=${pageBlockNext}&amp;asc=${asc}">&gt;</a>
        </c:if>
    </div>
    <div style="margin: 12px auto; text-align: right;">
        <a href="/bookCreate?page=${page}&asc=${asc}" class="btn-primary">登録</a>
    </div>
</div>
</body>
</html>
