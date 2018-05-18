package com.highlion.dao;

import java.util.List;
import java.util.Map;

import com.highlion.domain.BookVO;

public interface BookDaoJdbc {
int add(BookVO book);
List<BookVO> findAllBooks(int pageNo);
public int findTotal() ;
public Map<Integer,String> getTransforType();
}
