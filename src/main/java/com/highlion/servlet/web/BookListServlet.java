package com.highlion.servlet.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highlion.domain.BookVO;
import com.highlion.domain.TypeVO;
import com.highlion.service.BookService;
import com.highlion.service.impl.BookServiceImpl;
import com.highlion.utils.PageConstant;
@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String strPageNo = request.getParameter("pageNo");
		//获取搜索条件
		String name=request.getParameter("name");
		String strTid=request.getParameter("tid");
		int tid=-1;
		try {
			tid=Integer.parseInt(strTid);
		} catch (NumberFormatException e1) {
			
		}
		
		int pageNo;
		try {
			pageNo = Integer.parseInt(strPageNo);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		BookService bs = new BookServiceImpl();
		List<BookVO> list = bs.findAllBooks(pageNo,tid,name);
		Map<Integer,String>map=bs.getTransforType();
		List<TypeVO> types=bs.findAllTypes();
		int total = bs.findTotal(tid,name);
		System.out.println("总页数:"+total);
		if (total % PageConstant.PAGE_SIZE == 0) {
			request.setAttribute("totalPage", total / PageConstant.PAGE_SIZE);
		} else {
			request.setAttribute("totalPage", total / PageConstant.PAGE_SIZE + 1);
		}
		request.setAttribute("name",name);
		request.setAttribute("tid",tid);
		request.setAttribute("types",types);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("list", list);
		request.setAttribute("map",map);
		request.getRequestDispatcher("bookList.jsp").forward(request, response);
       
	}

}
