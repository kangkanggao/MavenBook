package com.highlion.servlet.web;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highlion.service.BookService;
import com.highlion.service.impl.BookServiceImpl;
@WebServlet("/bookDel")
public class BookDelServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String strId=request.getParameter("id");
int id=Integer.parseInt(strId);
BookService bs=new BookServiceImpl();
int ret=bs.delById(id);
System.out.println(ret);
if(ret<1) {
	request.setAttribute("msg", "É¾³ýÊ§°Ü");
}
request.getRequestDispatcher("bookList").forward(request, response);
}
}
