<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="part/header.jsp" %>
<strong>ログイン</strong>
<br>
<form action="/login" name="form" method="post" onsubmit="return validateForm()">
    <table class="table-form">
        <tr>
            <th>アカウント</th>
            <td>
                <input type="text" name="id" value="${loginVO.id}" required />
            </td>
        </tr>
        <tr>
            <th>パスワード</th>
            <td>
                <input type="password" name="pass" required />
            </td>
        </tr>
    </table>
    <button type"submit" class="btn-primary">サインイン</button>
    <br>
    <br>
    <a href="#" onclick="alert(''); return false;">パスワードをお忘れの方はこちらへ</a>
</form>
<script>
function validateForm() {
    var $form = document.forms["form"];
    var id = $form["id"].value;
    var pass = $form["pass"].value;

    if (!id) {
        alert("アカウントを入力してください。");
        return false;
    } else if (id.length < 4 || id.length > 20) {
        alert("4 ~ 20字以内でお願いいたします。");
        return false;
    } else if (!id.match(/^[a-zA-Z0-9]+$/)) {
        alert("英文字と数字のみ入力してください。");
        return false;
    }

    if (!pass) {
        alert("パスワードを入力してください。");
        return false;
    } else if (pass.length < 8 || pass.length > 32) {
        alert("8 ~ 32字以内でお願いいたします。");
        return false;
    } else if (!pass.match(/^[a-zA-Z0-9]+$/)) {
        alert("英文字と数字のみ入力してください。");
        return false;
    } else if (!hasUpperCase(pass)) {
        alert("１字以上を大文字でお願いいたします。");
        return false;
    }

    if (id != "admin" || pass != "Admin1234") {
        alert("アカウントとかパスワードが間違っております。");
        return false;
    }
}
function hasUpperCase(str) {
    for (x=0; x<str.length; x++)
        if (str.charAt(x) >= 'A' && str.charAt(x) <= 'Z')
            return true;

    return false;
}
</script>
<%@ include file="part/footer.jsp" %>
