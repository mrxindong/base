package com.sx_report.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import com.sx_report.dao.mapper.SEC_USERMapper;
import com.sx_report.model.SEC_USER;

@Service
public class UserService {

	@Autowired
	SEC_USERMapper userDao;

	public String find() throws InterruptedException {
		List<SEC_USER> result = userDao.find();
		return "" + result.size();
	}


}
