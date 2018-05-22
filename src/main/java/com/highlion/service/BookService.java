package com.highlion.service;

import java.util.List;
import java.util.Map;

import com.highlion.domain.BookVO;
import com.highlion.domain.TypeVO;

public interface BookService {

	int add(BookVO book);
	List<BookVO> findAllBooks(int pageNo, int tid, String name);
	int findTotal(int tid, String name);
	public Map<Integer,String> getTransforType();
	List<TypeVO> findAllTypes();
	int delById(int id);
	BookVO findAllBookById(int id);
	int doBookEdit(BookVO book);
}
