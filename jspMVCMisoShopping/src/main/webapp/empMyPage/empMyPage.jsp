<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>employeeDetail.jsp</title>
</head>
<body>
${dto }
	<ul>
		<li><a href="<c:url value='/' />">홈</a></li>
		<li><a href="empMyPage.my">내정보 보기</a></li>
		<li><a href="empUpdate.my">내정보 수정</a></li>
		<li><a href="empPwModify.my">비밀번호변경</a></li>
	</ul>
직원 상세 정보 <br />
직원번호 : ${dto.empNum }<br />
직원명 : ${dto.empName }<br />
직원아이디: ${dto.empId }<br />
직원연락처: ${dto.empPhone }<br />
직원이메일: ${dto.empEmail }<br />
직원주소: ${dto.empAddr }<br />
직원상세주소: ${dto.empAddrDetail }<br />
직원우편번호: ${dto.empPost }<br />
직원주민번호: ${dto.empJumin }<br />
직원입사일: ${dto.empHireDate }<br />
</body>
</html>