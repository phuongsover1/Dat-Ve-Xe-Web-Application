package com.example.demo.DAO;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.Ve_Xe;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {
	// need to inject the session factory
	private final SessionFactory sessionFactory;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public User getUser(int id) {
		User tempUser;
		try (Session currentSession = sessionFactory.openSession()) {
			currentSession.beginTransaction();
			tempUser = currentSession.get(User.class, id);
			currentSession.getTransaction().commit();
		}
		return tempUser;
	}

	@Override
	public void saveUser(User user, Account account) {
	//		User newUser = user;
	//			newUser.setPassword("{noop}" + user.getPassword());
	//			Role role = new Role("ROLE_USER");
	//			newUser.addRole(role);
	//		currentSession.save(newUser);
		try (Session currentSession = sessionFactory.openSession()) {
			currentSession.beginTransaction();
			Role role = new Role("ROLE_USER");
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			account.setAccountState(1);
			currentSession.save(account);
			account.addRole(role);
			user.setPhoneNumber(account.getUsername());
			user.addAccount(account);
			currentSession.save(user);
			currentSession.getTransaction().commit();
		}
	}

	@Override
	public void update(User user) {
		try (Session currentSession = sessionFactory.openSession()) {
			currentSession.beginTransaction();
			currentSession.update(user);
			currentSession.getTransaction().commit();
		}
	}
	
	

	@Override
	public User getUserFromUsername(String username) {
		List<User> userList;
		try (Session currentSession = sessionFactory.openSession()) {
			currentSession.beginTransaction();
			String sHQL = "FROM User U WHERE U.phoneNumber = :username";
			Query<User> query = currentSession.createQuery(sHQL, User.class);
			query.setParameter("username", username);
			userList = query.getResultList();
			currentSession.getTransaction().commit();
		}
		if (userList.isEmpty())
			return null;
		return userList.get(0);
	}

	@Override
	public List<Ve_Xe> getVeXeList(int id) {
		return null;
	}

	@Override
	public void saveUser(User tempUser) {
		try (Session currentSession = sessionFactory.openSession()) {
			currentSession.beginTransaction();
			currentSession.persist(tempUser);
			currentSession.getTransaction().commit();
		}
	}

	@Override
	public void updateUserWithAccount(User user, Account account) {
		try (Session currentSession = sessionFactory.openSession()) {
			currentSession.beginTransaction();
			Role role = new Role("ROLE_USER");
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			account.setAccountState(1);
			currentSession.save(account);
			account.addRole(role);
			user.addAccount(account);
			currentSession.update(user);
			currentSession.getTransaction().commit();
		}
	}

}
