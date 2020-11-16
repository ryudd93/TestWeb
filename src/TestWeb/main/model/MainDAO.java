package TestWeb.main.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import TestWeb.board.model.BoardVO;
import TestWeb.user.util.JdbcUtill;


public class MainDAO {
	private static MainDAO instance = new MainDAO();
	private DataSource ds = null;
	
	private MainDAO() {
		
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			System.out.println("드라이버 호출 오류");
		}
	}
	
	
	public static MainDAO getInstance() {
		return instance;
	}
	
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	
	public ArrayList<BoardVO> getMainList() {
		ArrayList<BoardVO> list = new ArrayList<>();
		
		String sql = "select *\r\n" + 
				"from (select rownum rn\r\n" + 
				"            , bno\r\n" + 
				"            , writer\r\n" + 
				"            , title\r\n" + 
				"            , content\r\n" + 
				"            , regdate\r\n" + 
				"     from (select * \r\n" + 
				"           from tw_board\r\n" + 
				"           order by bno desc))\r\n" + 
				"where rn >= 1 and rn <= 10";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bno = rs.getInt("bno");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				Timestamp regdate = rs.getTimestamp("regdate");
				
				BoardVO vo = new BoardVO(bno, writer, title, null, regdate);
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		
		return list;
		
	}
	
	public ArrayList<BoardVO> getMyList(String id) {
		ArrayList<BoardVO> list = new ArrayList<>();
		
		String sql = "select b.bno, b.title, b.writer, b.regdate\r\n" + 
				"from tw_board b left join tw_user u on u.id = b.writer\r\n" + 
				"where id = ?\r\n" + 
				"order by bno desc";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				Timestamp regdate = rs.getTimestamp("regdate");
				
				BoardVO vo = new BoardVO(bno, writer, title, null, regdate);
				list.add(vo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		return list;
	}


}


