package com.highlion.servlet.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.highlion.domain.User;
import com.highlion.service.UserLogin;
import com.highlion.service.impl.UserLoginImpl;
@WebServlet("/login")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginUserServlet() {
		super();

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        User user=new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		//判断验证码
		String vcode = request.getParameter("vcode");
		HttpSession session = request.getSession();
		String serverVcode = (String) session.getAttribute("validateCode");
		if (!serverVcode.equalsIgnoreCase(vcode)) {
			request.setAttribute("msg", "验证码错误");
			request.setAttribute("name", user.getName());
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//判断是否登录成功
		UserLogin us=new UserLoginImpl();
		Boolean ret=us.login(user);
		if (ret) {
			request.setAttribute("name", user.getName());
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "用户名或密码错误");
			request.setAttribute("name", user.getName());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}