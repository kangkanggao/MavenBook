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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Part part = request.getPart("photo");
		String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		fileName = fileName.indexOf("\\") == -1 ? fileName : fileName.substring(fileName.indexOf("\\") + 1);
		
		///hou zhui a !!!!!!!
		///hao hao kan kan biji a ????????ok
		String fn=fileName.substring(fileName.indexOf(".")+1);
		String newFileName = UUID.randomUUID().toString() + "." +fn;
		part.write(request.getServletContext().getRealPath("upload/" + newFileName));

		BookVO book = new BookVO();
		try {
			MyBeanUtils.populate(book, request.getParameterMap(), "yyyy-MM-dd");
			book.setPhoto(newFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		BookService us = new BookServiceImpl();
		int ret = us.add(book);
		String vcode = request.getParameter("vcode");
		HttpSession session = request.getSession();
		String serverVcode = (String) session.getAttribute("validateCode");
		// 楠岃瘉鐮�
		if (!serverVcode.equalsIgnoreCase(vcode)) {
			// 楠岃瘉澶辫触
			request.setAttribute("msg", "楠岃瘉鐮侀敊璇�");
			request.getRequestDispatcher("addBook").forward(request, response);
			return;
		}

		if (ret==1) {
			System.out.println("----------");
			//request.getRequestDispatcher("bookList").forward(request, response);
		    response.sendRedirect("bookList");
		} else {
			
			request.getRequestDispatcher("bookAdd.jsp").forward(request, response);
		}
	}

}
