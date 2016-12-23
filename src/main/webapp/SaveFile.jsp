<%@ page language="Java" import="java.util.*,com.zhuozhengsoft.pageoffice.*" pageEncoding="UTF-8"%>
<%
String contextPath = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";


FileSaver fs = new FileSaver(request, response);  //保存Word文档到相应的磁盘路径中   
fs.saveToFile(request.getSession().getServletContext().getRealPath("/") + "/" + fs.getFileName());   
//fs.showPage(300,300);//可在前台弹出相应大小的窗口，并在上面显示想要显示的信息内容
fs.close(); 
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
  </head>
  
  <body>
  <% 
  request.setCharacterEncoding("utf-8");
  response.setCharacterEncoding("utf-8");
  %>
  </body>
</html>
