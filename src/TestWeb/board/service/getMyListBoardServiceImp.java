package TestWeb.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TestWeb.board.model.BoardVO;
import TestWeb.main.model.MainDAO;
import TestWeb.user.service.UserService;

public class getMyListBoardServiceImp implements BoardService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		
		MainDAO dao = MainDAO.getInstance();
		ArrayList<BoardVO> list = dao.getMyList(id);
		
		request.setAttribute("list", list);
		
		
	}

}
