<%
String[] scriptList = {
	"https://cdn.rawgit.com/showdownjs/showdown/1.6.3/dist/showdown.min.js",
	"//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.11.0/highlight.min.js",
};
%>
<%@include file="template.jsp" %>
<link rel="stylesheet" type="text/css" href="https://sindresorhus.com/github-markdown-css/github-markdown.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.11.0/styles/default.min.css">
<script>
//reference: https://github.com/showdownjs/showdown/issues/215#issuecomment-168679324
showdown.extension('codehighlight', function() {
	function htmlunencode(text) {
		return (
			text.replace(/&amp;/g, '&')
				.replace(/&lt;/g, '<')
				.replace(/&gt;/g, '>')
		);
	}
	return [
		{
			type: 'output',
			filter: function (text, converter, options) {
				// use new shodown's regexp engine to conditionally parse codeblocks
				var left	= '<pre><code\\b[^>]*>',
					right = '</code></pre>',
					flags = 'g',
					replacement = function (wholeMatch, match, left, right) {
						// unescape match to prevent double escaping
						match = htmlunencode(match);
						return left + hljs.highlightAuto(match).value + right;
					};
				return showdown.helper.replaceRecursiveRegExp(text, replacement, left, right, flags);
			}
		}
	];
});
////

var converter = new showdown.Converter({extensions: ['codehighlight']});
converter.setFlavor('github');
var container = document.getElementById("context");
container.classList.add('markdown-body');	//github-markdown-css 需要 container 掛上這個 class
container.innerHTML = converter.makeHtml(context);
</script>