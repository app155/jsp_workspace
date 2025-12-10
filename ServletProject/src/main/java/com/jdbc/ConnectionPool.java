package com.jdbc;
import java.sql.*;
import java.util.ArrayList;

public class ConnectionPool {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (ClassNotFoundException ne) {
			ne.printStackTrace();
		}
	}
	
	// 사용하지 않은 커넥션 (초기 커넥션)을 저장할 변수 선언
	private ArrayList<Connection> free; //초기 커넥션
	private ArrayList<Connection> used; //사용중 커넥션
	
	private int initialCons = 10; // 최초 연결 커넥션 수
	private int maxCons = 20; // 최대 커넥션 수
	private int numCons = 0;
	
	private static ConnectionPool cp;
	
	public static ConnectionPool getInstance() {
		try {
			if (cp == null) {
				synchronized(ConnectionPool.class) {
					cp = new ConnectionPool();
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
			
		return cp;
	}
	
	private ConnectionPool() throws SQLException {
		
		free = new ArrayList<Connection>(initialCons);
		used = new ArrayList<Connection>(initialCons);
		
		while (numCons < initialCons) {
			addConnection();
		}
	}

	private void addConnection() throws SQLException {
		free.add(getNewConnection());
	}
	
	private Connection getNewConnection() throws SQLException {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		}
		catch(SQLException ss) {
			ss.printStackTrace();
		}
		
		System.out.println("About to Connection to " + con);
		
		numCons++;
		return con;
	}
	
	public synchronized Connection getConnection() throws SQLException {
		if (free.isEmpty()) {
			while (numCons < maxCons) {
				addConnection();
			}
		}
		
		Connection _con;
		_con = free.get(free.size() - 1);
		free.remove(_con);
		used.add(_con);
		
		return _con;
	}
	
	public synchronized void releaseConnection(Connection con) throws SQLException {
		boolean flag = false;
		
		if (used.contains(con)) {
			used.remove(con);
			numCons--;
			flag = true;
		}
		else {
			throw new SQLException("ConnectionPool에 존재하지 않음");
		}
		
		try {
			if (flag) {
				free.add(con);
				numCons++;
			}
			else {
				con.clearWarnings();
			}
		}
		catch(SQLException s) {
			try {
				if (con != null) {
					con.close();
				}
			}
			catch (SQLException ss) {
				ss.printStackTrace();
			}
		}
	}
	
	public void closeAll() {
		for (int i = 0; i < used.size(); i++) {
			Connection con = used.get(i);
			used.remove(i--);
			
			try {
				con.close();
			}
			catch(SQLException sss) {
				sss.printStackTrace();
			}
		}
		
		for (int i = 0; i < free.size(); i++) {
			Connection con = free.get(i);
			free.remove(i--);
			
			try {
				con.close();
			}
			catch(SQLException sss) {
				sss.printStackTrace();
			}
		}
	}

	public int getMaxCons() {
		return maxCons;
	}

	public int getNumCons() {
		return numCons;
	}
	
}
