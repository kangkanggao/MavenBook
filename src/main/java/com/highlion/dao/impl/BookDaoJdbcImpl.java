package com.highlion.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.highlion.dao.BookDaoJdbc;
import com.highlion.domain.BookVO;
import com.highlion.utils.PageConstant;
import com.highlion.utils.Utils;

public class BookDaoJdbcImpl implements BookDaoJdbc {
	Map <Integer,String>map=null;
	@Override
	public int add(BookVO bookVo) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = Utils.getConn();
			String sql = "insert into t_book(tid,name,descri,photo,price,author,pubDate) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookVo.getTid());
			ps.setString(2, bookVo.getName());
			ps.setString(3, bookVo.getDescrible());
			ps.setString(4, bookVo.getPhoto());
			ps.setDouble(5, bookVo.getPrice());
			ps.setString(6, bookVo.getAuthor());
			// java.util.Date-->java.sql.Date
			ps.setDate(7, new java.sql.Date(bookVo.getPubDate().getTime()));
			int ret = ps.executeUpdate();
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.release(conn, ps);
		}
		return 0;
	}

	@Override
	public List<BookVO> findAllBooks(int pageNo) {
		Connection conn = null;
		Connection conn2 = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			conn = Utils.getConn();
			conn2=Utils.getConn();
			ps = conn.prepareStatement("select * from t_book limit " + ((pageNo - 1) * PageConstant.PAGE_SIZE) + ","
					+ PageConstant.PAGE_SIZE);
			rs = ps.executeQuery();
			List<BookVO> list = new ArrayList<BookVO>();
			map=new HashMap<>();
			while (rs.next()) {
				BookVO bookVO = new BookVO();
				bookVO.setId(rs.getInt("id"));
				bookVO.setTid(rs.getInt("tid"));
				//把书的类型代号转换为实际类型
				ps2=conn2.prepareStatement("select name from t_type where id="+rs.getInt("tid")+"");
				rs2=ps2.executeQuery();
				if(rs2.next()) {
					String tname=rs2.getString("name");
					map.put(rs.getInt("tid"),tname);
				}
				bookVO.setName(rs.getString("name"));
				bookVO.setDescrible(rs.getString("describle"));
				bookVO.setPhoto(rs.getString("photo"));
				bookVO.setPubDate(rs.getDate("pubDate"));
				bookVO.setAuthor(rs.getString("author"));
				bookVO.setPrice(rs.getDouble("price"));
				list.add(bookVO);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.release(conn, ps, rs);
		}
		return null;
	}

	@Override
	public int findTotal() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConn();
			ps = conn.prepareStatement("select count(*) from t_book");
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.release(conn, ps, rs);
		}
		return 0;
	}
	public Map<Integer,String> getTransforType(){
		return map;
	}

}
