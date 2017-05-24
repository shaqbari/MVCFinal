package com.mvc.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


/**
 * @author user1 config.xml을 읽어들여 어떤 dbms를 사용할 것인지를 결정한다. db와 실제 작업을 담당하는 객체인
 *         SqlSession객체를 반환해주는 SqlSessionFactory를 생성하자.
 *
 *         한번만 읽어서 모든 DAO에서 이용할 수 있게 singleton으로 만들자.
 */
public class ConfigManager {
	private static ConfigManager instance;
	SqlSessionFactory factory;

	private ConfigManager() {
		// 패키지에 들어있는 파일이 클래스가 아닌, 일반파일인 경우
		// 도트가 아닌 그냥 /로 접근해야 한다.
		String resource = "com/mvc/mybatis/config.xml";
		InputStream inputStream = null;

		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// jdbc시절에는 쿼리문을 수행하기 위해 preparedStatement객체를 생성해야 했다
		// 하지만 mybatis를 이용하면 오직 SqlSession이라는 객체만 사용하면 된다.
		factory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	public static ConfigManager getInstance() {
		if (instance == null) {
			instance = new ConfigManager();

		}

		return instance;
	}

	// dao들을 위해 SqlSession을 반환해 부는 메소드 정의
	public SqlSession getSession() {
		SqlSession session = null;
		session = factory.openSession();
		return session;
	}

	// 다사용한 세션 반환
	public void close(SqlSession session) {
		session.close();
	}

	/*
	 * public static void main(String[] args) { //패키지에 들어있는 파일이 클래스가 아닌, 일반파일인
	 * 경우 //도트가 아닌 그냥 /로 접근해야 한다. String resource = "mybatis/config/config.xml";
	 * InputStream inputStream = null;
	 * 
	 * try { inputStream = Resources.getResourceAsStream(resource); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * }
	 * 
	 * //jdbc시절에는 쿼리문을 수행하기 위해 preparedStatement객체를 생성해야 했다 //하지만 mybatis를 이용하면
	 * 오직 SqlSession이라는 객체만 사용하면 된다. SqlSessionFactory factory = new
	 * SqlSessionFactoryBuilder().build(inputStream);
	 * 
	 * SqlSession session=factory.openSession();
	 * 
	 * //쿼리문이 존재하는 mapper xml의 아이디를 조회하여 쿼리문을 수행 List
	 * list=session.selectList("selectAll");
	 * System.out.println("총사원수는 "+list.size());
	 * 
	 * }
	 */

}
