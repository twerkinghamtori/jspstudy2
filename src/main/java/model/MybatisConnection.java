package model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConnection {
	private MybatisConnection() {}
	private static SqlSessionFactory sqlMap;
	//static 초기화 블럭 : static 변수(sqlMap)의 초기화 담당
	static { //MybatisConnection 클래스가 메모리에 로드 될 때 실행되는 블럭(static 초기화 블럭은 main이 실행되기 전에 한번만 실행 된다)
		//Connection 한번만 하고 session정보만 open 해주는거임.
		String resource = "model/mapper/mybatis-config.xml";
		InputStream input = null;
		try {
			input = Resources.getResourceAsStream(resource);
		} catch(IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);
	}
	public static SqlSession getConnection() {
		return sqlMap.openSession();
	}
	public static void close(SqlSession session) {
		session.commit();
		session.close();
	}
}
