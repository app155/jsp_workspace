package com.board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int pageSize = 5;
		String pageNum = request.getParameter("pageNum");
		
		if (pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		String find = null;
		String find_box = null;
		
		find = request.getParameter("find");
		find_box = request.getParameter("find_box");
		
		if (find == null) {
			find = "no";
		}
		if (find_box == null) {
			find_box = "no";
		}
		
		List<BoardVO> articleList = null;
		BoardDAO dbPro = BoardDAO.getInstance();

		count = dbPro.getArticleCount(find, find_box);
			
		if (count > 0) {
			articleList = dbPro.getArticles(find, find_box, startRow, endRow);
		}
		else {
			articleList = Collections.emptyList();
		}
		
		number = count - (currentPage - 1) * pageSize;
		
		// 해당 뷰 (list.jsp)에서 사용할 속성값 저장
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endtRow", endRow);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("number", number);
		request.setAttribute("articleList", articleList);
		request.setAttribute("find", find);
		request.setAttribute("find_box", find_box);
		
		return "/board/list.jsp";
	}
}
