package TestWeb.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import TestWeb.user.util.JdbcUtill;

public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	private DataSource ds = null;
	
	private BoardDAO() {
		
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			System.out.println("드라이버 호출 오류");
		}
	}
	
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public void write(String writer, String title, String content) {
		int result = 0;
		String sql = "insert into tw_board(bno, writer, title, content) values(TW_board_seq.nextval, ?, ?, ?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		
		
	}
	
	public ArrayList<BoardVO> getList(int pageNum, int amount) {
		ArrayList<BoardVO> list = new ArrayList<>();
		
		String sql = "select *\r\n" + 
				"from (select rownum rn\r\n" + 
				"             , bno\r\n" + 
				"             , title\r\n" + 
				"             , writer\r\n" + 
				"             , content\r\n" + 
				"             , regdate\r\n" + 
				"      from (select *\r\n" + 
				"            from tw_board\r\n" + 
				"            order by bno desc))\r\n" + 
				"where rn > ? and rn <= ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNum - 1) * amount);
			pstmt.setInt(2, pageNum * amount);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				
				BoardVO vo = new BoardVO(bno, writer, title, content, regdate);
				list.add(vo);
				
			} 
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		
		return list;
		
		
	}
	
	public BoardVO getContent(String bno) {
		BoardVO vo = new BoardVO();
		
		String sql = "select * from tw_board where bno = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo.setBno(rs.getInt("bno"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		
		return vo;
		
	} 
	
	public void update(String bno, String writer, String title, String content) {
		BoardVO vo = new BoardVO();
		String sql = "update tw_board set title = ?, content = ? where bno = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, bno);
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		
	}
	
	public void delete(String bno) {
		String sql = "delete from tw_board where bno = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.executeUpdate();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		
		
	}
	
	public int getTotal() {
		int total = 0;
		String sql = "select count(*) as total from tw_board";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				total = rs.getInt("total");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		
		return total;
		
		
	}
	
	
	
	
}
