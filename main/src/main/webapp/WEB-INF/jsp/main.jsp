<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>site</title>
<link rel="stylesheet" href="style.css" />
</head>
<body>
<strong>${loginId}</strong>様がログイン中です。
<a href="/logout" class="btn-danger btn-xs">サインアウト</a>
<br>
<br>
	<table class="table table-hover">
		<thead>
			<tr>
				<th class="">제목</th>
				<th class="">저자</th>
				<th class="">출판사</th>
				<th class="">출판일</th>
				<th class="">가격</th>
			</tr>	
		</thead>
		<tbody>
			<tr th:each="board : ${BookVO}">
				<td><a th:href="'/BookVO?id='+${BookVO.id}" th:text="${BookVO.title}"></a></td>
				<td><a th:href="'/BookVO?id='+${BookVO.id}" th:text="${BookVO.author}"></a></td>
				<td><a th:href="'/BookVO?id='+${BookVO.id}" th:text="${BookVO.publisher}"></a></td>
				<td><a th:href="'/BookVO?id='+${BookVO.id}" th:text="${BookVO.date}"></a></td>
				<td><a th:href="'/BookVO?id='+${BookVO.id}" th:text="${BookVO.cost}"></a></td>
		</tbody>
	</table>
<a href="/bookCreate" class="btn-primary">登録</a>
<br>
<br>
</body>
</html>
