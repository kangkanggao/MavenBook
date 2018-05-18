package com.highlion.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.highlion.dao.UserDao;
import com.highlion.domain.User;
import com.highlion.utils.Utils;

public class UserDaoimpl implements UserDao {

	@Override
	public Boolean login(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean ret = false;
		try {
			conn = Utils.getConn();
			String sql = "select * from t_admin where name=? and pwd=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPwd());
			rs = ps.executeQuery();
			if (rs.next()) {
				ret = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.release(conn, ps, rs);
		}
		return ret;
	}

}
