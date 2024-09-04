<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Membership</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.signup-container {
	display: flex;
	width: 900px; /* Adjust the width as needed */
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	overflow: hidden;
}

.welcome-section {
	flex: 1;
	background: #8E2DE2; /* fallback for old browsers */
	background: -webkit-linear-gradient(to left, #4A00E0, #8E2DE2);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to left, #4A00E0, #8E2DE2);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	color: white;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	padding: 60px;
	background: -webkit-linear-gradient(to left, #4A00E0, #8E2DE2);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to left, #4A00E0, #8E2DE2);
}

.welcome-section h2 {
	font-size: 28px;
	margin-bottom: 20px;
}

.welcome-section p {
	font-size: 16px;
	margin-bottom: 40px;
	text-align: center;
}

.welcome-section button {
	padding: 12px 30px;
	background-color: transparent;
	color: white;
	border: 2px solid white;
	border-radius: 4px;
	font-size: 16px;
	cursor: pointer;
}

.welcome-section button:hover {
	background-color: rgba(255, 255, 255, 0.2);
}

.signup-section {
	flex: 1;
	padding: 60px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	background-color: #ffffff;
}

.signup-section h2 {
	margin: 0;
	font-size: 28px;
	color: #0044cc;
	font-weight: bold;
}

.signup-section .social-buttons {
	display: flex;
	margin: 20px 0;
}

.signup-section .social-buttons a {
	margin: 0 10px;
	font-size: 18px;
	color: #555;
	text-decoration: none;
}

.signup-section .social-buttons a:hover {
	color: #0044cc;
	text-decoration:none;
}

.signup-section input[type="text"], .signup-section input[type="password"],
	.signup-section input[type="email"] , .signup-section input[type="tel"]
,.signup-section input[type="date"]{
	width: 100%;
	padding: 12px;
	margin: 8px 0;
	border: 1px solid #ddd;
	border-radius: 4px;
	box-sizing: border-box;
	font-size: 14px;
}

.signup-section button {
	width: 100%;
	padding: 12px;
	background: #8E2DE2; /* fallback for old browsers */ background :
	-webkit-linear-gradient( to left, #4A00E0, #8E2DE2);
	/* Chrome 10-25, Safari 5.1-6 */ background : linear-gradient( to left,
	#4A00E0, #8E2DE2);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
	margin-top: 20px;
	background: -webkit-linear-gradient(to left, #4A00E0, #8E2DE2);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to left, #4A00E0, #8E2DE2);
}

.signup-section button:hover {
	background-color: #003bb3;
}
</style>
<script type="text/javascript">
	// Function to show alert and redirect after form submission
	function showAlertAndRedirect() {
		alert("회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.");
		window.location.href = "${pageContext.request.contextPath}/user/login";
	}
</script>
</head>
<body>
	<div class="signup-container">
		<div class="welcome-section">
			<h2>Welcome Back!</h2>
			<p>To keep connected with us please login with your personal info</p>
			<button
				onclick="location.href='${pageContext.request.contextPath}/user/login'">Sign
				In</button>
		</div>
		<div class="signup-section">
			<h2>회원가입</h2>
			<div class="social-buttons">
				<a href="#"><i class="fa fa-facebook"></i> Facebook</a> <a href="#"><i
					class="fa fa-google"></i> Google</a> <a href="#"><i
					class="fa fa-linkedin"></i> LinkedIn</a>
			</div>
			<form action="/user/join"
				method="post" onsubmit="showAlertAndRedirect()">
				<label for="memberName">이름</label>
				<input type="text" name="memberName" id="memberName" required>
				<label for="memberId">아이디</label>
				<input type="text" name="memberId" id="memberId" required>
				<label for="memberPass">비밀번호</label>
				<input type="password" id="memberPass" required>
				<label for="checkMemberPass">비밀번호확인</label>
				<input type="password" name="memberPass" id="checkMemberPass" required>
				<label for="memberEmail">이메일</label>
				<input type="email" name="memberEmail" id="memberEmail" required>
				<label for="memberBirth">생일</label>
				<input type="date" name="memberBirth" id="memberBirth" required>
				<label for="memberTel">전화번호</label>
				<input type="tel" name="memberTel" id="memberTel" required>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="hidden" name="memberRole" value="ROLE_USER"/>

				<button type="submit">Sign Up</button>
			</form>
		</div>
	</div>
</body>
</html>
