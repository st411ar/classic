package org.st411ar.dao;

import java.util.List;

import org.st411ar.entity.*;

public interface DAO {
	public List<User> getUsers();
	public List<Book> getBooks();
	public List<Order> getOrders();
}