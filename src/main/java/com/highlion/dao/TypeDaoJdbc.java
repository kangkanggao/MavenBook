package com.highlion.dao;

import java.util.List;

import com.highlion.domain.TypeVO;

public interface TypeDaoJdbc {
	public List<TypeVO> findAllTypes();
}
