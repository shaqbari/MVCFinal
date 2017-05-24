<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	</head>
	<script>
		function regist(){
			form1.action="/student/regist.do";
			form1.method="post";
			form1.submit();
			
		}
		
	</script>
<body>
	<form name="form1">
		<pre><!--pre쓰면 html문서 형태가 그대로 반영된다. 왠만하면 쓰지말자.  -->
			<input type="text" name="id" placeholder="아이디" />
			<input type="text" name="password" placeholder="패스워드" />
			<input type="text" name="name" placeholder="이름" />
			
			<input type="text" name="blood" placeholder="혈액형">
			<input type="text" name="weight" placeholder="몸무게" value="65"/>
			<input type="button"  value="등록" onclick="regist();"/>
		</pre>
	</form>
</body>
</html>