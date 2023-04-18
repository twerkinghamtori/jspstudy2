package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
	public List<Comment> list(int num) {
		List<Comment> list = new ArrayList<Comment>();
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = "select * from comment where num=?";
		try {			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Comment cmt = new Comment();
				cmt.setNum(rs.getInt("num"));
				cmt.setSeq(rs.getInt("seq"));
				cmt.setWriter(rs.getString("writer"));
				cmt.setContent(rs.getString("content"));
				cmt.setRegdate(rs.getTimestamp("regdate"));
				list.add(cmt);
			}
		} catch(SQLException e) {
   		 e.printStackTrace();
   	 	} finally {
   		 DBConnection.close(con, pstmt, null);
   	 	}	
		return list;
	}

	public int maxseq(int num) {
		Connection con = DBConnection.getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int maxseq=0;
    	try {
    		pstmt = con.prepareStatement("select ifnull(max(seq),0) maxseq from comment where num=?");
    		pstmt.setInt(1, num);
    		rs = pstmt.executeQuery();
    		if(rs.next()) maxseq = rs.getInt("maxseq");
    		return maxseq;
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBConnection.close(con, pstmt, rs);
    	}
    	return maxseq;
	}

	public boolean insert(Comment cmt) {
		Connection con = DBConnection.getConnection();
    	PreparedStatement pstmt = null;
    	String sql = "insert into comment (num, seq, writer, content, regdate) values (?,?,?,?,now())";
    	try {
    		pstmt = con.prepareStatement(sql);
    		pstmt.setInt(1, cmt.getNum());
    		pstmt.setInt(2, cmt.getSeq());
    		pstmt.setString(3, cmt.getWriter());
    		pstmt.setString(4, cmt.getContent());
    		if(pstmt.executeUpdate()>0) return true;
    		else return false;
    	}  catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBConnection.close(con, pstmt, null);
    	}
		return false;
	}

	public boolean delete(int num, int seq) {
		Connection con = DBConnection.getConnection();
    	PreparedStatement pstmt = null;
    	String sql = "delete from comment where num=? and seq=?";
    	try {
    		pstmt = con.prepareStatement(sql);
    		pstmt.setInt(1, num);
    		pstmt.setInt(2, seq);
    		if(pstmt.executeUpdate()>0) return true;
    		else return false;
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBConnection.close(con, pstmt, null);
    	}
    	return false;
	}

}
