<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="us.dontcareabout.docCenter.server.*"%>
<%@page import="us.dontcareabout.docCenter.shared.vo.*"%>

<% //跟 template.jsp 有 87% 像，但是目前懶得抽共用了 XDDD %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body{
	height: 100vh;
	padding: 0px;
	margin: 0px;
}
div {
	width: 100%;
	height: 100%;
	overflow: auto;
}
</style>
</head>

<body>
<table width="100%" height="100%">
	<tr>
		<td width="30%" >
			<div>
			<%@include file="content.jsp" %>
			</div>
		</td>
		<td width="70%">
			<div id="context"></div>
		</td>
	</tr>
</table>
</body>
</html>