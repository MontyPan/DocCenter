<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="us.dontcareabout.docCenter.server.*"%>
<%@page import="us.dontcareabout.docCenter.shared.vo.*"%>
<%@page import="java.util.List"%>

<%!
void printFolder(String pathHeader, List<Folder> folders, StringBuffer result) {
	for (Folder folder : folders) {
		result.append("<li>" + folder.getName() + "\n");
		result.append("\t<ul>\n");
		printFolder(pathHeader, folder.getFolder(), result);
		printFile(pathHeader, folder, result);	
		result.append("\t</ul>\n</li>\n");
	}
}

void printFile(String pathHeader, Folder folder, StringBuffer result) {
	for (String name : folder.getFile()) {
		result.append("\t<li><a href='" + pathHeader + folder.genPathInfo(name) + "'>" + name + "</a></li>\n");
	}
}
%>

<%for (String repo : RepoButler.instance.getRepoList()) {%>
	<h3><%=repo%></h3>
	<ul>
<%
	String pathHeader = request.getContextPath() + "/" + repo;
	Folder repoFolder = RepoButler.instance.findFolder(repo);
	StringBuffer result = new StringBuffer();
	printFolder(pathHeader, repoFolder.getFolder(), result);
	printFile(pathHeader, repoFolder, result);
	out.println(result);
%>
	</ul>
<%}%>