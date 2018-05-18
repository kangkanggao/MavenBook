package com.highlion.servlet.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highlion.domain.TypeVO;
import com.highlion.service.TypeService;
import com.highlion.service.impl.TypeServiceImpl;

@WebServlet("/findAllTypes")
public class FindAllTypesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TypeService ts = new TypeServiceImpl();
		List<TypeVO> list = ts.findAllTypes();
		// 页面显示javascript类型
		response.setContentType("text/javascript;charset=utf-8");
		String js = "var types= [";
		for (int i = 0; i < list.size(); i++) {
			js += "{id:" + list.get(i).getid() + ",name:'" + list.get(i).getName() + "'}";
			if (i < list.size() - 1) {
				js += ",";
			}
		}
		js += "]";
		System.out.println(js);
		response.getWriter().write(js);
	}
}