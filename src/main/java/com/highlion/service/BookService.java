package com.highlion.service;

import java.util.List;
import java.util.Map;

import com.highlion.domain.BookVO;

public interface BookService {

	int add(BookVO book);
	List<BookVO> findAllBooks(int pageNo);
	int findTotal();
	public Map<Integer,String> getTransforType();
}
