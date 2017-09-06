<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
     String user=request.getParam("user");
     String password=request.getParam("password");
     if("aaaa".equals(user)&&"123456".equals(password)){
    	 
    	 out.println("Welcome:"+user);
     }else{
    	 out.println("Sorry,incorrect passowrd:"+user);
     }
%>


</body>
</html>