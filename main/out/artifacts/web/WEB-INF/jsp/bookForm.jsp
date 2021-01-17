<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="part/header.jsp" %>

<div style="text-align: right; margin:20px,0">
		<strong>${loginId}</strong>様がログイン中です。<a href="/logout" class="btn-danger btn-xs">サインアウト</a>
</div>
<br> 
<br>
<c:choose>
    <c:when test="${action == 'create'}">
        <strong>本の登録</strong>
    </c:when>
    <c:otherwise>
        <strong>修正</strong>
    </c:otherwise>
</c:choose>
<br>
<form action="${actionUrl}" name="form" method="post" onsubmit="return validateForm()">
    <table class="table-form">
        <tr>
            <th><span class="required">*</span>タイトル</th>
            <td>
                <input type="text" name="title" value="${bookVO.title}" placeholder="100字以内で" required maxlength="100" />
            </td>
        </tr>
        <tr>
            <th><span class="required">*</span>著者</th>
            <td>
                <input type="text" name="author" value="${bookVO.author}" placeholder="50字以内で" required maxlength="50" />
            </td>
        </tr>
        <tr>
            <th>出版社</th>
            <td>
                <input type="text" name="publisher" value="${bookVO.publisher}" placeholder="50字以内で" maxlength="50" />
            </td>
        </tr>
        <tr>
            <th>発売日</th>
            <td>
                <fmt:formatDate value="${bookVO.date}" type="date" pattern="yyyy/M/d" var="date" />
                <input type="text" name="date" value="${date}" placeholder="yyyy/m/d" />
            </td>
        </tr>
        <tr>
            <th><span class="required">*</span>価額</th>
            <td>
                <input type="text" name="cost" value="${bookVO.cost}" required />
            </td>
        </tr>
    </table>
    <button type"submit" class="btn-primary">登録</button>
    <a href="/bookList?page=${page}&field=${field}&asc=${asc}&amp;keyword=${keyword}" class="btn-default">戻る</a>
</form>
<script>
function validateForm() {
    var $form = document.forms["form"];
    var title = $form["title"].value;
    var author = $form["author"].value;
    var publisher = $form["publisher"].value;
    var date = $form["date"].value;
    var cost = $form["cost"].value;

    if (!title) {
        alert("タイトルを書いてください");
        return false;
    } else if (title.length > 100) {
        alert("最大100以内でお願いいたします。");
        return false;
    }

    if (!author) {
        alert("著者を書いてください");
        return false;
    } else if (author.length > 50) {
        alert("最大50以内でお願いいたします。");
        return false;
    }

    if (publisher && publisher.length > 50) {
        alert("最大50以内でお願いいたします。");
        return false;
    }

    if (date && !isValidDate(date)) {
        alert("yyyy/m/dに合わせてお願いいたします。");
        return false;
    }

    if (!cost) {
        alert("価額を書いてください");
        return false;
    } else if (cost * 1 < 0) {
        alert("価額を０以上で書いてください");
        return false;
    }
}
</script>
<%@ include file="part/footer.jsp" %>
