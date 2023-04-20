package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.mapper.MemberMapper;

public class MemberMybatisDao {
	private Class<MemberMapper> cls = MemberMapper.class;
	private Map<String, Object> map = new HashMap<>();
	public boolean insert(Member mem) {
		SqlSession session = MybatisConnection.getConnection();
   	 try {
   		 int cnt = session.getMapper(cls).insert(mem);
   		 if(cnt > 0) return true; 
   		 else return false;    		 
   	 } catch(Exception e) {
   		 e.printStackTrace();
   	 } finally {
   		 MybatisConnection.close(session);
   	 }	
   	 return false;
    }
    
    public Member selectOne(String id) {
    	SqlSession session = MybatisConnection.getConnection();   	
   	 try {
   		 return session.getMapper(cls).selectOne(id);   		 
   	 } catch(Exception e) {
   		 e.printStackTrace();
   	 } finally {
   		 MybatisConnection.close(session);
   	 }
   	 return null;
    }
    
    public boolean update(Member mem) {
    	SqlSession session = MybatisConnection.getConnection();
   	 try {
   		 int cnt = session.getMapper(cls).update(mem);
   		 if(cnt>0) return true;
   		 else return false;
   	 } catch(Exception e) {
   		 e.printStackTrace();
   	 } finally {
   		MybatisConnection.close(session);
   	 }
   	 return false;
    }
    
    public List<Member> list() {
    	SqlSession session = MybatisConnection.getConnection();
   	 try {
   		 return session.getMapper(cls).selectList(null);    		 
   	 } catch(Exception e) {
   		 e.printStackTrace();
   	 } finally {
   		 MybatisConnection.close(session);
   	 }
   	 return null;
    }
    
    public boolean delete(String id) {
    	SqlSession session = MybatisConnection.getConnection();
   	 try {
   		 int cnt = session.getMapper(cls).delete(id);
   		 if(cnt>0) return true;
   		 else return false;
   	 } catch(Exception e) {
   		 e.printStackTrace();
   	 } finally {
   		 MybatisConnection.close(session);
   	 }
   	 return false;
    }
    
    public String idSearch(String email, String tel) {
    	SqlSession session = MybatisConnection.getConnection();
   	 try {   		
   		return session.getMapper(cls).idSearch(email, tel); 		 
   	 } catch(Exception e) {
   		 e.printStackTrace();
   	 } finally {
   		 MybatisConnection.close(session);
   	 }
   	 return null;
    }
    
    public String pwSearch(String id, String email, String tel) {
    	SqlSession session = MybatisConnection.getConnection();
   	 try { 
   		 return session.getMapper(cls).pwSearch(id, email, tel);
   	 } catch(Exception e) {
   		 e.printStackTrace();
   	 } finally {
   		 MybatisConnection.close(session);
   	 }
   	 return null;
    }
    
    public boolean updatePass(String id, String chgpass) {
    	SqlSession session = MybatisConnection.getConnection();
   	 try {
   		 int cnt = session.getMapper(cls).updatePass(id, chgpass);
   		 if(cnt>0) return true;
   		 else return false;
   	 } catch(Exception e) {
   		 e.printStackTrace();
   	 } finally {
   		 MybatisConnection.close(session);
   	 }
   	 return false;
    }
    
    public List<Member> selectEmail(String[] ids) {
    	SqlSession session = MybatisConnection.getConnection();
   	 try {
   		 map.clear();
   		 map.put("ids", ids);
   		 return session.getMapper(cls).selectList(map);
   	 } catch(Exception e) {
   		 e.printStackTrace();
   	 } finally {
   		 MybatisConnection.close(session);
   	 }
   	 return null;
    }
}
