package com.highlion.service.impl;

import java.util.List;
import java.util.Map;

import com.highlion.dao.BookDaoJdbc;
import com.highlion.dao.impl.BookDaoJdbcImpl;
import com.highlion.domain.BookVO;
import com.highlion.service.BookService;

public class BookServiceImpl implements BookService {
	BookDaoJdbc bad=new BookDaoJdbcImpl();
	@Override
	public int add(BookVO book) {
		// TODO Auto-generated method stub
		return bad.add(book);
	}
   
	@Override
	public List<BookVO> findAllBooks(int pageNo) {
		
		return bad.findAllBooks(pageNo);
	}
	
	@Override
	public int findTotal() {
		
		return bad.findTotal();
	}

	@Override
	public Map<Integer, String> getTransforType() {
		// TODO Auto-generated method stub
		return bad.getTransforType();
	}

	
	

}
