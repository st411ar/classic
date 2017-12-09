package org.st411ar.papers.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import lombok.Data;

import org.st411ar.papers.entity.*;
import org.st411ar.papers.util.HibernateUtil;

@Data
public class JdbcDAO implements DAO {

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String HOST = "jdbc:mysql://127.0.0.1:3306/";
    private static final String DB = "papers";

    private static final String URI = HOST + DB;
    private static final String NAME = "root";
    private static final String PASSWORD = "qwerty";

    private static final String USERS_QUERY = "select id, name from users";
    private static final String BOOKS_QUERY = "select id, name from books";
    private static final String ORDERS_QUERY = "select id, user_id, book_id from orders";


    public JdbcDAO() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println( "can't find jdbc driver class" );
            e.printStackTrace();
        }
    }


	public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(URI, NAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(USERS_QUERY);
        ) {
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
        }

        return users;
	}

	public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(URI, NAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(BOOKS_QUERY);
        ) {
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
        }

        return books;
	}

	public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();

        List<User> users = getUsers();
        List<Book> books = getBooks();

        try (
                Connection connection = DriverManager.getConnection(URI, NAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(ORDERS_QUERY);
        ) {
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
        }

        return orders;
	}
}