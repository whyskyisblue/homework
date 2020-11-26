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
    <p>本一覧</p>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>
                    <c:choose>
                        <c:when test="${asc}">
                            <a href="/bookList?asc=false">タイトル▲</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/bookList?asc=true">タイトル▼</a>
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
                            <td><a href="/bookUpdate?id=${bookVO.id}">${bookVO.title}</a></td>
                            <td>${bookVO.author}</td>
                            <td>${bookVO.publisher}</td>
                            <td><fmt:formatDate value="${bookVO.date}" type="date" pattern="yyyy年 M月 d日" /></td>
                            <td style="text-align: right;"><fmt:formatNumber value="${bookVO.cost}" pattern="#,###" />円</td>
                            <td><a href="/bookDelete?id=${bookVO.id}" class="btn-danger btn-small" onclick="return confirm('削除しますか?');">削除</a></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    <div>
     <c:forEach begin="1" end="${pageNum}" var="num">
        <span>
         <a href="/board/listPage?num=${num}">${num}</a>
      </span>
     </c:forEach>
    </div>
    <div style="text-align: right; margin: 20px 0;">
        <a href="/bookCreate" class="btn-primary">登録</a>
    </div>
</div>
</body>
</html>
