<%
String[] scriptList = {
	"http://viz-js.com/bower_components/viz.js/viz.js"
};
%>
<%@include file="template.jsp" %>
<script>
document.getElementById("context").innerHTML = Viz(context);
</script>
</html>