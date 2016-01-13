package com.thousandeyes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class TweetJDBCTemplate implements ITweetJDBCTemplate {

	private DataSource dataSource;

	public void followUser(String user, String follower) {

		String sql = "INSERT INTO FOLLOWTABLE (USER,FOLLOWER) VALUES (?,?)";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, follower);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException("DataBase is Down");
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
