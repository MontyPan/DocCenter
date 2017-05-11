<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="us.dontcareabout.docCenter.server.*"%>
<%@page import="us.dontcareabout.docCenter.shared.vo.*"%>

<%
RepoButler.instance.rebuild();
response.sendRedirect("index.jsp");
%>