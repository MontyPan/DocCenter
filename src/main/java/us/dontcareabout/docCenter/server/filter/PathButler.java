package us.dontcareabout.docCenter.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import us.dontcareabout.docCenter.shared.vo.Document;

@WebFilter(urlPatterns="/*")
public class PathButler implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String pathInfo = req.getPathInfo();

		if (pathInfo == null) {	//在 Filter 裡頭好像會抓不到 pathInfo，所以自己再搞一次
			pathInfo = req.getRequestURI().substring(req.getContextPath().length());
		}

		Document doc = new Document(pathInfo);
		request.setAttribute("doc", doc);

		if ("flow".equals(doc.getType())) {
			request.getRequestDispatcher("/flow.jsp").forward(request, response);
			return;
		}

		if ("md".equals(doc.getType())) {
			request.getRequestDispatcher("/markdown.jsp").forward(request, response);
			return;
		}

		//如果都不符合，就回歸預設的處理方式
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}