package org.st411ar.papers.dao;

import java.util.List;
import lombok.Data;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import org.springframework.orm.hibernate3.HibernateTemplate;

import org.st411ar.papers.entity.*;
import org.st411ar.papers.util.HibernateUtil;

@Data
public class HibernateTransactionDAO implements DAO {

	private HibernateTemplate template;


	public List<User> getUsers() {
		return template.find("from User");
	}

	public List<Book> getBooks() {
        return template.find("from Book");
	}

	public List<Order> getOrders() {
        return template.find("from Order");
	}

}