package TestWeb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import TestWeb.user.model.UserDAO;
import TestWeb.user.model.UserVO;

public class deleteServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {

		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("user");
		String id = vo.getId();
		
		UserDAO dao = UserDAO.getInstance();
		
		System.out.println(password);
		System.out.println(id);
		
		if (dao.login(id, password) != null) {
			dao.delete(id);
			session.invalidate();
			return 1;
		} else {
			return 0;
		}
		
	}

}
