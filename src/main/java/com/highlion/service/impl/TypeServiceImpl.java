package com.highlion.service.impl;

import java.util.List;

import com.highlion.dao.TypeDaoJdbc;
import com.highlion.dao.impl.TypeDaoJdbcImpl;
import com.highlion.domain.TypeVO;
import com.highlion.service.TypeService;

public class TypeServiceImpl implements TypeService{
    TypeDaoJdbc tdj=new TypeDaoJdbcImpl();
	@Override
	public List<TypeVO> findAllTypes() {
		return tdj.findAllTypes();
	}

}
