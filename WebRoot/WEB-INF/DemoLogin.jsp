<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'DemoLogin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor=#EEFFAE><font size=2>
  <From action="servlet/ConnectServlet" Methon="post">
  <tr><td>  网关: <Input type=text  name="ip"></td></tr>
  <tr><td> 端口号:<Input type=text name="port"></td></tr>
  <Input type=submit name="g" value="连接">
  <tr><td>  连接情况</td></tr><tr><td><Input type=text name="IPPort"></td></tr>
  
  
  <br><TextArea  rows="6" cols="60">
      <jsp:getProperty name="fileBean" property="updateData"/>
      </TextArea>
 <tr><td><Input type=submit name="g" value="数据采集器"></td></tr>   
  
    <br><TextArea  rows="6" cols="60">
      <jsp:getProperty name="fileBean" property="updateData"/>
      </TextArea>
 <tr><td><Input type=submit name="gs" value="继电器"></td></tr>   
  </From></body>
</html>
