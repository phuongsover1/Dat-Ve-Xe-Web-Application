package com.example.demo.DAO;

import com.example.demo.entity.Account;

public interface AccountDAO {
	public Account getAccountFromUsername(String username);
}
