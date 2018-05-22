package com.highlion.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebFilter("/*")
public class AuthorFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain doChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String url=request.getRequestURI();
		if(url.contains("/bower_components/")||url.endsWith("/login.jsp")||url.endsWith("/login")||url.endsWith("/vcode.png")) {
			doChain.doFilter(req,resp);
			return;
		}
		if (request.getSession().getAttribute("loginSuccess") == null|| !request.getSession().getAttribute("loginSuccess").equals("1")) {
           response.sendRedirect("login.jsp");
           return ;
		} else {
			
            doChain.doFilter(req,resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
