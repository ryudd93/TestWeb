package TestWeb.user.model;

import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import TestWeb.user.util.JdbcUtill;

public class UserDAO {
	
	
	private static UserDAO instance = new UserDAO();
	private DataSource ds = null;
	
	private UserDAO() {
		
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			System.out.println("드라이버 호출 오류");
		}
	}
	
	
	public static UserDAO getInstance() {
		return instance;
	}
	
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	
	public int join(UserVO vo) {
		int result = 0;
		String sql = "insert into tw_user(id, password, name, ph_number, email, addr_basic, addr_detail) values(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());		
			pstmt.setString(2, vo.getPassword());		
			pstmt.setString(3, vo.getName());		
			pstmt.setString(4, vo.getPh_number());		
			pstmt.setString(5, vo.getEmail());		
			pstmt.setString(6, vo.getAddr_basic());
			pstmt.setString(7, vo.getAddr_detail());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		
		return result;
		
	}
	
	
	public int checkId(String id) {
		int result = 0;
		String sql = "select * from tw_user where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = 1;
			} else {
				result = 0;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	
	public UserVO login(String id, String pw) {
		String sql = "select * from tw_user where id = ? and password = ?";
		UserVO vo = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPh_number(rs.getString("ph_number"));
				vo.setEmail(rs.getString("email"));
				vo.setAddr_basic(rs.getString("addr_basic"));
				vo.setAddr_detail(rs.getString("addr_detail"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		
		return vo;
		
	}
	
	public int update(UserVO vo) {
		int result = 0;
		String sql = "update tw_user set password = ?, name = ?, ph_number = ?, email = ?, addr_basic = ?, addr_detail = ? where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPh_number());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddr_basic());
			pstmt.setString(6, vo.getAddr_detail());
			pstmt.setString(7, vo.getId());
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		
		return result;
		
		
	} public int delete(String id) {
		int result = 0;
		String sql = "delete from tw_user where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		
		return result;
	}
	

}
