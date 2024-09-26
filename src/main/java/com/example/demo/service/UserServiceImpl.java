package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.UserDAO;
import com.example.demo.entity.Account;
import com.example.demo.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User getUser(int id) {
		return userDAO.getUser(id);
	}

	@Override
	public void saveUser(User user, Account account) {
		userDAO.saveUser(user, account);
		
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public User getUserFromUsername(String username) {
		
		return userDAO.getUserFromUsername(username);
	}
	
	@Override
	public void saveUser(User tempUser) {
		userDAO.saveUser(tempUser);
		
	}

	@Override
	public void updateUserWithAccount(User user, Account account) {
		userDAO.updateUserWithAccount(user, account);
		
	}

}
