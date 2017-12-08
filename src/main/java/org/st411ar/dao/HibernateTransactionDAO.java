package org.st411ar.dao;


import java.util.List;
import lombok.Data;


import org.hibernate.Hibernate;
import org.hibernate.Session;

import org.springframework.orm.hibernate3.HibernateTemplate;


import org.st411ar.entity.*;

import org.st411ar.util.HibernateUtil;

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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Order> orders = session.createQuery("from Order").list();
        for (Order order : orders) {
            Hibernate.initialize(order.getUser());
            Hibernate.initialize(order.getBook());
        }
        session.getTransaction().commit();
        return orders;
        // problem with lazy initialization
        // return template.find("from Order");
	}

}