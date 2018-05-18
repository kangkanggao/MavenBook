package com.highlion.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.highlion.dao.TypeDaoJdbc;
import com.highlion.domain.TypeVO;
import com.highlion.utils.Utils;

public class TypeDaoJdbcImpl implements TypeDaoJdbc {

	@Override
	public List<TypeVO> findAllTypes() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=Utils.getConn();
			ps=conn.prepareStatement("select *from t_type");
			rs=ps.executeQuery();
			List<TypeVO> list=new ArrayList<TypeVO>();
			while(rs.next()) {
				TypeVO typevo=new TypeVO();
				typevo.setTid(rs.getInt("id"));
				typevo.setName(rs.getString("name"));
				list.add(typevo);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
