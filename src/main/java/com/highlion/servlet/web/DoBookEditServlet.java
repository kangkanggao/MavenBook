package com.highlion.servlet.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.highlion.domain.BookVO;
import com.highlion.service.BookService;
import com.highlion.service.impl.BookServiceImpl;
import com.highlion.utils.MyBeanUtils;
@WebServlet("/doBookEdit")
public class DoBookEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	Part part = request.getPart("photo");
	String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
	fileName = fileName.indexOf("\\") == -1 ? fileName : fileName.substring(fileName.indexOf("\\") + 1);
	String newFileName = UUID.randomUUID().toString() + "_" + fileName;
	part.write(request.getServletContext().getRealPath("upload/" + newFileName));
	BookVO book = new BookVO();
	try {
		MyBeanUtils.populate(book, request.getParameterMap(), "yyyy-MM-dd");
		book.setPhoto(newFileName);
	} catch (Exception e) {
		e.printStackTrace();
	}
	BookService bs=new BookServiceImpl();
	int ret=bs.doBookEdit(book);
	response.setContentType("text/html;charset=utf-8");
	if (ret > 0) {
		response.sendRedirect("bookList");
	} else {
		request.setAttribute("msg", "添加失败");
		//把用户刚才填写的回填
		request.setAttribute("bookVO", book);
		request.getRequestDispatcher("bookEdit.jsp").forward(request, response);

	}
}
}
