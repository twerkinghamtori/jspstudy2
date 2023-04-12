package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DBConnection;

public class BookDao {
      public boolean insert(Book book) {
    	  Connection con = DBConnection.getConnection();
    	  PreparedStatement pstmt = null;
    	  ResultSet rs = null;
    	  String sql = "insert into diary (writer,title,content) values(?,?,?)";
    	  try {
    		  pstmt = con.prepareStatement(sql);
    		  pstmt.setString(1,book.getWriter());
    		  pstmt.setString(2,book.getTitle());
    		  pstmt.setString(3,book.getContent());
    		  if(pstmt.executeUpdate()>0) return true;
    		  else return false;
    	  } catch(SQLException e) {
    		  e.printStackTrace();
    	  } finally {
    		  DBConnection.close(con, pstmt, rs);
    	  }
    	  return false;
      }
      
      public Book selectAll() {
    	  Connection con = DBConnection.getConnection();
    	  PreparedStatement pstmt=null;
    	  ResultSet rs=null;
    	  String sql = "select * from diary";
    	  
    	  try {
    		  pstmt = con.prepareStatement(sql);
    		  rs = pstmt.executeQuery();
    		  if(rs.next()) {
    			  Book book = new Book();
    			  book.setWriter(rs.getString("writer"));
    			  book.setTitle(rs.getString("title"));
    			  book.setContent(rs.getString("content"));
    			  return book;
    		  }    		  
    	  } catch(SQLException e) {
    		  e.printStackTrace();
    	  } finally {
    		  DBConnection.close(con, pstmt, rs);
    	  }
    	  return null;
      }
      
      public Book selectOne(String writer) {
    	  Connection con = DBConnection.getConnection();
    	  PreparedStatement pstmt=null;
    	  ResultSet rs=null;
    	  String sql = "select * from diary where writer=?";
    	  
    	  try {
    		  pstmt = con.prepareStatement(sql);
    		  pstmt.setString(1, writer);
    		  rs = pstmt.executeQuery();    		  
    		  if(rs.next()) {
    			  Book book = new Book();
    			  book.setWriter(rs.getString("writer"));
    			  book.setTitle(rs.getString("title"));
    			  book.setContent(rs.getString("content"));
    			  return book;
    		  }    		  
    	  } catch(SQLException e) {
    		  e.printStackTrace();
    	  } finally {
    		  DBConnection.close(con, pstmt, rs);
    	  }
    	  return null;
      }
      
      public List<Book> list() {
    	  Connection con = DBConnection.getConnection();
    	  PreparedStatement pstmt = null;
    	  ResultSet rs = null;
    	  String sql = "select * from diary";
    	  List<Book> list = new ArrayList<>();
    	  try {
    		  pstmt=con.prepareStatement(sql);
    		  rs = pstmt.executeQuery();    		  
    		  while(rs.next()) {
    			  Book book = new Book();
    			  book.setWriter(rs.getString("writer"));
    			  book.setTitle(rs.getString("title"));
    			  book.setContent(rs.getString("content"));
    			  list.add(book);
    		  }
    		  return list;
    	  } catch(SQLException e) {
    		  e.printStackTrace();
    	  } finally {
    		  DBConnection.close(con, pstmt, rs);
    	  }
    	  return null;
      } 
}
