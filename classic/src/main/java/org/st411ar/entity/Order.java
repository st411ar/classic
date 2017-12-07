package org.st411ar.entity;

public class Order {
	private long id;
	private User user;
	private Book book;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "order id: '" + id + "'\nuser: " + user + "\nbook: " + book;
	}
}