package org.st411ar;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;


import org.hibernate.Hibernate;
import org.hibernate.Session;


import org.st411ar.entity.*;

import org.st411ar.util.HibernateUtil;


public class App {

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String URI = "jdbc:mysql://127.0.0.1:3306/classic";
    private static final String NAME = "root";
    private static final String PASSWORD = "qwerty";

    private static final String USERS_QUERY = "select id, name from users";

    public static void main( String[] args ) {
        testSimpleJDBC();
        testHibernate();
    }

    private static void testSimpleJDBC() {
        System.out.println( "testSimpleJDBC() has been started" );
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
	        Class.forName(DRIVER);
	        connection = DriverManager.getConnection(URI, NAME, PASSWORD);
	        statement = connection.createStatement();
            resultSet = statement.executeQuery(USERS_QUERY);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                System.out.println("id: '" + id + "', name: '" + name + "'");
            }
        } catch (ClassNotFoundException e) {
        	System.out.println("Can't find driver class \"" + DRIVER + "\"");
        	e.printStackTrace();
        } catch (SQLException e) {
        	System.out.println("SQLException has been caught");
        	e.printStackTrace();
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
        System.out.println( "testSimpleJDBC() has been finished" );
    }

    private static void testHibernate() {
        System.out.println( "testHibernate() has been started" );
        for (User user : getUsers()) {
            System.out.println(user);
        }
        for (Book book : getBooks()) {
            System.out.println(book);
        }
        for (Order order : getOrders()) {
            System.out.println(order);
        }

        System.out.println( "testHibernate() has been finished" );
    }

    private static List<User> getUsers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        session.getTransaction().commit();
        return users;
    }

    private static List<Book> getBooks() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Book> books = session.createQuery("from Book").list();
        session.getTransaction().commit();
        return books;

    }

    private static List<Order> getOrders() {
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