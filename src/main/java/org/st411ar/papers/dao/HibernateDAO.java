package org.st411ar.papers.dao;

import java.util.List;
import lombok.Data;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import org.st411ar.papers.entity.*;
import org.st411ar.papers.util.HibernateUtil;

@Data
public class HibernateDAO implements DAO {
	public List<User> getUsers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        session.getTransaction().commit();
        return users;
	}

	public List<Book> getBooks() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Book> books = session.createQuery("from Book").list();
        session.getTransaction().commit();
        return books;
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
	}
}