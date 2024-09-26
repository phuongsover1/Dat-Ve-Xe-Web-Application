package com.example.demo.DAO;



import java.util.List;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.entity.Ve_Xe;


public interface UserDAO {
	public User getUser(int id);
	
	public void saveUser(User user, Account account);

	public void update(User user);
	
	public void updateUserWithAccount(User user, Account account);
	
	public User getUserFromUsername(String username);
	
	public List<Ve_Xe> getVeXeList(int id);

	public void saveUser(User tempUser);
	
}
