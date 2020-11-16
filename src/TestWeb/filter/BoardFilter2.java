package TestWeb.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import TestWeb.user.model.UserVO;

@WebFilter({"/board/bbs_modify.board", "/board/update.board"})
public class BoardFilter2 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = req.getSession();
		UserVO user = (UserVO)session.getAttribute("user");
		String writer = request.getParameter("writer");
		String bno = request.getParameter("bno");
		
		if (user == null) {
			res.sendRedirect("/TestWeb/user/login.user");
			return;
		}
		
		String id = user.getId();
		
		if (writer == null || !id.equals(writer)) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href='/TestWeb/board/bbs_content.board?bno=" + bno + "';");	//로그인 화면
			out.println("</script>");
			return;
		}

		
		
		chain.doFilter(request, response);
	}

}
