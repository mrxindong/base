package com.sx_report.service.task;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import com.sx_report.dao.mapper.SEC_USERMapper;
import com.sx_report.model.SEC_USER;

@Component
public class DaoThread<List> implements Callable<List> {

	private SqlSessionFactory sessionFactory;
	private CountDownLatch endSingle;
	private static final ThreadLocal threadLocal = new ThreadLocal();

	public SqlSessionFactory getSessionFacotry() {
		return sessionFactory;
	}

	public void setSessionFacotry(SqlSessionFactory sessionFacotry) {
		this.sessionFactory = sessionFacotry;
	}

	private String sql;

	public String getSql() {
		return sql;
	}

	private DaoThread() {

	}

	public DaoThread(SqlSessionFactory sessionFactory, String sql, java.util.concurrent.CountDownLatch endSingle) {
		this.sessionFactory = sessionFactory;
		this.sql = sql;
		this.endSingle = endSingle;
	}

	@Override
	public List call() throws Exception {
		// threadLocal得到每个子线程的变量副本
		SqlSession session = (SqlSession) threadLocal.get();

		if (session == null) {
			session = this.sessionFactory.openSession();
			// 把hibernate的session和当前线程绑定
			threadLocal.set(session);
		}

		SEC_USERMapper userMapper = session.getMapper(SEC_USERMapper.class);

		java.util.List<SEC_USER> user = userMapper.find(); 

		session.commit();

		session.close();

		if (user == null || user.size() == 0) {
			System.out.println("No validate entry");
			// 线程计数器减一
			endSingle.countDown();
			return null;
		}
		
		// 线程计数器减一
		endSingle.countDown();
		return (List) user;
	}

}
