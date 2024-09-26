package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.AccountDAO;
import com.example.demo.entity.Account;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;
	
	@Override
	public Account getAccountFromUsername(String username) {
		return accountDAO.getAccountFromUsername(username);
	}

}
