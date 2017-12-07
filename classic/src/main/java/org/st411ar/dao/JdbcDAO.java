package org.st411ar.dao;


import java.util.ArrayList;
import java.util.List;


import java.sql.*;


import org.st411ar.entity.*;

import org.st411ar.util.HibernateUtil;


public class JdbcDAO implements DAO {

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String URI = "jdbc:mysql://127.0.0.1:3306/classic";
    private static final String NAME = "root";
    private static final String PASSWORD = "qwerty";

    private static final String USERS_QUERY = "select id, name from users";
    private static final String BOOKS_QUERY = "select id, name from books";
    private static final String ORDERS_QUERY = "select id, user_id, book_id from orders";


    public JdbcDAO() throws ClassNotFoundException {
        Class.forName(DRIVER);
    }


	public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URI, NAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(USERS_QUERY);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                User user = new User();
                user.setId(id);
                user.setName(name);

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("SQLException has been caught");
            e.printStackTrace();
            return new ArrayList<User>();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("finally SQLException has been caught");
                e.printStackTrace();
            }
        }

        return users;
	}

	public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URI, NAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(BOOKS_QUERY);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                Book book = new Book();
                book.setId(id);
                book.setName(name);

                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("SQLException has been caught");
            e.printStackTrace();
            return new ArrayList<Book>();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("finally SQLException has been caught");
                e.printStackTrace();
            }
        }

        return books;
	}

	public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();

        List<User> users = getUsers();
        List<Book> books = getBooks();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URI, NAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ORDERS_QUERY);


            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long userId = resultSet.getLong("user_id");
                long bookId = resultSet.getLong("book_id");


                Order order = new Order();
                order.setId(id);

                for (User user : users) {
                    if (userId == user.getId()) {
                        order.setUser(user);
                        break;
                    }
                }

                for (Book book : books) {
                    if (bookId == book.getId()) {
                        order.setBook(book);
                        break;
                    }
                }

                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("SQLException has been caught");
            e.printStackTrace();
            return new ArrayList<Order>();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("finally SQLException has been caught");
                e.printStackTrace();
            }
        }

        return orders;
	}
}