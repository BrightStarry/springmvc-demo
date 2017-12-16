package com.zx.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DbUtil {
	
	/**
	 * 获取SqlSession
	 * @throws Exception 
	 */
	public static SqlSession getSqlSession() throws Exception{
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	/**
	 * 关闭SqlSession
	 */
	public static void close(SqlSession session){
		if(session != null){
			session.close();
			session = null;
		}
	}
}
