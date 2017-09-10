 package com.wangp.msp
 import com.wangp.msp.MspPervlet;
 public class jsp_my.jsp extends MspPervlet{
 public void handlerRequest(PvRequest req,PvReponse res){
 res.getOut().println("")
 res.getOut().println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
 res.getOut().println("<html>")
 res.getOut().println("<head>")
 res.getOut().println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">")
 res.getOut().println("<title>Insert title here</title>")
 res.getOut().println("</head>")
 res.getOut().println("<body>")
 res.getOut().println("")

     String user=request.getParam("user");
     String password=request.getParam("password");
     if("aaaa".equals(user)&&"123456".equals(password)){
    	 
    	 out.println("Welcome:"+user);
     }else{
    	 out.println("Sorry,incorrect passowrd:"+user);
     }
 res.getOut().println("")
 res.getOut().println("")
 res.getOut().println("")
 res.getOut().println("</body>")
 res.getOut().println("</html>")
}
