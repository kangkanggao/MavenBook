package com.highlion.servlet.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.highlion.domain.BookVO;
import com.highlion.service.BookService;
import com.highlion.service.impl.BookServiceImpl;
import com.highlion.utils.MyBeanUtils;

@WebServlet("/bookAdd")
@MultipartConfig
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 文件上传
		Part part = request.getPart("photo");
		String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		fileName = fileName.indexOf("\\") == -1 ? fileName : fileName.substring(fileName.indexOf("\\") + 1);
		String newFileName = UUID.randomUUID().toString() + "_" + fileName;
		part.write(request.getServletContext().getRealPath("upload/" + newFileName));
        //通过book对象存储表单数据
		BookVO book = new BookVO();
		try {
			MyBeanUtils.populate(book, request.getParameterMap(), "yyyy-MM-dd");
			book.setPhoto(newFileName);
			//查看外键值是多少
			System.out.println(book.getTid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		BookService us = new BookServiceImpl();
		int ret = us.add(book);
        //获取验证码
		String vcode = request.getParameter("vcode");
		HttpSession session = request.getSession();
		String serverVcode = (String) session.getAttribute("validateCode");
		// 验证码
		if (!serverVcode.equalsIgnoreCase(vcode)) {
			// 验证失败
			request.setAttribute("msg", "验证码错误");
			request.getRequestDispatcher("addBook.jsp").forward(request, response);
			return;
		}

		if (ret==1) {
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else {
			// 失败
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
