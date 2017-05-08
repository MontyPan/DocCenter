<%
String[] scriptList = {
	"https://bramp.github.io/js-sequence-diagrams/js/webfont.js",
	"https://bramp.github.io/js-sequence-diagrams/js/snap.svg-min.js",
	"https://bramp.github.io/js-sequence-diagrams/js/underscore-min.js",
	"https://bramp.github.io/js-sequence-diagrams/js/sequence-diagram-min.js",
};
%>
<%@include file="template.jsp" %>
<script>
var options = {
	theme: "simple",
	scale: 1
};
var diagram = Diagram.parse(context);
diagram.drawSVG(document.getElementById("context"), options);
</script>