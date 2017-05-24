package com.mvc.model.student;

import org.apache.ibatis.session.SqlSession;

import com.mvc.mybatis.ConfigManager;

public class PhysicalDAO {
	//ConfigManager manager=ConfigManager.getInstance();
	SqlSession session;//서비스가 공유해 준것을 쓰자. 왜? 그래야 다른 DAO객체들과 하나의 커넥션으로 묶일수 있어 트랜잭션을 이용할 수 있기 때문에
	
	/*public PhysicalDAO(SqlSession session) {
		this.session=session;
	}*/
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	public int insert(Physical physical){
		int result=0;
		//SqlSession session=manager.getSession();//session을 Service가 가져야 tranjection처리를 할 수 있다.
		result=session.insert("Physical.insert", physical);
		//session.commit();//session에서 commit한다.
		//manager.close(session);//session에서 닫자.
		return result;
		
	}	
	
}
