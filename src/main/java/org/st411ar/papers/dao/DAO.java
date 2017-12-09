package org.st411ar.papers.dao;

import java.util.List;

import org.st411ar.papers.entity.*;

public interface DAO {
	public List<User> getUsers();
	public List<Book> getBooks();
	public List<Order> getOrders();
}