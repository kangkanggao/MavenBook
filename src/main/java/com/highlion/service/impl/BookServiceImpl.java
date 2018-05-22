package com.highlion.service.impl;

import java.util.List;
import java.util.Map;

import com.highlion.dao.BookDaoJdbc;
import com.highlion.dao.TypeDaoJdbc;
import com.highlion.dao.impl.BookDaoJdbcImpl;
import com.highlion.dao.impl.TypeDaoJdbcImpl;
import com.highlion.domain.BookVO;
import com.highlion.domain.TypeVO;
import com.highlion.service.BookService;

public class BookServiceImpl implements BookService {
	BookDaoJdbc bad=new BookDaoJdbcImpl();
	@Override
	public int add(BookVO book) {
		// TODO Auto-generated method stub
		return bad.add(book);
	}
   
	@Override
	public List<BookVO> findAllBooks(int pageNo,int tid,String name) {
		
		return bad.findAllBooks(pageNo,tid,name);
	}
	
	@Override
	public int findTotal(int tid,String name) {
		
		return bad.findTotal(tid,name);
	}

	@Override
	public Map<Integer, String> getTransforType() {
		// TODO Auto-generated method stub
		return bad.getTransforType();
	}

	@Override
	public List<TypeVO> findAllTypes() {
		TypeDaoJdbc tdj=new TypeDaoJdbcImpl();
		return tdj.findAllTypes();
	}

	@Override
	public int delById(int id) {
		
		return bad.delById(id);
	}

	@Override
	public BookVO findAllBookById(int id) {
		// TODO Auto-generated method stub
		return bad.findAllBookById(id);
	}

	@Override
	public int doBookEdit(BookVO book) {

		return bad.doBookEdit(book);
	}

	
	

}
