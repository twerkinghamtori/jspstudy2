package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.mapper.BoardMapper;

public class BoardMybatisDao {
	private Class<BoardMapper> cls = BoardMapper.class;
	private Map<String, Object> map = new HashMap<>();
	
	public int maxnum() {
    	SqlSession session = MybatisConnection.getConnection();
    	try {
    		return session.getMapper(cls).maxnum();
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		MybatisConnection.close(session);
    	}
    	return 0;
    }
    
    public boolean insert(Board board) {
    	SqlSession session = MybatisConnection.getConnection();
    	try {
    		int cnt = session.getMapper(cls).insert(board);
    		if(cnt>0) return true;
    		else return false;
    	}  catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		MybatisConnection.close(session);
    	}
    	return false;
    }
    
    public int boardCount(String boardid, String column, String find) {
    	SqlSession session = MybatisConnection.getConnection();
    	try {
    		map.clear();
    		map.put("boardid", boardid);
    		map.put("column", column);
    		if(column != null) {
    			String[] cols = column.split(",");
    			switch(cols.length) {
    			case 3 : map.put("cols3", cols[2].trim()); //제목+작성자+내용
    			case 2 : map.put("cols2", cols[1].trim()); 
    			case 1 : map.put("cols1", cols[0].trim());
    			}
    			map.put("cols", cols);
        		map.put("find", find);
    		}   		
    		return session.getMapper(cls).boardCount(map);
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		MybatisConnection.close(session);
    	}
    	return 0;
    }
    
    public List<Board> list(String boardid, int pageNum, int limit, String column, String find) {
    	SqlSession session = MybatisConnection.getConnection();
    	try {
    		map.clear();
    		map.put("boardid", boardid);
    		map.put("start", (pageNum-1)*limit);
    		map.put("limit", limit);
    		map.put("column", column);
    		if(column != null) {
    			String[] cols = column.split(",");
    			switch(cols.length) {
    			case 3 : map.put("cols3", cols[2].trim()); //제목+작성자+내용
    			case 2 : map.put("cols2", cols[1].trim()); 
    			case 1 : map.put("cols1", cols[0].trim());
    			}
    			map.put("cols", cols);
        		map.put("find", find);
    		}   		
    		return session.getMapper(cls).selectList(map);    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		MybatisConnection.close(session);
    	}
    	return null;
    }
    
    public Board selectOne(int num) {
    	SqlSession session = MybatisConnection.getConnection();
    	try {
    		return session.getMapper(cls).selectOne(num);
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		MybatisConnection.close(session);
    	}
    	return null;
    } 
    
    public void readcntAdd(int num) {
    	SqlSession session = MybatisConnection.getConnection();
    	try {
    		session.getMapper(cls).readcntAdd(num);
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		MybatisConnection.close(session);
    	}
    }
    
    public void grpStepAdd(int grp, int grpstep) {
    	SqlSession session = MybatisConnection.getConnection();
    	try {
    		session.getMapper(cls).grpStepAdd(grp, grpstep);
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		MybatisConnection.close(session);
    	}
    }
    
    public boolean update(Board b) {
    	SqlSession session = MybatisConnection.getConnection();
    	try {
    		int cnt = session.getMapper(cls).update(b);
    		if(cnt>0) return true;
    		else return false;
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		MybatisConnection.close(session);
    	} 
    	return false;    	
    }
    
    public boolean delete(int num) {
    	SqlSession session = MybatisConnection.getConnection();
    	try {
    		int cnt = session.getMapper(cls).delete(num);
    		if(cnt>0) return true;
    		else return false;
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		MybatisConnection.close(session);
    	}
    	return false;
    }
}
