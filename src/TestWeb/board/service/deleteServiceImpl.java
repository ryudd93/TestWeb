package TestWeb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TestWeb.board.model.BoardDAO;

public class deleteServiceImpl implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		String bno = request.getParameter("bno");
		
		
		BoardDAO dao = BoardDAO.getInstance();
		
		
		dao.delete(bno);
		
		
	}

}
