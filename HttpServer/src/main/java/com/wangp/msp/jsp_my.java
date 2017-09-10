 package com.wangp.msp;
 import com.wangp.msp.MspPervlet;
 import com.wangp.msp.interfaces.OutPutStream;
 import com.wangp.msp.interfaces.PvReponse;
 import com.wangp.msp.interfaces.PvRequest;
 public class jsp_my extends MspPervlet{
 public  jsp_my () {
 super();
 	}
 public  jsp_my (PvRequest req, PvReponse res, OutPutStream out) {
 super(req, res, out);
 	}
 public void handlerRequest(){
 response.getOut().println("");
 response.getOut().println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
 response.getOut().println("<html>");
 response.getOut().println("<head>");
 response.getOut().println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
 response.getOut().println("<title>Insert title here</title>");
 response.getOut().println("</head>");
 response.getOut().println("<body>");
 response.getOut().println("");

     String user=(String) request.getParam("user");
     String password=(String) request.getParam("password");
     if("aaaa".equals(user)&&"123456".equals(password)){
    	 
    	 out.println("Welcome:"+user);
     }else{
    	 out.println("Sorry,incorrect passowrd:"+user);
     }
 response.getOut().println("");
 response.getOut().println("");
 response.getOut().println("");
 response.getOut().println("</body>");
 response.getOut().println("</html>");
}
}
