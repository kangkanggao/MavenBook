package com.highlion.servlet.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highlion.domain.BookVO;
import com.highlion.service.BookService;
import com.highlion.service.impl.BookServiceImpl;
@WebServlet("/toBookEdit")
public class ToBookEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("----------------");
	String strId=request.getParameter("id");
	int id=Integer.parseInt(strId);
	BookService bs=new BookServiceImpl();
	BookVO bv=bs.findAllBookById(id);
	request.setAttribute("bookVo",bv);
	System.out.println("=========");
	request.getRequestDispatcher("bookEdit.jsp").forward(request,response);
}
}