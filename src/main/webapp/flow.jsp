<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="us.dontcareabout.docCenter.server.*"%>
<%@page import="us.dontcareabout.docCenter.shared.vo.*"%>

<%
Document doc = (Document) request.getAttribute("doc");
String[] lines = RepoButler.instance.getContext(doc).split("\n");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://bramp.github.io/js-sequence-diagrams/js/webfont.js"></script>
<script src="https://bramp.github.io/js-sequence-diagrams/js/snap.svg-min.js"></script>
<script src="https://bramp.github.io/js-sequence-diagrams/js/underscore-min.js"></script>
<script src="https://bramp.github.io/js-sequence-diagrams/js/sequence-diagram-min.js"></script>
</head>
<body>
<div id="context"></div>
</body>
<script>
var context =
<%
for (String line : lines) {
	out.println("\t\"" + JspUtil.toJsString(line) + "\\n\" + ");
}
%>
	"";
var options = {
	theme: "simple",
	scale: 1
};
var diagram = Diagram.parse(context);
diagram.drawSVG(document.getElementById("context"), options);
</script>
</html>