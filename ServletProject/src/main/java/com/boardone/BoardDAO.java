package com.boardone;

public class BoardDAO {
	private static BoardDAO instance = null;

	public static BoardDAO getInstance() {
		if (instance == null) {
			synchronized(BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		
		return instance;
	}
	
	
}
