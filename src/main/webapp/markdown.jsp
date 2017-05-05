<%
String[] scriptList = {
	"https://cdn.rawgit.com/showdownjs/showdown/1.6.3/dist/showdown.min.js"
};
%>
<%@include file="template.jsp" %>
<script>
var converter = new showdown.Converter();
document.getElementById("context").innerHTML = converter.makeHtml(context);
</script>