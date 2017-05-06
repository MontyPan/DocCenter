<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="us.dontcareabout.docCenter.server.*"%>
<%@page import="us.dontcareabout.docCenter.shared.vo.*"%>

<%
Document doc = (Document) request.getAttribute("doc");
String[] lines = RepoButler.instance.getContext(doc).split("\n");
//還有一個 scriptList（String[]）交由各頁面自行宣告
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<% for (String script : scriptList) { %>
<script src="<%=script %>"></script>
<% } %>

<script>
var context =
<% for (String line : lines) { %>
	"<%=JspUtil.toJsString(line)%>\n" +
<% } %>
		"";
</script>
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