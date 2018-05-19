package com.highlion.dao;

import java.util.List;
import java.util.Map;

import com.highlion.domain.BookVO;

public interface BookDaoJdbc {
int add(BookVO book);
List<BookVO> findAllBooks(int pageNo, int tid, String name);
public int findTotal(int tid, String name) ;
public Map<Integer,String> getTransforType();
int delById(int id);
}
