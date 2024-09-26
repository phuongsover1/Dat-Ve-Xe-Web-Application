package com.example.demo.DAO;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;

@Repository
@RequiredArgsConstructor
public class AccountDAOImpl implements AccountDAO {

	private final SessionFactory sessionFactory;
	
	@Override
	public Account getAccountFromUsername(String username) {
		List<Account> accountList;
		try (Session currentSession = sessionFactory.openSession()) {
			currentSession.beginTransaction();
			String sHQL = "FROM Account A WHERE A.username = :username";
			Query<Account> query = currentSession.createQuery(sHQL, Account.class);
			query.setParameter("username", username);
			accountList = query.getResultList();
			currentSession.getTransaction().commit();
		}
		if (accountList.isEmpty())
			return null;
		return accountList.get(0);
	}

}
