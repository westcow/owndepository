package com.bjb.project.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
	private Connection conn = null;
	private List<PreparedStatement> pstList = new ArrayList<PreparedStatement>();
	private List<ResultSet> rsList = new ArrayList<ResultSet>();

	public DBHelper(String url, String name, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName(name);// 指定连接类型
		conn = DriverManager.getConnection(url, user, password);// 获取连接
	}

	private PreparedStatement getPreparedStatement(String sql) throws SQLException {
		PreparedStatement pst = conn.prepareStatement(sql);
		pstList.add(pst);
		return pst;
	}

	private PreparedStatement getPreparedStatement(String sql, int type) throws SQLException {
		PreparedStatement pst = conn.prepareStatement(sql, type);
		pstList.add(pst);
		return pst;
	}

	/**
	 * 主键自增的INSERT
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public int insert(String sql, String[] args) throws SQLException {
		int ret = 0;
		PreparedStatement pst = getPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				pst.setString(i + 1, args[i]);
			}
		}
		pst.execute();
		ResultSet rs = pst.getGeneratedKeys();
		rsList.add(rs);
		if (rs != null && rs.next()) {
		    ret = rs.getInt(1);
		}
		return ret;
	}

	/**
	 * UPDATE || DELETE || INSERT
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public int update(String sql, String[] args) throws SQLException {
		PreparedStatement pst = getPreparedStatement(sql);
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				pst.setString(i + 1, args[i]);
			}
		}
		return pst.executeUpdate();
	}

	/**
	 * SELECT
	 * 注意：值索引从1开始
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public ResultSet query(String sql, String[] args) throws SQLException {
		PreparedStatement pst = getPreparedStatement(sql);
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				pst.setString(i + 1, args[i]);
			}
		}
		ResultSet rs = pst.executeQuery();
		rsList.add(rs);
		return rs;
	}
	
	/**
	 * UPDATE || DELETE || INSERT || SELECT
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public Boolean execute(String sql, String[] args) throws SQLException {
		PreparedStatement pst = getPreparedStatement(sql);
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				pst.setString(i + 1, args[i]);
			}
		}
		return pst.execute();
	}

	public Connection getConnection() {
		return conn;
	}
	
	public void close() {
		for (int i = rsList.size() - 1; i >= 0; i--) {
			ResultSet rs = rsList.get(i);
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				rsList.remove(i);
			}
		}
		for (int i = pstList.size() - 1; i >= 0; i--) {
			PreparedStatement pst = pstList.get(i);
			if (pst != null) {
				try {
					pst.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				pstList.remove(i);
			}
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}