package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;

public interface UserService {
	public User getUser(int id);
	
	public void saveUser(User user, Account account);

	public void update(User user);
	
	public User getUserFromUsername(String username);

	public void saveUser(User tempUser);
	
	public void updateUserWithAccount(User user, Account account);
}
