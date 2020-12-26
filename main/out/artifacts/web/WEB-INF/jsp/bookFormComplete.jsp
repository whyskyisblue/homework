<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="part/header.jsp" %>
<c:choose>
    <c:when test="${action == 'create'}">
        <strong>登録完了</strong>
    </c:when>
    <c:otherwise>
        <strong>修正完了</strong>
    </c:otherwise>
</c:choose>
<br>
<br>
<a href="/bookList?page=${page}&amp;asc=${asc}&amp;keyword=${keyword}" class="btn-default">確認</a>
<%@ include file="part/footer.jsp" %>
