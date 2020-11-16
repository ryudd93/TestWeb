package TestWeb.user.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import TestWeb.board.model.BoardVO;
import TestWeb.main.model.MainDAO;
import TestWeb.user.model.UserVO;

public class getMyListUserServiceImp implements UserService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		UserVO vo = (UserVO) session.getAttribute("user");
		String id = vo.getId();
		
		MainDAO dao = MainDAO.getInstance();
		ArrayList<BoardVO> list = dao.getMyList(id);
		
		request.setAttribute("list", list);
		
		return 1;
	}

}
